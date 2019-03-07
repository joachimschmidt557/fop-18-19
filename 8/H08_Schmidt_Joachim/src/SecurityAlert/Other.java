package SecurityAlert;

public class Other extends GovernmentEmployee {

	/**
	 * Constructs a new government employee who is not
	 * president or a secretary
	 * @param name The name
	 * @param sex The sex
	 */
	public Other(String name, Sex sex) {

		this.name = name;
		this.sex = sex;

	}

	/**
	 * Compares the security level of this government employee
	 * @return 1 when this government employee has a higher rank,
	 * 0 when the government employee has a higher rank,
	 * -1 when this government employee has a lower rank
	 */
	@Override
	public int compareTo(GovernmentEmployee o) {

		if (o.getSecurityLevel() == SecurityLevel.LOW)
			return 0;
		else
			return -1;
		
	}

	/**
	 * Gets the security level of this government employee
	 * @return The security level
	 */
	@Override
	SecurityLevel getSecurityLevel() {

		return SecurityLevel.LOW;

	}

	/**
	 * Returns a formal title of this government employee
	 * @return The title
	 */
	@Override
	String getTitle() {

		StringBuilder sb = new StringBuilder();

		sb.append(this.sex == Sex.FEMALE ? "Madame" : "Mister");
		sb.append(" ");
		sb.append(this.name);

		return sb.toString();
	}

}
