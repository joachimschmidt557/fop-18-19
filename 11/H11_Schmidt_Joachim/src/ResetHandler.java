import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Predicate;

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
		
		// H5: Added
		mF.customSearchField.setText("");
		
		// Reset the filter
		manager.filterStudents = "";
		manager.filterProfs = "";
		manager.filterModules = "";
		
		manager.filterStudentPred = x -> true;
		manager.filterProfPred = x -> true;
		manager.filterModulePred = x -> true;
	}
}
