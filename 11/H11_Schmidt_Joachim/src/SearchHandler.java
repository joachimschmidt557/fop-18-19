import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SearchHandler implements ActionListener {
	MainFrame mF;

	public SearchHandler(MainFrame mF) {
		this.mF = mF;
	}

	// TODO H4
	public void actionPerformed(ActionEvent e) {
		
		String search = mF.searchField.getText();
		
		if (search.isEmpty())
			JOptionPane.showMessageDialog(mF, "Empty search!");
		
		Manager manager = Manager.createManagerInst(null);
		
		if (mF.searchStudentRadioButton.isSelected())
			manager.showStudents(manager.searchStudentName(search));
		else if (mF.searchProfRadioButton.isSelected())
			manager.showProfs(manager.searchProfName(search));
		else if (mF.searchModuleRadioButton.isSelected())
			manager.showModules(manager.searchModuleName(search));
		
	}
}
