package GUI.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

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
	
	public MainFrame() {
		super("Assignment Maker");
		tabs = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
		setupMenuBar();
		//model = initialize();
		model = new Model("List1", "List2");
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
		saveData.addActionListener(saveDataListener);
		loadData.addActionListener(loadDataListener);
		fileMenu.add(saveData);
		fileMenu.add(loadData);
		bar.add(fileMenu);
		setJMenuBar(bar);
	}
	
	public void setupTabs(Model m) {
		ProblemSetupPanel panel = new ProblemSetupPanel(m);
		m.addObserver(panel);
		tabs.add("Problem Setup", panel);
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
			String fileName = JOptionPane.showInputDialog("Enter file name");
			SpreadSheetWriter writer = new SpreadSheetWriter(fileName, model);
			writer.saveData();
		}
	};
	
	private ActionListener loadDataListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int option = chooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				SpreadSheetReader reader = new SpreadSheetReader(file);
				try {
					reader.readData();
					model.setObjectList1(reader.getList1());
					model.setObjectList2(reader.getList2());
					model.setObjectiveValues(reader.getObjectiveValues());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
	};
	
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
	}
	
}
