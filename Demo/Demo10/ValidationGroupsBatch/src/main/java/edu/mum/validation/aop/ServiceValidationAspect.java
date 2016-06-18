package edu.mum.validation.aop;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.groups.Default;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import edu.mum.validation.groups.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import edu.mum.domain.ProductionStatus;
import edu.mum.domain.UserCredentials;
import edu.mum.service.CredentialsService;
import edu.mum.service.MemberService;
import edu.mum.validation.groups.Details;
import edu.mum.validation.groups.Production;

@Aspect
@Component
public class ServiceValidationAspect {


	@Autowired
	CredentialsService credentialsService;

    @Autowired
    private MutableAclService mutableAclService;

	// ALL service methods...
	 @Pointcut("execution(* edu.mum.service..*(..))")
	    public void applicationMethod() {}

	   @Pointcut("@annotation(edu.mum.validation.aop.ServiceValidation)")
	    public void validate() {}
	
	   // Save & Update contain domain  Object
		 @Pointcut("args(object)")
		    public void argsMethod(Object object) {}

/*
 * This Advice method actually implements a Bussiness process [ and/or part of a workflow]
 * It moves the domain object through the validation process based on managing the 
 * status[enum ProductionStatus].
  * Based on object status it assigns tasks to Admins or Supervisors.
  * The admin assignment is based on a "crude" round robin...[See determineOwner] 
*/
    @Around("validate() && applicationMethod() && argsMethod(object)")	
    public void  doValidate( ProceedingJoinPoint joinPoint, Object object) throws Throwable {
     	
    	/*
      	 * Reflection API is typically done only in special circumstances when setting the values in 
      	the usual way is not possible. Because such access usually violates the design intentions
      	of the class, it should be used with the utmost discretion...Should we consider 
      	Domain object specific AOP? Or manage list of target Domain Objects? Or give Domain objects
      	an interface?
      	  */  
	
     	ProductionStatus currentStatus = null;
     	
     	System.out.println();
     	System.out.println("DOING Validation!");
     	
     	// Using Hibernate validator...
        javax.validation.Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();      	

        /*
         * Get the validation group to validate against
         * ...and Validate!!!
         */
        Class<?> group = getValidationGroup(object);
         Set<ConstraintViolation<Object>> errors = validator.validate(object, group);
         	        	

         /*
          * Any Validation errors?
          * If errors PRINT them...
          * ELSE update status to next step in process AND PRINT success!!!
          */
        Boolean validationSuccess = errors.size() == 0 ? true : false;       
       	if (!validationSuccess) {
      	            	for (ConstraintViolation<Object> error : errors) {
      	            		System.out.println(error.getPropertyPath() + " " +error.getMessage());
      	            	}
        }
       	else {
        		currentStatus = setProductStatus(object); 
            	System.out.println("Validation Success! setting Status to: " + currentStatus);

      	}
          
       	/*
       	 * Execute joinpoint
       	 */
       joinPoint.proceed(new Object[]{object}); 
       
       /*
        *  If it is Valid in the current state [i.e., ProductStatus] 
        *  THEN move to the Status...to continue processing...
        */
       
      	if (validationSuccess) {
         	Class<?> classofObject = object.getClass();
         	Field idField = classofObject.getDeclaredField("id");
         	idField.setAccessible(true);
         	Long id = (Long) idField.get(object);
      		manageAcl(object,id,currentStatus);
      	}
       return;
   
    }
  
    /*
     *  IF NEW one set up ACL
     *  ELSE modify Status
     */
    private void manageAcl(Object object, Long id, ProductionStatus status) {
    	UserCredentials admin = determineNewOwner(id, status);
    	
    	if (status == ProductionStatus.BASIC)
    		initializeAcl(object, id,admin);
    	else modifyAcl(object,id,admin);
    }
    
