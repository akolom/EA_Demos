
Table Per sub Class

subclasses are joined with super class

Here is the Setup:

@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
public  class MemberRole {


@Entity
public class Volunteer extends MemberRole {

// NO PRIMARY KEY