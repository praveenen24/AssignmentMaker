package GUI.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.gnu.glpk.GLPKConstants;

import Excel.SpreadSheetReader;
import Excel.SpreadSheetWriter;
import GUI.Model.Model;
import Main.AssignmentObject;
import Main.Bound;
import Main.Constraint;
import Main.ObjectList;

public class MainFrame extends JFrame {

	private JTabbedPane tabs;
	private Model model;
	private ProblemSetupPanel panel;

	public MainFrame() {
		super("Assignment Maker");
		String list1Name = JOptionPane.showInputDialog(this, "Name of Object List 1 (Defaulted to List1)", "List 1 Name", JOptionPane.QUESTION_MESSAGE);
		String list2Name = JOptionPane.showInputDialog(this, "Name of Object List 2 (Defaulted to List2)", "List 2 Name", JOptionPane.QUESTION_MESSAGE);
		tabs = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
		setupMenuBar();
		if (list1Name == null || list1Name.trim().equals("")) list1Name = "List1";
		if (list2Name == null || list2Name.trim().equals("")) list2Name = "List2";
		model = new Model(list1Name, list2Name);
		setupTabs(model);
		add(tabs);
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	private void setupMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadData = new JMenuItem("Load Data");
		JMenuItem saveData = new JMenuItem("Save Data");
		JMenuItem saveResults = new JMenuItem("Save Results");
		saveData.addActionListener(saveDataListener);
		loadData.addActionListener(loadDataListener);
		saveResults.addActionListener(saveResultsListener);
		saveResults.setToolTipText("Save the Contents of the Solution Panel");
		fileMenu.add(saveData);
		fileMenu.add(loadData);
		fileMenu.add(saveResults);
		bar.add(fileMenu);
		setJMenuBar(bar);
	}

	public void setupTabs(Model m) {
		panel = new ProblemSetupPanel(m);
		m.addObserver(panel);
		ConstraintPanel constraints = new ConstraintPanel(m);
		tabs.add("Problem Setup", panel);
		tabs.add("Restrictions", constraints);
	}

	public Model initialize() {
		String list1Name = JOptionPane.showInputDialog("Name of List 1?");
		String list2Name = JOptionPane.showInputDialog("Name of List 2?");
		String numbList1 = JOptionPane.showInputDialog("Number of " + list1Name + "?");
		String numbList2 = JOptionPane.showInputDialog("Number of " + list2Name + "?");
		String rowMin = JOptionPane.showInputDialog("Minimum # of " + list2Name + " a " + list1Name + " must be assigned to?");
		String rowMax = JOptionPane.showInputDialog("Maximum # of " + list2Name + " a " + list1Name + " must be assigned to?");
		String colMin = JOptionPane.showInputDialog("Minimum # of " + list1Name + " a " + list2Name + " must have?");
		String colMax = JOptionPane.showInputDialog("Maximum # of " + list1Name + " a " + list2Name + " must have?");

		ObjectList list1 = createObjects(list1Name, Integer.parseInt(numbList1));
		ObjectList list2 = createObjects(list2Name, Integer.parseInt(numbList2));
		ArrayList<Constraint> constraints = createHorizontalConstraints(Integer.parseInt(numbList1), Integer.parseInt(numbList2),
				Integer.parseInt(rowMin), Integer.parseInt(rowMax));
		constraints.addAll(createVerticalConstraints(Integer.parseInt(numbList1), Integer.parseInt(numbList2),
				Integer.parseInt(colMin), Integer.parseInt(colMax)));
		Model model = new Model(list1, list2, constraints);
		return model;
	}

	public ObjectList createObjects(String listName, int n) {
		ObjectList objects = new ObjectList(listName);
		for (int i = 1; i < n+1; i++) {
			AssignmentObject a = new AssignmentObject(listName + i);
			objects.add(a);
		}
		return objects;
	}

	public ArrayList<Constraint> createHorizontalConstraints(int rows, int columns, int lowerBound, int upperBound) {
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		int index = 1;
		for (int i = 1; i < rows+1; i++) {
			Constraint c1;
			if (lowerBound == upperBound) {
				c1 = new Constraint("c"+i, new Bound(GLPKConstants.GLP_FX, lowerBound, upperBound));
			} else {
				c1 = new Constraint("c"+i, new Bound(GLPKConstants.GLP_DB, lowerBound, upperBound));
			}
			for (int j = 1; j < columns+1; j++) {
				c1.addValue(index, 1.0);
				index++;
			}
			constraints.add(c1);
		}
		return constraints;
	}

