package users.api.pojoclass;

public class EmployeePOJO {
// variable declaration.
	 public String designation;
	    public String dob;
	    public String email;
	    public String empName;
	    public double experience;
	    public String mobileNo;
	    public String project;
	    public String role;
	    public String username;
//Note: use JSON file while decariong vas.(same name as in JSON file.)
//use -> https://json2csharp.com/code-converters/json-to-pojo-> copy+paste code here above. Do changes in datatype as mentioned into swagger doc-? model. eg. long.

	 //we should have to write an empty constructor.
	private EmployeePOJO() {}// Empty constructor is mandatory for De-Serialization.
	//Generate/create constructor using fields.
	public EmployeePOJO(String designation, String dob, String email, String empName, double experience,
			String mobileNo, String project, String role, String username) {
		super();
		this.designation = designation;
		this.dob = dob;
		this.email = email;
		this.empName = empName;
		this.experience = experience;
		this.mobileNo = mobileNo;
		this.project = project;
		this.role = role;
		this.username = username;
	}
// Generate/create getters & setters.
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getExperience() {
		return experience;
	}
	public void setExperience(double experience) {
		this.experience = experience;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
		
}