    /*
     * Initialize ACL ...
     */
    private void initializeAcl(Object object, Long id,UserCredentials credentials) {
  
     	ObjectIdentity oid =  new ObjectIdentityImpl(object.getClass(), id);
        MutableAcl acl = mutableAclService.createAcl(oid);
        acl.insertAce(0, BasePermission.ADMINISTRATION,
                new PrincipalSid(credentials.getUsername()), true);
        acl.insertAce(1, BasePermission.DELETE,
                new GrantedAuthoritySid("ROLE_ADMIN"), true);
        acl.insertAce(2, BasePermission.READ,
                new GrantedAuthoritySid("ROLE_USER"), true);
        mutableAclService.updateAcl(acl);
    }
  
    /*
     * 
     */
    private void modifyAcl(Object object,Long id,UserCredentials credentials) {
    	ObjectIdentity oid =  new ObjectIdentityImpl(object.getClass(), id);
	 
    	MutableAcl  acl = (MutableAcl) mutableAclService.readAclById(oid);

     	// If no Principal must be restoring to ROLE_ADMIN - means we are all DONE!!!
     	if (credentials == null) 
     		acl.insertAce(0, BasePermission.ADMINISTRATION, new GrantedAuthoritySid("ROLE_ADMIN"), true);
  //  		acl.setOwner(new GrantedAuthoritySid("ROLE_ADMIN") );
    	else 
//    		acl.setOwner(new PrincipalSid(credentials.getUsername()) );
    		acl.insertAce(0, BasePermission.ADMINISTRATION, new PrincipalSid(credentials.getUsername()), true);    	

     	mutableAclService.updateAcl(acl);

    }
    
    /*
     * Determine NEW owner...to associated with Domain Object
     */
    private UserCredentials determineNewOwner(Long id, ProductionStatus status) {
    	UserCredentials owner = null;
    
        switch (status) {
        // "Round robin" assignment to ROLE_ADMIN
      	case BASIC:
	    	List<UserCredentials> credentials = credentialsService.findAllAdmin();
	     	Long ownerIndex =  id % credentials.size();
	     	 owner = credentials.get(ownerIndex.intValue());
    		break;

  		case DETAILS:
  			// Logged in user is current owner - want to switch to his/her supervisor
  			String userName = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
  			owner = credentialsService.findOne(userName);
  			owner = owner.getMember().getSupervisor().getUserCredentials();
  			break;
  		case PRODUCTION:
  		default:
  	    	 owner = null;

        }

     	return owner;
    }
    
    /*
     * Based on domain object being processed and it's current status, 
     *  return the current Validation class for the domain object
     */
    private Class<?> getValidationGroup(Object object) throws Throwable {
       	Class<?> classofObject = object.getClass();
   	 Field status = classofObject.getDeclaredField("status");
      status.setAccessible(true);

      ProductionStatus currentStatus  = (ProductionStatus)status.get(object);

      Class<?> returnClass = Default.class;
      
      switch (currentStatus) {
      case INVALID:
    	  returnClass = Default.class;
   	   break;
   	case BASIC:
  	  returnClass = Details.class;
  		break;

		case DETAILS:
		case PRODUCTION:
	    	  returnClass = Production.class;

      }

      return returnClass;
    }
    	 
    /*
     * Move the status to NEXT state
     */
    private ProductionStatus setProductStatus(Object object) throws Throwable {
     	Class<?> classofObject = object.getClass();
    	 Field status = classofObject.getDeclaredField("status");
       status.setAccessible(true);

       ProductionStatus returnStatus = null;
       ProductionStatus currentStatus  = (ProductionStatus)status.get(object);
        
       switch (currentStatus) {
       case INVALID:
    	   returnStatus = ProductionStatus.BASIC;
    	   break;
    	case BASIC:
    		returnStatus = ProductionStatus.DETAILS;
    		break;
 
		case DETAILS:
		case PRODUCTION:
			returnStatus =  ProductionStatus.PRODUCTION;
 
       }

	   status.set(object, returnStatus);

       return returnStatus;
 
    }
}

 


