public class Student extends Person {
	// contains all the data (given name, family name, street, zip-code, city,
	// studentID., course)
	public String studID, course;

	public Student(String[] data) {
		super(new String[] { data[0], data[1], data[2], data[3], data[4] });
		studID = data[5];
		course = data[6];
		updateData();
	}

	public Student(String name, String famName, String street, String zip, String city, String studID, String course) {
		super(name, famName, street, zip, city);
		this.studID = studID;
		this.course = course;
		updateData();
	}

	public String getString() {
		return super.getString() + "\t" + studID + "\t" + course;
	}

	public void updateData() {
		super.updateData();
		data[5] = studID;
		data[6] = course;
	}
}