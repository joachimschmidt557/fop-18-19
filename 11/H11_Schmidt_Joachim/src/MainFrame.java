import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private final int textFieldSize = 30;
	public final int[] size = { 1280, 600 };

	JTextField searchField = new JTextField(8);
	JButton searchButton = new JButton("Suche");
	JRadioButton searchStudentRadioButton = new JRadioButton("Student");
	JRadioButton searchProfRadioButton = new JRadioButton("Professor");
	JRadioButton searchModuleRadioButton = new JRadioButton("Modul");
	ButtonGroup searchButtonGroup = new ButtonGroup();
	JButton resetButton = new JButton("Alles anzeigen");
	JCheckBox searchCaseSensitive = new JCheckBox("Case-sensitive");

	JButton addButton = new JButton("Hinzufügen");
	JRadioButton addStudentRadioButton = new JRadioButton("Student");
	JRadioButton addProfRadioButton = new JRadioButton("Professor");
	JRadioButton addModuleRadioButton = new JRadioButton("Modul");
	ButtonGroup addButtonGroup = new ButtonGroup();

	// Person Fields
	JLabel nameLabel = new JLabel("Vorname");
	JTextField nameField = new JTextField(textFieldSize);
	JLabel famNameLabel = new JLabel("Nachname");
	JTextField famNameField = new JTextField(textFieldSize);
	JLabel streetLabel = new JLabel("Straße, Nr");
	JTextField streetField = new JTextField(textFieldSize);
	JLabel zipLabel = new JLabel("PLZ");
	JTextField zipField = new JTextField(textFieldSize);
	JLabel cityLabel = new JLabel("Ort");
	JTextField cityField = new JTextField(textFieldSize);
	ArrayList<JLabel> personLabels = new ArrayList<JLabel>();
	ArrayList<JTextField> personFields = new ArrayList<JTextField>();

	// Student Fields
	JLabel studIDLabel = new JLabel("Matrikelnummer");
	JTextField studIDField = new JTextField(textFieldSize);
	JLabel courseLabel = new JLabel("Studiengang");
	JTextField courseField = new JTextField(textFieldSize);
	ArrayList<JLabel> studentLabels = new ArrayList<JLabel>();
	ArrayList<JTextField> studentFields = new ArrayList<JTextField>();

	// Professor Fields
	JLabel staffIDLabel = new JLabel("Kürzel");
	JTextField staffIDField = new JTextField(textFieldSize);
	JLabel fieldLabel = new JLabel("Fachgebiet");
	JTextField fieldField = new JTextField(textFieldSize);
	ArrayList<JLabel> profLabels = new ArrayList<JLabel>();
	ArrayList<JTextField> profFields = new ArrayList<JTextField>();

	// Module Fields
	JLabel mNameLabel = new JLabel("Modulname");
	JTextField mNameField = new JTextField(textFieldSize);
	JLabel mNrLabel = new JLabel("Modulnummer");
	JTextField mNrField = new JTextField(textFieldSize);
	JLabel profIDLabel = new JLabel("Professor (Kürzel)");
	JTextField profIDField = new JTextField(textFieldSize);
	JLabel semLabel = new JLabel("Semester");
	JTextField semField = new JTextField(textFieldSize);
	JLabel partLabel = new JLabel("Teilnehmer");
	JTextField partField = new JTextField(textFieldSize);
	ArrayList<JLabel> moduleLabels = new ArrayList<JLabel>();
	ArrayList<JTextField> moduleFields = new ArrayList<JTextField>();

	JTextField deleteField = new JTextField(10);
	JButton deleteButton = new JButton("Löschen");
	JRadioButton deleteStudentRadioButton = new JRadioButton("Student");
	JRadioButton deleteProfRadioButton = new JRadioButton("Professor");
	JRadioButton deleteModuleRadioButton = new JRadioButton("Modul");
	ButtonGroup deleteButtonGroup = new ButtonGroup();
	JButton modifyButton = new JButton("Bearbeiten");

	Color alternateColor = new Color(215,230,255);
    Color whiteColor = Color.WHITE;
    
	JScrollPane studentPanel;
	String[] studentColumns = { "Vorname", "Nachname", "Straße, Nr.", "PLZ", "Ort", "Matrikelnr.", "Studiengang" };
	String[][] studentData = { { "", "", "", "", "", "", "" } };
	DefaultTableModel studentModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}
	};
	JTable studentTable = new JTable(studentModel){
	    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
	        Component returnComp = super.prepareRenderer(renderer, row, column);
	        if (!returnComp.getBackground().equals(getSelectionBackground())){
	            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
	            returnComp .setBackground(bg);
	            bg = null;
	        }
	        return returnComp;
	};};

	JScrollPane profPanel;
	String[] profColumns = { "Vorname", "Nachname", "Straße, Nr.", "PLZ", "Ort", "Kürzel", "Fachgebiet" };
	String[][] profData = { { "", "", "", "", "", "", "" } };
	DefaultTableModel profModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}
	};
	JTable profTable = new JTable(profModel){
	    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
	        Component returnComp = super.prepareRenderer(renderer, row, column);
	        if (!returnComp.getBackground().equals(getSelectionBackground())){
	            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
	            returnComp .setBackground(bg);
	            bg = null;
	        }
	        return returnComp;
	};};

	JScrollPane modulePanel;
	String[] moduleColumns = { "Modulname", "Modulnummer", "Professor", "Semester", "Teilnehmer" };
	String[][] moduleData = { { "", "", "", "", "" } };
	DefaultTableModel moduleModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}
	};
	JTable moduleTable = new JTable(moduleModel){
	    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
	        Component returnComp = super.prepareRenderer(renderer, row, column);
	        if (!returnComp.getBackground().equals(getSelectionBackground())){
	            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
	            returnComp .setBackground(bg);
	            bg = null;
	        }
	        return returnComp;
	};};
	
	JPanel tablePanel = new JPanel();

	JScrollPane joinPanel;
	DefaultTableModel joinModel = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// all cells false
			return false;
		}
	};
	JTable joinTable = new JTable(joinModel){
	    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
	        Component returnComp = super.prepareRenderer(renderer, row, column);
	        if (!returnComp.getBackground().equals(getSelectionBackground())){
	            Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
	            returnComp .setBackground(bg);
	            bg = null;
	        }
	        return returnComp;
	};};
	Border joinBorder;

	JMenuBar menuBar = new JMenuBar();
	JMenu open = new JMenu("Öffnen");
	JMenuItem openStudent = new JMenuItem("Studenten");
	JMenuItem openProf = new JMenuItem("Professoren");
	JMenuItem openModule = new JMenuItem("Module");
	JMenu add = new JMenu("Hinzufügen");
	JMenuItem addStudent = new JMenuItem("Studenten");
	JMenuItem addProf = new JMenuItem("Professoren");
	JMenuItem addModule = new JMenuItem("Module");
	JMenu save = new JMenu("Speichern");
	JMenuItem saveItem = new JMenuItem("Speichern (aktuelle Dateien werden Überschrieben)");
	JMenuItem saveAsItem = new JMenuItem("Speichern unter ...");
	JMenu join = new JMenu("Tabellenjoins");
	JMenuItem joinStudMod = new JMenuItem("Studenten mit Modulen");
	JMenuItem joinProfMod = new JMenuItem("Professoren mit Modulen");
	JMenuItem joinModStud = new JMenuItem("Module mit Studenten");
	JMenuItem joinModProf = new JMenuItem("Module mit Professoren");
	JMenuItem joinModProfStud = new JMenuItem("Module mit Professoren und Studenten");
	JMenuItem joinReset = new JMenuItem("Tabellenjoin ausblenden");
	JMenuItem help = new JMenuItem("Hilfe");

	String joinFile = null;


	public MainFrame(String title) {
		super(title);
		setSize(size[0], size[1]);
		getContentPane().setPreferredSize(new Dimension(size[0], size[1]));
		Border border = BorderFactory.createEtchedBorder();

		Border searchBorder = BorderFactory.createTitledBorder(border, "Suche nach Namen");
		JPanel searchPanel = new JPanel();
		searchPanel.setPreferredSize(new Dimension(size[0] / 3, 60));
		searchPanel.setBorder(searchBorder);
		searchPanel.setLayout(new FlowLayout());

		searchButtonGroup.add(searchStudentRadioButton);
		searchButtonGroup.add(searchProfRadioButton);
		searchButtonGroup.add(searchModuleRadioButton);
		// default selection: Student
		searchStudentRadioButton.setSelected(true);
		searchPanel.add(searchStudentRadioButton);
		searchPanel.add(searchProfRadioButton);
		searchPanel.add(searchModuleRadioButton);

		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		// Added: a case sensitive checkbox
		searchPanel.add(searchCaseSensitive);

		resetButton.setVisible(false);
		searchPanel.add(resetButton);

		Border addBorder = BorderFactory.createTitledBorder(border, "Hinzufügen");
		JPanel addPanel = new JPanel();
		addPanel.setPreferredSize(new Dimension(size[0] / 3, 2 * size[1] / 3));
		addPanel.setBorder(addBorder);
		addPanel.setLayout(new BorderLayout());

		// default selection: Student
		addStudentRadioButton.setSelected(true);
		addButtonGroup.add(addStudentRadioButton);
		addButtonGroup.add(addProfRadioButton);
		addButtonGroup.add(addModuleRadioButton);

		JPanel addButtonPanel = new JPanel();

		addButtonPanel.add(addStudentRadioButton);
		addButtonPanel.add(addProfRadioButton);
		addButtonPanel.add(addModuleRadioButton);
		addButtonPanel.add(addButton);

		JPanel addInputPanel = new JPanel();
		addInputPanel.setPreferredSize(new Dimension(size[0] / 3, 260));
		JScrollPane addScrollPane = new JScrollPane(addInputPanel);
		addScrollPane.setBorder(null);

		// Person
		personLabels.add(nameLabel);
		personFields.add(nameField);
		personLabels.add(famNameLabel);
		personFields.add(famNameField);
		personLabels.add(streetLabel);
		personFields.add(streetField);
		personLabels.add(zipLabel);
		personFields.add(zipField);
		personLabels.add(cityLabel);
		personFields.add(cityField);
		addInputPanel.setLayout(null);
		for (int i = 0; i < personLabels.size(); i++) {
			JLabel l = personLabels.get(i);
			addInputPanel.add(l);
			l.setBounds(50, 35 * i + 15, 120, 30);
			JTextField f = personFields.get(i);
			addInputPanel.add(f);
			f.setBounds(200, 35 * i + 15, 300, 30);
		}

		// Student
		studentLabels.add(studIDLabel);
		studentFields.add(studIDField);
		studentLabels.add(courseLabel);
		studentFields.add(courseField);
		for (int i = 0; i < studentLabels.size(); i++) {
			JLabel l = studentLabels.get(i);
			addInputPanel.add(l);
			l.setBounds(50, 35 * i + 190, 120, 30);
			JTextField f = studentFields.get(i);
			addInputPanel.add(f);
			f.setBounds(200, 35 * i + 190, 300, 30);
		}

		// Professor
		profLabels.add(staffIDLabel);
		profFields.add(staffIDField);
		profLabels.add(fieldLabel);
		profFields.add(fieldField);
		for (int i = 0; i < profLabels.size(); i++) {
			JLabel l = profLabels.get(i);
			addInputPanel.add(l);
			l.setBounds(50, 35 * i + 190, 120, 30);
			JTextField f = profFields.get(i);
			addInputPanel.add(f);
			f.setBounds(200, 35 * i + 190, 300, 30);
		}
		// default selection: Student
		for (JLabel l : profLabels)
			l.setVisible(false);
		for (JTextField f : profFields)
			f.setVisible(false);

		// Module
		moduleLabels.add(mNameLabel);
		moduleFields.add(mNameField);
		moduleLabels.add(mNrLabel);
		moduleFields.add(mNrField);
		moduleLabels.add(profIDLabel);
		moduleFields.add(profIDField);
		moduleLabels.add(semLabel);
		moduleFields.add(semField);
		moduleLabels.add(partLabel);
		moduleFields.add(partField);
		for (int i = 0; i < moduleLabels.size(); i++) {
			JLabel l = moduleLabels.get(i);
			addInputPanel.add(l);
			l.setBounds(50, 35 * i + 15, 120, 30);
			JTextField f = moduleFields.get(i);
			addInputPanel.add(f);
			f.setBounds(200, 35 * i + 15, 300, 30);
		}
		// default selection: Student
		for (JLabel l : moduleLabels)
			l.setVisible(false);
		for (JTextField f : moduleFields)
			f.setVisible(false);

		addPanel.add(addButtonPanel, BorderLayout.NORTH);
		addPanel.add(addScrollPane, BorderLayout.CENTER);

		Border deleteBorder = BorderFactory.createTitledBorder(border, "Bearbeiten mit Matrikelnr/Kürzel/Modulnr");
		JPanel deletePanel = new JPanel();
		deletePanel.setBorder(deleteBorder);
		deletePanel.setLayout(new FlowLayout());

		// default selection: Student
		deleteStudentRadioButton.setSelected(true);
		deleteButtonGroup.add(deleteStudentRadioButton);
		deleteButtonGroup.add(deleteProfRadioButton);
		deleteButtonGroup.add(deleteModuleRadioButton);
		deletePanel.add(deleteStudentRadioButton);
		deletePanel.add(deleteProfRadioButton);
		deletePanel.add(deleteModuleRadioButton);

		deletePanel.add(deleteField);
		deletePanel.add(deleteButton);
		deletePanel.add(modifyButton);

		studentModel.setColumnIdentifiers(studentColumns);
		studentPanel = new JScrollPane(studentTable);
		Border studentBorder = BorderFactory.createTitledBorder(border, "Studenten");
		studentPanel.setBorder(studentBorder);
		studentTable.getParent().addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				if (studentTable.getPreferredSize().width < studentTable.getParent().getWidth()) {
					studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				} else {
					studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
		});
		
		profModel.setColumnIdentifiers(profColumns);
		profPanel = new JScrollPane(profTable);
		Border profBorder = BorderFactory.createTitledBorder(border, "Professoren");
		profPanel.setBorder(profBorder);
		profTable.getParent().addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				if (profTable.getPreferredSize().width < profTable.getParent().getWidth()) {
					profTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				} else {
					profTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
		});

		moduleModel.setColumnIdentifiers(moduleColumns);
		modulePanel = new JScrollPane(moduleTable);
		Border moduleBorder = BorderFactory.createTitledBorder(border, "Module");
		modulePanel.setBorder(moduleBorder);
		moduleTable.getParent().addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				if (moduleTable.getPreferredSize().width < moduleTable.getParent().getWidth()) {
					moduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				} else {
					moduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
		});

		joinPanel = new JScrollPane(joinTable);
		Border joinBorder = BorderFactory.createTitledBorder(border, "Tabellenjoin");
		joinPanel.setBorder(joinBorder);
		joinTable.getParent().addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				if (joinTable.getPreferredSize().width < joinTable.getParent().getWidth()) {
					joinTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				} else {
					joinTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
			}
		});
		// start without any joins
		joinPanel.setPreferredSize(new Dimension(0, 0));

		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BorderLayout());
		actionPanel.add(searchPanel, BorderLayout.NORTH);
		actionPanel.add(addPanel, BorderLayout.CENTER);
		actionPanel.add(deletePanel, BorderLayout.SOUTH);

		tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(0, 1, 5, 5));
		tablePanel.setPreferredSize(new Dimension(size[0] * 2 / 3, size[1]));
		tablePanel.add(studentPanel);
		tablePanel.add(profPanel);
		tablePanel.add(modulePanel);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(actionPanel, BorderLayout.WEST);
		mainPanel.add(tablePanel, BorderLayout.CENTER);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(joinPanel, BorderLayout.SOUTH);

		searchButton.addActionListener(new SearchHandler(this));
		resetButton.addActionListener(new ResetHandler(this));
		addButton.addActionListener(new AddHandler(this));
		deleteButton.addActionListener(new DeleteHandler(this));
		modifyButton.addActionListener(new ModifyHandler(this));

		addStudentRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					addInputPanel.setPreferredSize(new Dimension(size[0] / 3, 260));
					for (JLabel l : personLabels)
						l.setVisible(true);
					for (JTextField f : personFields)
						f.setVisible(true);
					for (JLabel l : studentLabels)
						l.setVisible(true);
					for (JTextField f : studentFields) {
						f.setVisible(true);
						f.setText("");
					}
					for (JLabel l : profLabels)
						l.setVisible(false);
					for (JTextField f : profFields)
						f.setVisible(false);
					for (JLabel l : moduleLabels)
						l.setVisible(false);
					for (JTextField f : moduleFields)
						f.setVisible(false);
				}
			}
		});
		addProfRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				addInputPanel.setPreferredSize(new Dimension(size[0] / 3, 260));
				if (e.getStateChange() == ItemEvent.SELECTED) {
					for (JLabel l : personLabels)
						l.setVisible(true);
					for (JTextField f : personFields)
						f.setVisible(true);
					for (JLabel l : studentLabels)
						l.setVisible(false);
					for (JTextField f : studentFields)
						f.setVisible(false);
					for (JLabel l : profLabels)
						l.setVisible(true);
					for (JTextField f : profFields) {
						f.setVisible(true);
						f.setText("");
					}
					for (JLabel l : moduleLabels)
						l.setVisible(false);
					for (JTextField f : moduleFields)
						f.setVisible(false);
				}
			}
		});
		addModuleRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					addInputPanel.setPreferredSize(new Dimension(size[0] / 3, 188));
					for (JLabel l : personLabels)
						l.setVisible(false);
					for (JTextField f : personFields) {
						f.setVisible(false);
						f.setText("");
					}
					for (JLabel l : studentLabels)
						l.setVisible(false);
					for (JTextField f : studentFields)
						f.setVisible(false);
					for (JLabel l : profLabels)
						l.setVisible(false);
					for (JTextField f : profFields)
						f.setVisible(false);
					for (JLabel l : moduleLabels)
						l.setVisible(true);
					for (JTextField f : moduleFields) {
						f.setVisible(true);
						f.setText("");
					}
				}
			}
		});

		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		open.add(openStudent);
		open.add(openProf);
		open.add(openModule);
		menuBar.add(open);
		add.add(addStudent);
		add.add(addProf);
		add.add(addModule);
		menuBar.add(add);
		save.add(saveItem);
		save.add(saveAsItem);
		menuBar.add(save);
		join.add(joinStudMod);
		join.add(joinProfMod);
		join.add(joinModStud);
		join.add(joinModProf);
		join.add(joinModProfStud);

		joinReset.setVisible(false);
		join.add(joinReset);

		menuBar.add(join);
		menuBar.add(help);

		setJMenuBar(menuBar);
		openStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null,
						"Geben Sie den Namen der Datei an, welche Sie Öffnen möchten.\n Dateiendung nicht vergessen. (z.B. \".txt\")");
				if (name != null) {
					Manager manager = Manager.createManagerInst(null);
					try {
						manager.students = new ArrayList<Student>();
						manager.showStudents(manager.students);
						manager.readStudent(name);
						manager.studentFile = name;
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Es gibt keine Datei namens: " + name, "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		openProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "vor name");
				String name = JOptionPane.showInputDialog(null,
						"Geben Sie den Namen der Datei an, welche Sie Öffnen möchten.\n Dateiendung nicht vergessen. (z.B. \".txt\")");
				JOptionPane.showMessageDialog(null, "nach name");
				if (name != null) {
					Manager manager = Manager.createManagerInst(null);
					try {
						manager.profs = new ArrayList<Professor>();
						JOptionPane.showMessageDialog(null, "vor show");
						manager.showProfs(manager.profs);
						JOptionPane.showMessageDialog(null, "vor read");
						manager.readProf(name);
						JOptionPane.showMessageDialog(null, "nach read");
						manager.profFile = name;
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Es gibt keine Datei namens: " + name, "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		openModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null,
						"Geben Sie den Namen der Datei an, welche Sie Öffnen möchten.\n Dateiendung nicht vergessen. (z.B. \".txt\")");
				if (name != null) {
					Manager manager = Manager.createManagerInst(null);
					try {
						manager.modules = new ArrayList<Module>();
						manager.showModules(manager.modules);
						manager.readModule(name);
						manager.moduleFile = name;
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Es gibt keine Datei namens: " + name, "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		addStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null,
						"Geben Sie den Namen der Datei an, welche Sie hinzufügen möchten.\n Dateiendung nicht vergessen. (z.B. \".txt\")");
				if (name != null) {
					Manager manager = Manager.createManagerInst(null);
					try {
						manager.readStudent(name);
						manager.studentFile = name;
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Es gibt keine Datei namens: " + name, "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		addProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null,
						"Geben Sie den Namen der Datei an, welche Sie hinzufügen möchten.\n Dateiendung nicht vergessen. (z.B. \".txt\")");
				if (name != null) {
					Manager manager = Manager.createManagerInst(null);
					try {
						manager.readProf(name);
						manager.profFile = name;
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Es gibt keine Datei namens: " + name, "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		addModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null,
						"Geben Sie den Namen der Datei an, welche Sie hinzufügen möchten.\n Dateiendung nicht vergessen. (z.B. \".txt\")");
				if (name != null) {
					Manager manager = Manager.createManagerInst(null);
					try {
						manager.readModule(name);
						manager.moduleFile = name;
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Es gibt keine Datei namens: " + name, "Information",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager manager = Manager.createManagerInst(null);
				String question;
				if (joinFile == null)
					question = "Möchten Sie die Dateien \"" + manager.studentFile + "\", \"" + manager.profFile
							+ "\", \"" + manager.moduleFile + "\" wirklich überschreiben?";
				else
					question = "Möchten Sie die Dateien \"" + manager.studentFile + "\", \"" + manager.profFile
							+ "\", \"" + manager.moduleFile + "\", \"" + joinFile + "\" wirklich überschreiben?";
				int reply = JOptionPane.showConfirmDialog(null, question, "Dateien vorhanden!",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					try {
						manager.writeStudent(manager.studentFile);
						manager.writeProf(manager.profFile);
						manager.writeModule(manager.moduleFile);
						if (joinFile != null)
							manager.writeJoin(joinFile);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		saveAsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel sLabel = new JLabel("Studenten");
				JTextField sField = new JTextField(30);
				JLabel pLabel = new JLabel("Professoren");
				JTextField pField = new JTextField(30);
				JLabel mLabel = new JLabel("Module");
				JTextField mField = new JTextField(30);

				JPanel savePanel = new JPanel(new GridLayout(0, 2, 5, 5));
				savePanel.add(sLabel);
				savePanel.add(sField);
				savePanel.add(pLabel);
				savePanel.add(pField);
				savePanel.add(mLabel);
				savePanel.add(mField);
				
				JTextField jField = new JTextField(30);
				if (joinFile != null) {
					JLabel jLabel = new JLabel(joinFile.substring(0, joinFile.length() - 4));
					savePanel.add(jLabel);
					savePanel.add(jField);
				}

				int result = JOptionPane.showConfirmDialog(null, savePanel,
						"Geben Sie hier die gewünschten Dateinamen ein. Bleibt ein Feld leer, wird die entsprechende Tabelle nicht gespeichert!",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					Manager manager = Manager.createManagerInst(null);
					if (!sField.getText().isEmpty()) {
						try {
							manager.writeStudent(sField.getText());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if (!pField.getText().isEmpty()) {
						try {
							manager.writeProf(pField.getText());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if (!mField.getText().isEmpty()) {
						try {
							manager.writeModule(mField.getText());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if (!jField.getText().isEmpty()) {
						try {
							manager.writeJoin(jField.getText());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		joinStudMod.addActionListener(new JoinHandler(this, 1));
		joinProfMod.addActionListener(new JoinHandler(this, 2));
		joinModStud.addActionListener(new JoinHandler(this, 3));
		joinModProf.addActionListener(new JoinHandler(this, 4));
		joinModProfStud.addActionListener(new JoinHandler(this, 5));

		joinReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (joinModel.getRowCount() != 0)
					joinModel.removeRow(0);
				joinPanel.setPreferredSize(new Dimension(0, 0));
				joinReset.setVisible(false);
				joinFile = null;
				pack();
			}
		});

		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Um ein Modul hinzuzufügen, muss der Professor existieren, der dieses Modul anbietet. Falls manche Teilnehmer nicht existieren, werden diese gestrichen und eine Meldung angezeigt.\n\nTabellenjoins sind von den restlichen Tabellen unabhängig und bleiben durch Suchen, Hinzufügen oder Löschen von Studenten, Professoren oder Modulen unberührt.\nUm den Tabellenjoin zu Überschreiben, können Sie ihn erneut aufrufen.",
						"Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = Math.max(table.getColumnName(column).length() * 8, 50); // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
		pack();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MainFrame winFrame = new MainFrame("Campus Management");
		Manager manager = Manager.createManagerInst(winFrame);
	}
}