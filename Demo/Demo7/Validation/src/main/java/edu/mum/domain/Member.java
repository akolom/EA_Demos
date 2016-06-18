package edu.mum.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import edu.mum.validation.EmptyOrSize;
import edu.mum.validation.NullMinNumber;


@Entity 
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="member_id")
	private long id;
	
	@Column(length = 16)
	@NotEmpty
	private String firstName;
	
	@Column(length = 16)
	@EmptyOrSize(min=5, max = 9, message= "{EmptyOrSize}")
	private String lastName;

	@NullMinNumber(value=6)
	private Integer age;
 	
	@Column(length = 32)
	@NotEmpty
	private String title;
	
	@NullMinNumber(value=6)
 	private Integer memberNumber;

	@OneToOne(fetch=FetchType.EAGER,  cascade = CascadeType.ALL) 
 	@JoinColumn(name="memberId") 
 	UserCredentials userCredentials;
 	
    @OneToMany(mappedBy="member",fetch=FetchType.EAGER)  
    private Set<Comment> comments = new HashSet<Comment>();


 //   @Valid
     @OneToMany(mappedBy="member",fetch=FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })  
     private Set<Address> addresses = new HashSet<Address>();

 	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
 	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	
 	public UserCredentials getUserCredentials() {
		return userCredentials;
	}
	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
	public Set<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
 	public void addAddress(Address address) {
		this.addresses.add(address);
		address.setMember(this);
	}
  }
