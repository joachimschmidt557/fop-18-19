package SecurityAlert;

import java.util.ArrayList;

public class SecurityAgency {

	private ArrayList<GovernmentEmployee> emps;
	private EmergencyQueue<GovernmentEmployee> emergency;
	private AlertLevel al;

	/**
	 * Constructs a new object of type SecurityAgency
	 */
	public SecurityAgency() {

		this.al = AlertLevel.LOW;

	}

	/**
	 * Adds a Government Employee to the queue
	 * @param e The government employee
	 */
	public void add(GovernmentEmployee e) {

		emps.add(e);

	}

	/**
	 * Changes the alert level and perfoms actions when necessary
	 * @param newAL
	 */
	public void changeAlertLevel(AlertLevel newAL) {

		if (al == AlertLevel.ELEVATED) {
			// Move all presidents to queue
			for (GovernmentEmployee govEmployee : emps) {
				if (govEmployee instanceof President)
					emergency.enqueue(govEmployee);
			}
		} else if (al == AlertLevel.HIGH) {
			// Move all presidents and all secretaries to queue
			for (GovernmentEmployee govEmployee : emps) {
				if (govEmployee instanceof President || govEmployee instanceof Secretary)
					emergency.enqueue(govEmployee);
			}
		} else if (al == AlertLevel.SEVERE) {
			// Move everyone to queue
			for (GovernmentEmployee govEmployee : emps) {
				emergency.enqueue(govEmployee);
			}
		}

	}

}
