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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

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
 	private UserCredentials userCredentials;
 	
//    @OneToMany(mappedBy="member",fetch=FetchType.EAGER)  
	@Transient
    private Set<Comment> comments = new HashSet<Comment>();

 
    @OneToMany(mappedBy="supervisor",fetch=FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })  
    private Set<Member> admins = new HashSet<Member>();

    @ManyToOne( fetch=FetchType.EAGER)  
    private Member supervisor;

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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
 	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}
	
 	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public UserCredentials getUserCredentials() {
		return userCredentials;
	}
	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
	public Set<Member> getAdmins() {
		return admins;
	}
	public void setAdmins(Set<Member> admins) {
		this.admins = admins;
	}
 	public void addAddress(Member admin) {
		this.admins.add(admin);
		admin.setSupervisor(this);
	}
	public Member getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Member supervisor) {
		this.supervisor = supervisor;
	}
  }
