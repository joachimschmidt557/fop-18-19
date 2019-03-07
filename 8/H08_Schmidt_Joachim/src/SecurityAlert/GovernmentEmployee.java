package SecurityAlert;

/**
 * An abstract class that defines an abstract employee
 * @author Florian Kadner
 *
 */
abstract class GovernmentEmployee implements Comparable<GovernmentEmployee> {
	
	protected String name;
	protected Sex sex;

	abstract SecurityLevel getSecurityLevel();
	
	abstract String getTitle();
	
	@Override
	public String toString() {
		return getTitle();
	}

}
