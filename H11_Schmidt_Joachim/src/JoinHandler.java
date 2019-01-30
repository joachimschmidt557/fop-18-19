import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;

public class JoinHandler implements ActionListener {
	MainFrame mF;
	// stores the mode of our table join: 1->StudMod, 2->ProfMod, 3->ModStud,
	// 4->ModProf, 5->ModProfStud
	int mode;
	// stores the data we want to display in our JTable
	ArrayList<String[]> items;

	public JoinHandler(MainFrame mF, int mode) {
		this.mF = mF;
		this.mode = mode;
	}

	public void actionPerformed(ActionEvent e) {
		Manager manager = Manager.createManagerInst(null);
		// make sure the table is clear before adding new Rows
		while (mF.joinModel.getRowCount() != 0)
			mF.joinModel.removeRow(0);
		items = new ArrayList<String[]>();
		String title;
		String[] joinColumns;
		if (mode == 1) {
			title = "Studenten mit Modulen";
			// StudMod stores s.name, s.famName, s.street, s.zip, s.city, s.studID, s.course,
			// m.name, m.nr, m.profID, m.semester
			joinColumns = new String[] { "Vorname", "Nachname", "Straße, Nr.", "PLZ", "Ort", "Matrikelnr.",
					"Studiengang", "Modulname", "Modulnummer", "Professor (Kürzel)", "Semester" };
			Module m1;
			for (Student s : manager.students) {
				ArrayList<Module> mods = manager.getStudentModules(s.studID);
				if (mods.size() == 0) {
					items.add(new String[] { s.name, s.famName, s.street, s.zip, s.city, s.studID, s.course, "", "", "",
							"" });
				} else {
					m1 = mods.get(0);
					items.add(new String[] { s.name, s.famName, s.street, s.zip, s.city, s.studID, s.course, m1.name,
							m1.nr, m1.profID, m1.semester });
					for (int i = 1; i < mods.size(); i++) {
						m1 = mods.get(i);
						items.add(new String[] { "", "", "", "", "", "", "", m1.name, m1.nr, m1.profID, m1.semester });
					}
				}
			}
		} else if (mode == 2) {
			title = "Professoren mit Modulen";
			// ProfMod stores p.name, p.famName, p.street, p.zip, p.city, p.staffID,
			// p.field, m.name, m.nr, m.semester, m.part
			joinColumns = new String[] { "Vorname", "Nachname", "Straße, Nr.", "PLZ", "Ort", "Kürzel", "Fachgebiet",
					"Modulname", "Modulnummer", "Semester", "Teilnehmer" };
			Module m1;
			for (Professor p : manager.profs) {
				ArrayList<Module> mods = manager.getProfModules(p.staffID);
				if (mods.size() == 0) {
					items.add(new String[] { p.name, p.famName, p.street, p.zip, p.city, p.staffID, p.field, "", "", "",
							"" });
				} else {
					m1 = mods.get(0);
					items.add(new String[] { p.name, p.famName, p.street, p.zip, p.city, p.staffID, p.field, m1.name,
							m1.nr, m1.semester, m1.part });
					for (int i = 1; i < mods.size(); i++) {
						m1 = mods.get(i);
						items.add(new String[] { "", "", "", "", "", "", "", m1.name, m1.nr, m1.semester, m1.part });
					}
				}
			}
		} else if (mode == 3) {
			title = "Module mit Studenten";
			// ModStud stores m.name, m.nr, m.profID, m.semester, s.name, s.famName,
			// s.street, s.zip, s.city, s.studID, s.course
			joinColumns = new String[] { "Modulname", "Modulnummer", "Professor (Kürzel)", "Semester", "Vorname",
					"Nachname", "Straße, Nr.", "PLZ", "Ort", "Matrikelnr.", "Studiengang" };
			Student s;
			for (Module m : manager.modules) {
				ArrayList<String> studs = m.participants;
				if (studs.size() == 0) {
					items.add(new String[] { m.name, m.nr, m.profID, m.semester, "", "", "", "", "", "", "" });
				} else {
					s = manager.getStudent(studs.get(0));
					items.add(new String[] { m.name, m.nr, m.profID, m.semester, s.name, s.famName, s.street, s.zip,
							s.city, s.studID, s.course });
					for (int i=1; i<studs.size();i++) {
						s = manager.getStudent(studs.get(i));
						items.add(new String[] { "", "", "", "", s.name, s.famName, s.street, s.zip, s.city, s.studID,
								s.course });
					}
				}
			}
		} else if (mode == 4) {
			title = "Module mit Professoren";
			// ModProf stores m.name, m.nr, m.semester, p.name, p.famName, p.street, p.zip,
			// p.city, p.staffID, p.field, m.part
			joinColumns = new String[] { "Modulname", "Modulnummer", "Semester", "Vorname", "Nachname", "Straße, Nr.",
					"PLZ", "Ort", "Kürzel", "Fachgebiet", "Teilnehmer" };
			Professor p;
			for (Module m : manager.modules) {
				p = manager.getProf(m.profID);
				items.add(new String[] { m.name, m.nr, m.semester, p.name, p.famName, p.street, p.zip, p.city,
						p.staffID, p.field, m.part });
			}
		} else {
			title = "Module mit Professoren und Studenten";
			// ModProfStud stores m.name, m.nr, m.semester, p.name, p.famName, p.street,
			// p.zip, p.city, p.staffID, p.field, s.name, s.famName, s.street, s.zip,
			// s.city, s.studID, s.course
			joinColumns = new String[] { "Modulname", "Modulnummer", "Semester", "Vorname (P)", "Nachname (P)",
					"Straße, Nr. (P)", "PLZ (P)", "Ort (P)", "Kürzel", "Fachgebiet", "Vorname (S)", "Nachname (S)",
					"Straße, Nr. (S)", "PLZ (S)", "Ort (S)", "Matrikelnr.", "Studiengang" };
			Professor p;
			Student s;
			for (Module m : manager.modules) {
				p = manager.getProf(m.profID);
				ArrayList<String> studs = m.participants;
				if (studs.size() == 0) {
					items.add(new String[] { m.name, m.nr, m.semester, p.name, p.famName, p.street, p.zip, p.city,
							p.staffID, p.field, "", "", "", "", "", "", "" });
				} else {
					s = manager.getStudent(studs.get(0));
					items.add(new String[] { m.name, m.nr, m.semester, p.name, p.famName, p.street, p.zip, p.city,
							p.staffID, p.field, s.name, s.famName, s.street, s.zip, s.city, s.studID, s.course });
					for (int i=1;i<studs.size();i++) {
						s = manager.getStudent(studs.get(i));
						items.add(new String[] { "", "", "", "", "", "", "", "", "", "", s.name, s.famName, s.street,
								s.zip, s.city, s.studID, s.course });
					}
				}
			}
		}
		// display items
		mF.joinBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title);
		mF.joinPanel.setBorder(mF.joinBorder);
		mF.joinModel.setColumnIdentifiers(joinColumns);
		for (String[] item : items) {
			mF.joinModel.addRow(item);
		}

		// update layouts
		mF.joinPanel.setPreferredSize(new Dimension(mF.size[0], mF.joinTable.getRowCount() * 15 + 50));
		mF.resizeColumnWidth(mF.joinTable);
		mF.joinReset.setVisible(true);
		mF.pack();
		
		// set default filename
		mF.joinFile=title+".txt";
	}
}
