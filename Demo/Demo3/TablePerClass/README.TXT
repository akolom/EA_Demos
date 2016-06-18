
Table Per Class

Every subclass has full contents of Super Class

Here is the Setup:

@Entity 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 public  class MemberRole {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
