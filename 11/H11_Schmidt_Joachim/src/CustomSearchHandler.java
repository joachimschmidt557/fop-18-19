import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Handles a click on the search button of the customized search
 * 
 * 
 * @author joachim
 *
 */
public class CustomSearchHandler implements ActionListener {

	MainFrame mF;

	/**
	 * Constructs a new CustomSearchHandler
	 * 
	 * @param mF The Main Frame
	 */
	public CustomSearchHandler(MainFrame mF) {

		this.mF = mF;

	}

	/**
	 * H5: Handles a click onto the custom search button
	 * 
	 * @param e The event parameters
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Manager manager = Manager.createManagerInst(null);

		String searchString = mF.customSearchField.getText();
		String searchField = mF.customSearchComboBox.getSelectedItem().toString();

		// Reset everything
		new ResetHandler(mF).actionPerformed(null);
		mF.customSearchField.setText(searchString);

		if (searchString.isEmpty()) {
			if (e != null)
				JOptionPane.showMessageDialog(mF, "Leerer Suchtext!");
		} else {

			if (mF.customSearchStudentRadioButton.isSelected()) {
				manager.showStudents(manager.searchStudentByField(searchString, searchField,
						mF.customSearchCaseSensitive.isSelected()));
			} else if (mF.customSearchProfRadioButton.isSelected()) {
				manager.showProfs(manager.searchProfByField(searchString, searchField,
						mF.customSearchCaseSensitive.isSelected()));
			} else if (mF.customSearchModuleRadioButton.isSelected()) {
				manager.showModules(manager.searchModuleByField(searchString, searchField,
						mF.customSearchCaseSensitive.isSelected()));
			}

		}

	}

}
