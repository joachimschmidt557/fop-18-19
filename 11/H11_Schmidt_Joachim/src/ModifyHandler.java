import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifyHandler implements ActionListener {
	MainFrame mF;
	public ModifyHandler(MainFrame mF) {
		this.mF = mF;
	}

	public void actionPerformed(ActionEvent e) {
		Manager manager = Manager.createManagerInst(null);
	
		String modID = mF.deleteField.getText();
		mF.deleteField.setText("");

		if (mF.deleteStudentRadioButton.isSelected()) {
			Student newStudent = manager.getStudent(modID);

			// find the correct row from JTable if the JTable contains a line corresponding
			// to newStudent
			int index;
			if (manager.filteredStudents == null)
				index = manager.students.indexOf(newStudent);
			else
				index = manager.filteredStudents.indexOf(newStudent);

			if (newStudent != null) {
				JTextField nameField = new JTextField(newStudent.name.length());
				nameField.setText(newStudent.name);
				JTextField famNameField = new JTextField(newStudent.famName.length());
				famNameField.setText(newStudent.famName);
				JTextField streetField = new JTextField(newStudent.street.length());
				streetField.setText(newStudent.street);
				JTextField zipField = new JTextField(newStudent.zip.length());
				zipField.setText(newStudent.zip);
				JTextField cityField = new JTextField(newStudent.city.length());
				cityField.setText(newStudent.city);
				JTextField studIDField = new JTextField(newStudent.studID.length());
				studIDField.setText(newStudent.studID);
				JTextField courseField = new JTextField(newStudent.course.length());
				courseField.setText(newStudent.course);

				JPanel modPanel = new JPanel();
				modPanel.add(nameField);
				modPanel.add(famNameField);
				modPanel.add(streetField);
				modPanel.add(zipField);
				modPanel.add(cityField);
				modPanel.add(studIDField);
				modPanel.add(courseField);

				int result = JOptionPane.showConfirmDialog(null, modPanel, "Geben Sie hier die neuen Daten ein:",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					newStudent.name = nameField.getText();
					newStudent.famName = famNameField.getText();
					newStudent.street = streetField.getText();
					newStudent.zip = zipField.getText();
					newStudent.city = cityField.getText();
					newStudent.studID = studIDField.getText();
					newStudent.course = courseField.getText();
					newStudent.updateData();

					// do not modify a row from JTable if the JTable does not contain a line
					// corresponding to newStudent
					if (index != -1) {
						mF.studentModel.removeRow(index);
						mF.studentModel.insertRow(index, newStudent.data);
					}
					
					// update all modules this student participates in, if the studID is changed
					if(!modID.equals(newStudent.studID)) {
						int modIndex;
						for(Module m: manager.getStudentModules(modID)) {
							m.participants.set(m.participants.indexOf(modID), newStudent.studID);
							m.data[4] = m.getParticipants();
							if (manager.filteredModules == null)
								modIndex = manager.modules.indexOf(m);
							else
								modIndex = manager.filteredModules.indexOf(m);

							// do not modify a row from JTable if the JTable does not contain a line
							// corresponding to newModule
							if (modIndex != -1) {
								mF.moduleModel.removeRow(modIndex);
								mF.moduleModel.insertRow(modIndex, m.data);
							}
						}
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "Es gibt keinen Studenten mit Matrikelnummer: " + modID,
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (mF.deleteProfRadioButton.isSelected()) {
			Professor newProf = manager.getProf(modID);

			// find the correct row from JTable if the JTable contains a line corresponding
			// to newProf
			int index;
			if (manager.filteredProfs == null)
				index = manager.profs.indexOf(newProf);
			else
				index = manager.filteredProfs.indexOf(newProf);

			if (newProf != null) {
				JTextField nameField = new JTextField(newProf.name.length());
				nameField.setText(newProf.name);
				JTextField famNameField = new JTextField(newProf.famName.length());
				famNameField.setText(newProf.famName);
				JTextField streetField = new JTextField(newProf.street.length());
				streetField.setText(newProf.street);
				JTextField zipField = new JTextField(newProf.zip.length());
				zipField.setText(newProf.zip);
				JTextField cityField = new JTextField(newProf.city.length());
				cityField.setText(newProf.city);
				JTextField staffIDField = new JTextField(newProf.staffID.length());
				staffIDField.setText(newProf.staffID);
				JTextField fieldField = new JTextField(newProf.field.length());
				fieldField.setText(newProf.field);

				JPanel modPanel = new JPanel();
				modPanel.add(nameField);
				modPanel.add(famNameField);
				modPanel.add(streetField);
				modPanel.add(zipField);
				modPanel.add(cityField);
				modPanel.add(staffIDField);
				modPanel.add(fieldField);

				int result = JOptionPane.showConfirmDialog(null, modPanel, "Geben Sie hier die neuen Daten ein:",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					newProf.name = nameField.getText();
					newProf.famName = famNameField.getText();
					newProf.street = streetField.getText();
					newProf.zip = zipField.getText();
					newProf.city = cityField.getText();
					newProf.staffID = staffIDField.getText();
					newProf.field = fieldField.getText();
					newProf.updateData();

					// do not modify a row from JTable if the JTable does not contain a line
					// corresponding to newProf
					if (index != -1) {
						mF.profModel.removeRow(index);
						mF.profModel.insertRow(index, newProf.data);
					}
					
					// update all modules taught by this prof, if the staffID is changed
					if(!modID.equals(newProf.staffID)) {
						int modIndex;
						for(Module m: manager.getProfModules(modID)) {
							m.profID=newProf.staffID;
							m.data[2]=newProf.staffID;
							if (manager.filteredModules == null)
								modIndex = manager.modules.indexOf(m);
							else
								modIndex = manager.filteredModules.indexOf(m);

							// do not modify a row from JTable if the JTable does not contain a line
							// corresponding to newModule
							if (modIndex != -1) {
								mF.moduleModel.removeRow(modIndex);
								mF.moduleModel.insertRow(modIndex, m.data);
							}
						}
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "Es gibt keinen Professor mit Kürzel: " + modID,
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			Module newModule = manager.getModule(modID);

			// find the correct row from JTable if the JTable contains a line corresponding
			// to newModule
			int index;
			if (manager.filteredModules == null)
				index = manager.modules.indexOf(newModule);
			else
				index = manager.filteredModules.indexOf(newModule);

			if (newModule != null) {
				JTextField nameField = new JTextField(newModule.name.length());
				nameField.setText(newModule.name);
				JTextField nrField = new JTextField(newModule.nr.length());
				nrField.setText(newModule.nr);
				JTextField profIDField = new JTextField(newModule.profID.length());
				profIDField.setText(newModule.profID);
				JTextField semField = new JTextField(newModule.semester.length());
				semField.setText(newModule.semester);
				JTextField partField = new JTextField(newModule.getParticipants().length());
				partField.setText(newModule.getParticipants());

				JPanel modPanel = new JPanel();
				modPanel.add(nameField);
				modPanel.add(nrField);
				modPanel.add(profIDField);
				modPanel.add(semField);
				modPanel.add(partField);

				int result = JOptionPane.showConfirmDialog(null, modPanel, "Geben Sie hier die neuen Daten ein:",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					newModule.name = nameField.getText();
					newModule.nr = nrField.getText();
					newModule.profID = profIDField.getText();
					newModule.semester = semField.getText();
					newModule.addParticipants(partField.getText());

					// do not modify a row from JTable if the JTable does not contain a line
					// corresponding to newModule
					if (index != -1) {
						mF.moduleModel.removeRow(index);
						mF.moduleModel.insertRow(index, newModule.data);
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "Es gibt kein Modul mit Modulnummer: " + modID,
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
