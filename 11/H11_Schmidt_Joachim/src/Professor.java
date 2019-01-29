import java.util.ArrayList;

public class Professor extends Person {

	// contains all the data (given name, family name, street, zip-code, city,
	// staffId, field, modules)
	public String staffID, field, mod;
	public ArrayList<String> modules;

	public Professor(String[] data) {
		super(new String[] { data[0], data[1], data[2], data[3], data[4] });
		staffID = data[5];
		field = data[6];
		updateData();
	}

	public Professor(String name, String famName, String street, String zip, String city, String staffID, String field,
			String modules) {
		super(name, famName, street, zip, city);
		this.staffID = staffID;
		this.field = field;
		updateData();
	}

	public String getString() {
		return super.getString() + "\t" + staffID + "\t" + field;
	}

	public void updateData() {
		super.updateData();
		data[5] = staffID;
		data[6] = field;
	}
}