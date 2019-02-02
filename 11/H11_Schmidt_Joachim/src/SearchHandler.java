import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SearchHandler implements ActionListener {
	MainFrame mF;

	public SearchHandler(MainFrame mF) {
		this.mF = mF;
	}

	/**
	 * H4: Handles a click onto the name search button
	 * 
	 * @param e The event parameters
	 */
	public void actionPerformed(ActionEvent e) {

		Manager manager = Manager.createManagerInst(null);

		String search = mF.searchField.getText();

		// Reset everything
		new ResetHandler(mF).actionPerformed(null);
		mF.searchField.setText(search);

		if (search.isEmpty()) {
			if (e != null)
				JOptionPane.showMessageDialog(mF, "Leerer Suchtext!");
		} else {

			if (mF.searchStudentRadioButton.isSelected()) {

				manager.showStudents(manager.searchStudentName(search));

				manager.filterStudents = search;
				/*
				 * StringBuilder filter = new StringBuilder(); for (Student stud :
				 * manager.searchStudentName(search)) { filter.append(stud.getString());
				 * filter.append("\n"); } manager.filterStudents = filter.toString();
				 */

			} else if (mF.searchProfRadioButton.isSelected()) {

				manager.showProfs(manager.searchProfName(search));

				manager.filterProfs = search;

			} else if (mF.searchModuleRadioButton.isSelected()) {

				manager.showModules(manager.searchModuleName(search));

				manager.filterModules = search;

			}

			mF.resetButton.setVisible(true);
		}

	}
}