	public ArrayList<Constraint> createVerticalConstraints(int rows, int columns, int lowerBound, int upperBound) {
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		for (int i = 1; i < columns+1; i++) {
			int index = i;
			Constraint c1;
			if (lowerBound == upperBound) {
				c1 = new Constraint("c"+i, new Bound(GLPKConstants.GLP_FX, lowerBound, upperBound));
			} else {
				c1 = new Constraint("c"+i, new Bound(GLPKConstants.GLP_DB, lowerBound, upperBound));
			}
			for (int j = 1; j < rows+1; j++) {
				c1.addValue(index, 1.0);
				index += columns;
			}
			constraints.add(c1);
		}
		return constraints;
	}

	private ActionListener saveDataListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save Data");	
			int choice = chooser.showSaveDialog(null);
			if (choice == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();

				SpreadSheetWriter writer = new SpreadSheetWriter(file, model);
				writer.save();

			}
		}
	};

	private ActionListener saveResultsListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save Data");	
			int choice = chooser.showSaveDialog(null);
			if (choice == JFileChooser.APPROVE_OPTION) {
				File file = new File(chooser.getSelectedFile() + ".txt");
				try {
					JTextArea solution = panel.getSolution();
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					solution.write(writer);
					writer.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Unfortunately There Was An Error. Please Try Again.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	};

	private ActionListener loadDataListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int option = chooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				if (file.getName().toLowerCase().endsWith(".xlsx")) {
					SpreadSheetReader reader = new SpreadSheetReader(file, model);
					try {
						List<String> errors = reader.loadData();
						model.setObjectList1(reader.getList1());
						model.setObjectList2(reader.getList2());
						model.setObjectiveValues(reader.getObjectiveValues());
						if (!errors.isEmpty()) {
							JTextArea area = new JTextArea(8,25);
							area.setEditable(false);
							for (String s : errors) {
								area.append(s + "\n");
							}
							JScrollPane scrollPane = new JScrollPane(area);
							JOptionPane.showMessageDialog(null, scrollPane, "Restriction Violations that were Reset to 0", JOptionPane.WARNING_MESSAGE);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Loading Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect File Format. Must be Excel File (.xlsx)", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	};

	private ActionListener quickSetupListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String list1Name = JOptionPane.showInputDialog("Name of List 1?");
			String list2Name = JOptionPane.showInputDialog("Name of List 2?");
			JPanel panel = new JPanel(new GridLayout(0, 2));
			panel.add(new JLabel("Are you going to make assignments based on a score?"));
			panel.add(new JCheckBox("Assignments Based on Score?"));
			JOptionPane.showMessageDialog(null, panel);
			String numbList1 = JOptionPane.showInputDialog("Number of " + list1Name + "?");
			String numbList2 = JOptionPane.showInputDialog("Number of " + list2Name + "?");
			String rowMin = JOptionPane.showInputDialog("Minimum # of " + list2Name + " a " + list1Name + " must be assigned to?");
			String rowMax = JOptionPane.showInputDialog("Maximum # of " + list2Name + " a " + list1Name + " must be assigned to?");
			String colMin = JOptionPane.showInputDialog("Minimum # of " + list1Name + " a " + list2Name + " must have?");
			String colMax = JOptionPane.showInputDialog("Maximum # of " + list1Name + " a " + list2Name + " must have?");

			ObjectList list1 = createObjects(list1Name, Integer.parseInt(numbList1));
			ObjectList list2 = createObjects(list2Name, Integer.parseInt(numbList2));
			ArrayList<Constraint> constraints = createHorizontalConstraints(Integer.parseInt(numbList1), Integer.parseInt(numbList2),
					Integer.parseInt(rowMin), Integer.parseInt(rowMax));
			constraints.addAll(createVerticalConstraints(Integer.parseInt(numbList1), Integer.parseInt(numbList2),
					Integer.parseInt(colMin), Integer.parseInt(colMax)));
			model.setObjectList1(list1);
			model.setObjectList2(list2);
		}
	};

	public static void main(String[] args) {
		try {
			MainFrame f = new MainFrame();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An Unexpected Error Occurred. Please Verify and Try Again", "ERROR", JOptionPane.ERROR);
		}
	}

}
