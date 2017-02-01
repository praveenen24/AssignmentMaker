package GUI.View;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import org.gnu.glpk.GLPKConstants;

import GUI.Model.Model;
import Main.AssignmentObject;
import Main.Bound;
import Main.Constraint;
import Main.ObjectList;

public class MainFrame extends JFrame {
	
	private JTabbedPane tabs;

	public MainFrame() {
		super("Assignment Maker");
		tabs = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
		setupTabs(initialize());
		add(tabs);
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	public void setupTabs(Model m) {
		tabs.add("Problem Setup", new ProblemSetupPanel(m));
	}
	
	public Model initialize() {
		String list1Name = JOptionPane.showInputDialog("Name of List 1?");
		String list2Name = JOptionPane.showInputDialog("Name of List 2?");
		String numbList1 = JOptionPane.showInputDialog("Number of " + list1Name + "?");
		String numbList2 = JOptionPane.showInputDialog("Number of " + list2Name + "?");
		String min = JOptionPane.showInputDialog("Minimum # of " + list2Name + " a " + list1Name + " must be assigned to?");
		String max = JOptionPane.showInputDialog("Maximum # of " + list2Name + " a " + list1Name + " must be assigned to?");
		ObjectList list1 = createObjects(list1Name, Integer.parseInt(numbList1));
		ObjectList list2 = createObjects(list2Name, Integer.parseInt(numbList2));
		ArrayList<Constraint> constraints = createBoundConstraints(Integer.parseInt(numbList1), Integer.parseInt(min), Integer.parseInt(max));
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

	public ArrayList<Constraint> createBoundConstraints(int n, int lowerBound, int upperBound) {
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		int index = 1;
		for (int i = 1; i < n+1; i++) {
			Constraint c1;
			if (lowerBound == upperBound) {
				c1 = new Constraint("c"+i, new Bound(GLPKConstants.GLP_FX, lowerBound, upperBound));
			} else {
				c1 = new Constraint("c"+i, new Bound(GLPKConstants.GLP_DB, lowerBound, upperBound));
			}
			for (int j = 1; j < n+1; j++) {
				c1.addValue(index, 1.0);
				index++;
			}
			constraints.add(c1);
		}
		return constraints;
	}

	
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
	}
	
}
