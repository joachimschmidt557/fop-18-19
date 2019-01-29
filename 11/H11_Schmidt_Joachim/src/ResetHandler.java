import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetHandler implements ActionListener {
	MainFrame mF;

	public ResetHandler(MainFrame mF) {
		this.mF = mF;
	}

	public void actionPerformed(ActionEvent e) {
		Manager manager = Manager.createManagerInst(null);
		mF.searchField.setText("");
		manager.showStudents(manager.students);
		manager.showProfs(manager.profs);
		manager.showModules(manager.modules);
		mF.resetButton.setVisible(false);
	}
}
