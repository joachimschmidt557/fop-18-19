import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddHandler implements ActionListener {
	MainFrame mF;
	JTextField searchField;
	JTextField[] allFields;
	DefaultTableModel model;
	String[] newData;

	public AddHandler(MainFrame mF) {
		this.mF = mF;
	}

	public void actionPerformed(ActionEvent e) {
		Manager manager = Manager.createManagerInst(null);
		boolean allFieldsSet = true;
		if (mF.addStudentRadioButton.isSelected()) {
			searchField = mF.studIDField;
			allFields = new JTextField[] { mF.nameField, mF.famNameField, mF.streetField, mF.zipField, mF.cityField,
					mF.studIDField, mF.courseField };
			model = mF.studentModel;
		} else if (mF.addProfRadioButton.isSelected()) {
			searchField = mF.staffIDField;
			allFields = new JTextField[] { mF.nameField, mF.famNameField, mF.streetField, mF.zipField, mF.cityField,
					mF.staffIDField, mF.fieldField };
			model = mF.profModel;
		} else {
			searchField = mF.mNrField;
			allFields = new JTextField[] { mF.mNameField, mF.mNrField, mF.profIDField, mF.semField};
			model = mF.moduleModel;
		}

		for (JTextField f : allFields) {
			if (f.getText().isEmpty()) {
				allFieldsSet = false;
				break;
			}
		}
		if (!allFieldsSet)
			JOptionPane.showMessageDialog(null, "Es wurden nicht alle benötigeten Felder ausgefüllt.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
		else {
			String newID = searchField.getText();
			if (mF.addStudentRadioButton.isSelected()) {
				if (manager.getStudent(newID) != null) {
					JOptionPane.showMessageDialog(null, "Es gibt schon einen Studenten mit Matrikelnummer: " + newID,
							"Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					newData = new String[] { allFields[0].getText(), allFields[1].getText(), allFields[2].getText(),
							allFields[3].getText(), allFields[4].getText(), newID, allFields[6].getText() };
					// only add a row to JTable if either there is no filter active, or the new
					// Student satisfies the filter's restrictions
					if (manager.filteredStudents == null || allFields[0].getText().contains(manager.filterStudents)
							|| allFields[1].getText().contains(manager.filterStudents)) {
						model.addRow(newData);
					}
					manager.students.add(new Student(newData));
				}
			} else if (mF.addProfRadioButton.isSelected()) {
				if (manager.getProf(newID) != null) {
					JOptionPane.showMessageDialog(null, "Es gibt schon einen Professor mit Kürzel: " + newID,
							"Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					newData = new String[] { allFields[0].getText(), allFields[1].getText(), allFields[2].getText(),
							allFields[3].getText(), allFields[4].getText(), newID, allFields[6].getText() };
					// only add a row to JTable if either there is no filter active, or the new
					// Professor satisfies the filter's restrictions
					if (manager.filteredProfs == null || allFields[0].getText().contains(manager.filterProfs)
							|| allFields[1].getText().contains(manager.filterProfs)) {
						model.addRow(newData);
					}
					manager.profs.add(new Professor(newData));
				}

			} else {
				if (manager.getModule(newID) != null) {
					JOptionPane.showMessageDialog(null, "Es gibt schon ein Modul mit Modulnummer: " + newID,
							"Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					newData = new String[] { allFields[0].getText(), newID, allFields[2].getText(),
							allFields[3].getText(), "" };
					if (manager.getProf(allFields[2].getText()) == null) {
						JOptionPane.showMessageDialog(null,
								"Der Professor mit Kürzel " + newData[2] + " existiert nicht.", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						Module newModule = new Module(newData, manager);
						newModule.addParticipants(mF.partField.getText());
						// only add a row to JTable if either there is no filter active, or the new
						// Module satisfies the filter's restrictions
						if (manager.filteredModules == null || allFields[0].getText().contains(manager.filterModules)) {
							model.addRow(newModule.data);
						}
						manager.modules.add(newModule);
					}
				}
				mF.partField.setText("");
			}
			for (JTextField f : allFields)
				f.setText("");
		}
	}
}
