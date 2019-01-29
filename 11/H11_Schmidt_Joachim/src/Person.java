public abstract class Person extends TableItem{

	public String name, famName, street, zip, city;
	public String[] data = new String[7];

	public Person(String[] data) {
		name = data[0];
		famName = data[1];
		street = data[2];
		zip = data[3];
		city = data[4];
	}

	public Person(String name, String famName, String street, String zip, String city) {
		this.name = name;
		this.famName = famName;
		this.street = street;
		this.zip = zip;
		this.city = city;
	}

	public String getString() {
		return name + "\t" + famName + "\t" + street + "\t" + zip + "\t" + city;
	}

	public void updateData() {
		data[0] = name;
		data[1] = famName;
		data[2] = street;
		data[3] = zip;
		data[4] = city;
	}
}