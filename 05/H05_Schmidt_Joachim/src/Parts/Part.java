package Parts;

public class Part {

	public static String conditionDamaged = "damaged";
	public static String conditionNew = "new";
	public static String conditionUsed = "used";

	private String name;
	private String condition;

	public Part(String name, String condition) {
		this.name = name;
		this.condition = condition;
	}

	public String getName() {
		return name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
