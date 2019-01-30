import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Module extends TableItem {
	private Manager manager;
	// contains all the data (name, nr, profID, semester, participants)
	public String[] data = new String[5];
	public String name, nr, profID, semester, part;
	public ArrayList<String> participants; // stores the matriculation numbers of all participants
	
	public Module(String[] data, Manager manager) {
		this.manager=manager;
		name = data[0];
		nr = data[1];
		profID = data[2];
		semester = data[3];
		participants = new ArrayList<String>();
		if(data.length==4)
			part = "";
		else 
			part=data[4];
		addParticipants(part);
	}

	public Module(String name, String nr, String profID, String semester, String participants, Manager manager) {
		this.manager=manager;
		this.name = name;
		this.nr = nr;
		this.profID = profID;
		this.semester = semester;
		part = participants;
		this.participants = new ArrayList<String>();
		addParticipants(participants);
	}

	public String getString() {
		String out = name + "\t" + nr + "\t" + profID + "\t" + semester;
		String part = getParticipants();
		if (!part.isEmpty())
			part = "\t" + part;
		return out + part;
	}

	public void updateData() {
		data[0] = name;
		data[1] = nr;
		data[2] = profID;
		data[3] = semester;
		data[4] = getParticipants();
	}

	public void addParticipants(String list) {
		String nonStud = null;
		String[] partData = list.trim().split(",");
		for (String studID : partData)
			if (!studID.isEmpty()&&!participants.contains(studID))
				if (manager.getStudent(studID) != null)
					participants.add(studID);
				else {
					if (nonStud == null)
						nonStud = studID;
					else
						nonStud = nonStud + ", " + studID;
				}
		updateData();
		part = data[4];
		if (nonStud != null)
			JOptionPane.showMessageDialog(null, "Studenten mit Matrikelnummern: " + nonStud
					+ " existieren nicht.\nSie werden aus dem Modul gestrichen.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
	}

	public String getParticipants() {
		String out = "";
		for (int i = 0; i < participants.size(); i++) {
			out = out + participants.get(i);
			if (i < participants.size() - 1)
				out = out + ",";
		}
		return out;
	}
}