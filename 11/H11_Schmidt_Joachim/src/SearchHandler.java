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
	 * @param e The event parameters
	 */
	public void actionPerformed(ActionEvent e) {

		Manager manager = Manager.createManagerInst(null);

		String search = mF.searchField.getText();

		if (search.isEmpty()) {
			if (e != null)
				JOptionPane.showMessageDialog(mF, "Empty search!");
		} else {
			
			if (mF.searchStudentRadioButton.isSelected()) {
				manager.showStudents(manager.searchStudentName(search));
			} else if (mF.searchProfRadioButton.isSelected()) {
				manager.showProfs(manager.searchProfName(search));
			} else if (mF.searchModuleRadioButton.isSelected()) {
				manager.showModules(manager.searchModuleName(search));
			}
			
			mF.resetButton.setVisible(true);
		}
		
	}
}
