import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Manager {
	private static MainFrame mainFrame = null;
	public String studentFile = "Studenten.txt";
	public ArrayList<Student> students = new ArrayList<Student>();
	public ArrayList<Student> filteredStudents = null;
	public String filterStudents = "";
	public String profFile = "Professoren.txt";
	public ArrayList<Professor> profs = new ArrayList<Professor>();
	public ArrayList<Professor> filteredProfs = null;
	public String filterProfs = "";
	public String moduleFile = "Module.txt";
	public ArrayList<Module> modules = new ArrayList<Module>();
	public ArrayList<Module> filteredModules = null;
	public String filterModules = "";

	static Manager inst = null;

	public static Manager createManagerInst(MainFrame mF) {
		if (mainFrame == null)
			mainFrame = mF;
		if (inst == null)
			inst = new Manager();
		return inst;
	}

	private Manager() {
		String infoMessage = null;
		try {
			readStudent(studentFile);
		} catch (FileNotFoundException e) {
			infoMessage = "Es gibt keine Datei(en) namens: Studenten.txt";
		}
		try {
			readProf(profFile);
		} catch (FileNotFoundException e) {
			if (infoMessage == null)
				infoMessage = "Es gibt keine Datei(en) namens: Professoren.txt";
			else
				infoMessage = infoMessage + ", Professoren.txt";
		}
		try {
			readModule(moduleFile);
		} catch (FileNotFoundException e) {
			if (infoMessage == null)
				infoMessage = "Es gibt keine Datei namens: Module.txt\n Die Tabelle wird leer gelassen!";
			else
				infoMessage = infoMessage + ", Module.txt\n Entsprechende Tabellen werden leer gelassen!";
		}
		if (infoMessage != null)
			JOptionPane.showMessageDialog(null, infoMessage, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("resource")
	public void readStudent(String fileName) throws FileNotFoundException {
		String outString = "";
		ArrayList<String> lines = new ArrayList<String>();
		Scanner sc = new Scanner(new FileInputStream(new File(fileName)), "UTF-8");
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] data;
		for (String info : lines) {
			data = info.split("\t");
			if (getStudent(data[5]) == null) {
				students.add(new Student(data));
				mainFrame.studentModel.addRow(data);
			} else {
				if (!outString.isEmpty())
					outString = outString + ", ";
				outString = outString + data[5];
			}
		}
		mainFrame.resizeColumnWidth(mainFrame.studentTable);
		if (!outString.isEmpty())
			JOptionPane.showMessageDialog(null,
					"Daten von Student(en) mit Matrikelnummer(n) " + outString
							+ " waren schon vorhanden. Sie wurden daher nicht ersetzt!",
					"Information", JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("resource")
	public void readProf(String fileName) throws FileNotFoundException {
		String outString = "";
		ArrayList<String> lines = new ArrayList<String>();
		Scanner sc = new Scanner(new FileInputStream(new File(fileName)), "UTF-8");
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] data;
		for (String info : lines) {
			data = info.split("\t");
			if (getProf(data[5]) == null) {
				profs.add(new Professor(data));
				mainFrame.profModel.addRow(data);
			} else {
				if (!outString.isEmpty())
					outString = outString + ", ";
				outString = outString + data[5];
			}
		}
		mainFrame.resizeColumnWidth(mainFrame.profTable);
		if (!outString.isEmpty())
			JOptionPane.showMessageDialog(null,
					"Daten von Professor(en) mit Kürzel(n) " + outString
							+ " waren schon vorhanden. Sie wurden daher nicht ersetzt!",
					"Information", JOptionPane.INFORMATION_MESSAGE);
	}

	@SuppressWarnings("resource")
	public void readModule(String fileName) throws FileNotFoundException {
		String outString = "";
		ArrayList<String> lines = new ArrayList<String>();
		Module m;
		Scanner sc = new Scanner(new FileInputStream(new File(fileName)), "UTF-8");
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		String[] data;
		for (String info : lines) {
			data = info.split("\t");
			if (getModule(data[1]) == null) {
				m = new Module(data, this);
				if (getProf(m.profID) == null) {
					JOptionPane.showMessageDialog(null,
							"Der Professor mit Kürzel " + m.profID + " existiert nicht. Das Modul mit Modulnummer "
									+ m.nr + " konnte nicht erstellt werden.",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					modules.add(m);
					mainFrame.moduleModel.addRow(m.data);
				}
			} else {
				if (!outString.isEmpty())
					outString = outString + ", ";
				outString = outString + data[1];
			}
		}
		mainFrame.resizeColumnWidth(mainFrame.moduleTable);
		if (!outString.isEmpty())
			JOptionPane.showMessageDialog(null,
					"Daten von Modul(en) mit Modulnummer(n) " + outString
							+ " waren schon vorhanden. Sie wurden daher nicht ersetzt!",
					"Information", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * H2: Search for students with this name
	 * @param name The name to search for
	 * @return A list of students containing this name
	 */
	public ArrayList<Student> searchStudentName(String name) {
		ArrayList<Student> result = students.stream()
				.filter(x -> {
					if (!mainFrame.searchCaseSensitive.isSelected())
						return x.name.toLowerCase().contains(name.toLowerCase()) || 
							   x.famName.toLowerCase().contains(name.toLowerCase());
					else
						return x.name.contains(name) || x.famName.contains(name);
					})
				.collect(Collectors.toCollection(ArrayList::new));
		if (result.isEmpty())
			JOptionPane.showMessageDialog(mainFrame, "Es gibt keinen Studenten mit Teil - Namen: "+name);
		return result;
	}

	/**
	 * H2: Search for professors with this name
	 * @param name The name to search for
	 * @return A list of professors containing this name
	 */
	public ArrayList<Professor> searchProfName(String name) {
		ArrayList<Professor> result = profs.stream()
				.filter(x -> {
					if (!mainFrame.searchCaseSensitive.isSelected())
						return x.name.toLowerCase().contains(name.toLowerCase()) || 
							   x.famName.toLowerCase().contains(name.toLowerCase());
					else
						return x.name.contains(name) || x.famName.contains(name);
					})
				.collect(Collectors.toCollection(ArrayList::new));
		if (result.isEmpty())
			JOptionPane.showMessageDialog(mainFrame, "Es gibt keinen Professor mit Teil - Namen: "+name);
		return result;
	}

	/**
	 * H2: Search for modules with this name
	 * @param name The name to search for
	 * @return A list of modules containing this name
	 */
	public ArrayList<Module> searchModuleName(String name) {
		ArrayList<Module> result = modules.stream()
				.filter(x -> {
					if (!mainFrame.searchCaseSensitive.isSelected())
						return x.name.toLowerCase().contains(name.toLowerCase());
					else
						return x.name.contains(name);
					})
				.collect(Collectors.toCollection(ArrayList::new));
		if (result.isEmpty())
			JOptionPane.showMessageDialog(mainFrame, "Es gibt keinen Module mit Teil - Namen: "+name);
		return result;
	}

	public Student getStudent(String studID) {
		for (Student s : students)
			if (s.studID.equals(studID))
				return s;
		return null;
	}

	public Professor getProf(String staffID) {
		for (Professor p : profs)
			if (p.staffID.equals(staffID))
				return p;
		return null;
	}

	public Module getModule(String nr) {
		for (Module m : modules)
			if (m.nr.equals(nr))
				return m;
		return null;
	}

	/**
	 * 
	 * @param list
	 */
	public void showStudents(ArrayList<Student> list) {
		while (mainFrame.studentModel.getRowCount() > 0)
			mainFrame.studentModel.removeRow(0);
		
		for (Student stud : list) {
			Vector<String> vec = new Vector<String>();
			vec.add(stud.name);
			vec.add(stud.famName);
			vec.add(stud.street);
			vec.add(stud.zip);
			vec.add(stud.city);
			vec.add(stud.studID);
			vec.add(stud.course);
			mainFrame.studentModel.addRow(vec);
		}
				
		mainFrame.resizeColumnWidth(mainFrame.studentTable);
	}

	/**
	 * 
	 * @param list
	 */
	public void showProfs(ArrayList<Professor> list) {
		while (mainFrame.profModel.getRowCount() > 0)
			mainFrame.profModel.removeRow(0);
		
		for (Professor prof : list) {
			Vector<String> vec = new Vector<String>();
			vec.add(prof.name);
			vec.add(prof.famName);
			vec.add(prof.street);
			vec.add(prof.zip);
			vec.add(prof.city);
			vec.add(prof.staffID);
			vec.add(prof.field);
			mainFrame.profModel.addRow(vec);
		}
		
		mainFrame.resizeColumnWidth(mainFrame.profTable);
	}

	/**
	 * 
	 * @param list
	 */
	public void showModules(ArrayList<Module> list) {
		while (mainFrame.moduleModel.getRowCount() > 0)
			mainFrame.moduleModel.removeRow(0);
		
		for (Module mod : list) {
			Vector<String> vec = new Vector<String>();
			vec.add(mod.name);
			vec.add(mod.nr);
			vec.add(mod.profID);
			vec.add(mod.semester);
			vec.add(mod.getParticipants());
			mainFrame.moduleModel.addRow(vec);
		}
		
		mainFrame.resizeColumnWidth(mainFrame.moduleTable);
	}

	public ArrayList<Module> getStudentModules(String studID) {
		ArrayList<Module> out = new ArrayList<Module>();
		for (Module m : modules) {
			if (m.participants.contains(studID))
				out.add(m);
		}
		return out;
	}

	public ArrayList<Module> getProfModules(String staffID) {
		ArrayList<Module> out = new ArrayList<Module>();
		for (Module m : modules) {
			if (m.profID.equals(staffID))
				out.add(m);
		}
		return out;
	}

	public void writeStudent(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < students.size(); i++) {
			bw.write(students.get(i).getString());
			if (i < students.size() - 1)
				bw.newLine();
		}
		bw.close();
	}

	public void writeProf(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < profs.size(); i++) {
			bw.write(profs.get(i).getString());
			if (i < profs.size() - 1)
				bw.newLine();
		}
		bw.close();
	}

	public void writeModule(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < modules.size(); i++) {
			bw.write(modules.get(i).getString());
			if (i < modules.size() - 1)
				bw.newLine();
		}
		bw.close();
	}

	public void writeJoin(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < mainFrame.joinModel.getRowCount(); i++) {
			String row = "";
			for (int j = 0; j < mainFrame.joinModel.getColumnCount(); j++) {
				row = row + mainFrame.joinModel.getValueAt(i, j);
				if (j < mainFrame.joinModel.getColumnCount() - 1)
					row = row + "\t";
			}
			bw.write(row);
			if (i < mainFrame.joinModel.getRowCount() - 1)
				bw.newLine();
		}
		bw.close();
	}
}