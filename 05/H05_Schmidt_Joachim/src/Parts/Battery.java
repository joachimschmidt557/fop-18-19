package Parts;

public class Battery extends Part {
	
	private int level;

	public Battery(String condition, int lvl) {
		super("Battery", condition);
		level = lvl;
	}
	
	public int getLevel() { return level; }
	
	public void setLevel(int lvl) {
		if (lvl < 100) {
			level = lvl;
			setCondition(Part.conditionUsed);
			if (lvl <= 0) {
				level = 0;
				setCondition(Part.conditionDamaged);
			}			
		}
	}

}
