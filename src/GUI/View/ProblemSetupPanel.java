package GUI.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.gnu.glpk.GLPKConstants;

import GUI.Model.Model;
import Main.AssignmentObject;
import Main.Bound;
import Main.Constraint;
import Main.HorizontalConstraint;
import Main.LinearProblem;
import Main.ObjectList;
import Main.ScoreType;
import Main.VerticalConstraint;

public class ProblemSetupPanel extends JPanel implements Observer {

	private JList<AssignmentObject> objectList1;
	private JList<AssignmentObject> objectList2;
	private DefaultListModel<AssignmentObject> model1;
	private DefaultListModel<AssignmentObject> model2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JTextField listName1;
	private JTextField listName2;
	private JComboBox comboBox;
	private Model model;
	private JTextArea solution;
	private JButton run;
	private JTextField score;
	private JLabel scoreLabel;
	private JButton saveButton;
	private JButton addList1Button;
	private JButton removeList1Button;
	private JButton addList2Button;
	private JButton removeList2Button;

	public ProblemSetupPanel(Model model) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		model1 = new DefaultListModel<AssignmentObject>(); 
		model2 = new DefaultListModel<AssignmentObject>();
		this.model = model;
		setupModel(this.model);
		objectList1 = new JList<>(model1);
		objectList2 = new JList<>(model2);
		score = new JTextField();
		solution = new JTextArea("Click Solve to Generate Solution ... ");
		scrollPane1 = new JScrollPane(objectList1);
		scrollPane2 = new JScrollPane(objectList2);
		scrollPane3 = new JScrollPane(solution);
		objectList1.addListSelectionListener(listListener);
		objectList2.addListSelectionListener(listListener);
		listName1 = new JTextField(model.getList1Name());
		listName1.setBackground(null);
		listName2 = new JTextField(model.getList2Name());
		listName2.setBackground(null);
		run = new JButton("SOLVE!");
		run.addActionListener(solveListener);
		scoreLabel = new JLabel("Score");
		comboBox = new JComboBox(new String[]{"Minimize", "Maximize"});
		addList1Button = new JButton("+");
		addList2Button = new JButton("+");
		removeList1Button = new JButton("-");
		removeList2Button = new JButton("-");
		saveButton = new JButton("Save");
		add(listName2); add(listName1);
		add(scrollPane1); add(scrollPane2);
		add(scrollPane3); add(run); add(score);
		add(scoreLabel); add(comboBox);
		add(saveButton); add(addList1Button);
		add(removeList1Button); add(addList2Button);
		add(removeList2Button);
		addList1Button.addActionListener(addList1Listener);
		addList2Button.addActionListener(addList2Listener);
		removeList1Button.addActionListener(removeList1Listener);
		removeList2Button.addActionListener(removeList2Listener);
		saveButton.addActionListener(saveListener);
		score.addActionListener(saveListener);
		setupConstraints(springLayout);
	}

	public void setupConstraints(SpringLayout springLayout) {
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane1, 9, SpringLayout.SOUTH, listName1);
		springLayout.putConstraint(SpringLayout.NORTH, removeList1Button, 0, SpringLayout.NORTH, run);
		springLayout.putConstraint(SpringLayout.WEST, removeList1Button, 42, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, removeList1Button, -6, SpringLayout.WEST, addList1Button);
		springLayout.putConstraint(SpringLayout.NORTH, addList1Button, 6, SpringLayout.SOUTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.WEST, addList1Button, 82, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, addList1Button, 0, SpringLayout.EAST, scrollPane1);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane1, -35, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, saveButton, 42, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, addList2Button, -40, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, addList2Button, 0, SpringLayout.SOUTH, run);
		springLayout.putConstraint(SpringLayout.EAST, addList2Button, -6, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, removeList2Button, -40, SpringLayout.WEST, addList2Button);
		springLayout.putConstraint(SpringLayout.SOUTH, removeList2Button, 0, SpringLayout.SOUTH, run);
		springLayout.putConstraint(SpringLayout.EAST, removeList2Button, -6, SpringLayout.WEST, addList2Button);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 256, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -6, SpringLayout.WEST, run);
		springLayout.putConstraint(SpringLayout.NORTH, scoreLabel, 47, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, listName1, 0, SpringLayout.NORTH, listName2);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane2, 9, SpringLayout.SOUTH, listName2);
		springLayout.putConstraint(SpringLayout.WEST, listName1, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, listName2, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, listName2, 134, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane3, 264, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane3, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, run, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, run, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, scoreLabel, -152, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -2, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, score, -92, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane3, 77, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, score, -9, SpringLayout.NORTH, scrollPane3);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane3, -6, SpringLayout.NORTH, run);
		springLayout.putConstraint(SpringLayout.WEST, score, 6, SpringLayout.EAST, scoreLabel);
		springLayout.putConstraint(SpringLayout.WEST, saveButton, 6, SpringLayout.EAST, score);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane2, 134, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane1, 116, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane2, 250, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane1, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane2, 0, SpringLayout.SOUTH, scrollPane3);
	}

	private void setupModel(Model m) {
		for (AssignmentObject a : m.getObjectList1()) {
			model1.addElement(a);
		}
		for (AssignmentObject a : m.getObjectList2()) {
			model2.addElement(a);
		} 
	}
	
	public List<String> validateData() {
		List<String> errors = new ArrayList<String>();
		for (AssignmentObject o1 : model.getObjectList1()) {
			HashSet<Double> set = new HashSet<Double>();
			for (AssignmentObject o2 : model.getObjectList2()) {
				Double value = model.getObjectiveValues().get(o1.getName()+o2.getName());
				if (Math.floor(value) != value || value < 1 || value > model.getObjectList2().size() || !set.add(value)) {
					errors.add(o1.getName()+o2.getName());
					model.getObjectiveValues().put(o1.getName()+o2.getName(), 0.0);
				}
			}
		}
		return errors;
	}

	public ActionListener solveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (model.getScoreRestriction().isContinuous()) {
				List<String> errors = validateData();
				if (!errors.isEmpty()) {
					JTextArea area = new JTextArea();
					area.setEditable(false);
					for (String s : errors) {
						area.append(s + "\n");
					}
					JPanel panel = new JPanel(new GridLayout(2,1));
					panel.add(new JLabel("The Following Combinations did not Meet the Score Restrictions and Have Been Set to 0"));
					panel.add(new JScrollPane(area));
					JOptionPane.showMessageDialog(null, panel, "Score Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			ArrayList<Constraint> constraints = new ArrayList<Constraint>();
			for (Constraint c : model.getConstraints()) {
				if (c instanceof HorizontalConstraint) {
					constraints.addAll(createHorizontalConstraints(c.getName(), model.getObjectList1().size(), model.getObjectList2().size(), 
							c.getBounds().getLowerBound(), c.getBounds().getUpperBound()));
				} else if (c instanceof VerticalConstraint) {
					constraints.addAll(createVerticalConstraints(c.getName(), model.getObjectList1().size(), model.getObjectList2().size(), 
							c.getBounds().getLowerBound(), c.getBounds().getUpperBound()));
				} else {
					constraints.add(c);
				}
			}
			LinearProblem lp;
			if (comboBox.getSelectedItem().toString().equals("Minimize")) {
				lp = new LinearProblem("LP", model.getObjectList1(), model.getObjectList2(), constraints, GLPKConstants.GLP_MIN, model.getObjectiveValues());
			} else if (comboBox.getSelectedItem().toString().equals("Maximize")) {
				lp = new LinearProblem("LP", model.getObjectList1(), model.getObjectList2(), model.getConstraints(), GLPKConstants.GLP_MAX, model.getObjectiveValues());
			} else {
				JOptionPane.showMessageDialog(null, "No Solution Found", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			solution.setText(lp.getStringSolution());
		}
	};
	
	private ListSelectionListener listListener = new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int index1 = objectList1.getSelectedIndex();
			int index2 = objectList2.getSelectedIndex();
			if (index1 != -1 && index2 != -1) {
				String key = objectList1.getSelectedValue().getName()+objectList2.getSelectedValue().getName();
				if (model.getObjectiveValue(key) != null) {
					score.setText(Double.toString(model.getObjectiveValue(key)));
				} else {
					score.setText("");
				}
				score.requestFocus();
			}
		}
	};
	
	public ActionListener saveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			double d;
			try {
				d = Double.parseDouble(score.getText());
				String key = objectList1.getSelectedValue().getName() + objectList2.getSelectedValue().getName();
				if (model.getScoreRestriction().getType().equals(ScoreType.POSITIVE)) {
					if (d < 0) {
						JOptionPane.showMessageDialog(null, "Scores must be positive", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else if (model.getScoreRestriction().getType().equals(ScoreType.NEGATIVE)) {
					if (d >= 0) {
						JOptionPane.showMessageDialog(null, "Scores must be negative", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				model.putObjectiveValue(key, d);
			} catch (Exception ex) {
				score.setText("Invalid Entry");
			}
		}
		
	};
	
	public ActionListener addList1Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = JOptionPane.showInputDialog("Enter name of new object");
			if (name != null) {
				AssignmentObject object = new AssignmentObject(name);
				model.addToList1(object);
				model1.addElement(object);
			}
		}
	};
	
	public ActionListener addList2Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = JOptionPane.showInputDialog("Enter name of new object");
			if (name != null) {
				AssignmentObject object = new AssignmentObject(name);
				model.addToList2(object);
				model2.addElement(object);
			}
		}
	};
	
	public ActionListener removeList1Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			List<AssignmentObject> objects = objectList1.getSelectedValuesList();
			if (!objects.isEmpty()) {
				for (AssignmentObject o : objects) {
					model.removeFromList1(o);
					model1.removeElement(o);
				}
			} else {
				JOptionPane.showMessageDialog(null, "No objects selected.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	};
	
	public ActionListener removeList2Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			List<AssignmentObject> objects = objectList2.getSelectedValuesList();
			if (!objects.isEmpty()) {
				for (AssignmentObject o : objects) {
					model.removeFromList2(o);
					model2.removeElement(o);
				}
			} else {
				JOptionPane.showMessageDialog(null, "No objects selected.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	};

	public ArrayList<Constraint> createHorizontalConstraints(String name, int rows, int columns, int lowerBound, int upperBound) {
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		int index = 1;
		for (int i = 1; i < rows+1; i++) {
			Constraint c1;
			if (lowerBound == upperBound) {
				c1 = new Constraint(name+i, new Bound(GLPKConstants.GLP_FX, lowerBound, upperBound));
			} else {
				c1 = new Constraint(name+i, new Bound(GLPKConstants.GLP_DB, lowerBound, upperBound));
			}
			for (int j = 1; j < columns+1; j++) {
				c1.addValue(index, 1.0);
				index++;
			}
			constraints.add(c1);
		}
		return constraints;
	}
	
	public ArrayList<Constraint> createVerticalConstraints(String name, int rows, int columns, int lowerBound, int upperBound) {
		ArrayList<Constraint> constraints = new ArrayList<Constraint>();
		for (int i = 1; i < columns+1; i++) {
			int index = i;
			Constraint c1;
			if (lowerBound == upperBound) {
				c1 = new Constraint(name+i, new Bound(GLPKConstants.GLP_FX, lowerBound, upperBound));
			} else {
				c1 = new Constraint(name+i, new Bound(GLPKConstants.GLP_DB, lowerBound, upperBound));
			}
			for (int j = 1; j < rows+1; j++) {
				c1.addValue(index, 1.0);
				index += columns;
			}
			constraints.add(c1);
		}
		return constraints;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Model model = (Model) o;
		if (arg.equals("List1")) {
			ObjectList list1 = model.getObjectList1();
			model1.clear();
			for (AssignmentObject o1 : list1) {
				model1.addElement(o1);
			}
		} else if (arg.equals("List2")) {
			ObjectList list2 = model.getObjectList2();
			model2.clear();
			for (AssignmentObject o2 : list2) {
				model2.addElement(o2);
			}
		}
	}
}
