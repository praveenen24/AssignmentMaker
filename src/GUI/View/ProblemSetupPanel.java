package GUI.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

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
import Main.LinearProblem;

public class ProblemSetupPanel extends JPanel {

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
	private Integer[][] objectiveValues;
	private JButton saveButton;

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
		objectiveValues = new Integer[model.getObjectList1().size()][model.getObjectList2().size()];
		solution = new JTextArea("Click Solve to Generate Solution ... ");
		scrollPane1 = new JScrollPane(objectList1);
		scrollPane2 = new JScrollPane(objectList2);
		scrollPane3 = new JScrollPane(solution);
		
		objectList1.addListSelectionListener(listListener);
		objectList2.addListSelectionListener(listListener);
		
		listName1 = new JTextField(model.getList1Name());
		listName1.setBackground(null); listName1.setBorder(null);
		listName2 = new JTextField(model.getList2Name());
		listName2.setBackground(null); listName2.setBorder(null);
		run = new JButton("SOLVE!");
		run.addActionListener(solveListener);
		scoreLabel = new JLabel("Score");
		comboBox = new JComboBox(new String[]{"Minimize", "Maximize"});
		add(listName2); add(listName1);
		add(scrollPane1); add(scrollPane2);
		add(scrollPane3); add(run); add(score);
		add(scoreLabel); add(comboBox);
		saveButton = new JButton("Save");
		
		add(saveButton);
		saveButton.addActionListener(saveListener);
		score.addActionListener(saveListener);
		setupConstraints(springLayout);
	}

	public void setupConstraints(SpringLayout springLayout) {
		//Labels
		springLayout.putConstraint(SpringLayout.NORTH, listName1, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, listName1, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, listName2, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, listName2, 134, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane3, 264, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane3, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, run, 0, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, run, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.EAST, scoreLabel, -152, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scoreLabel, 2, SpringLayout.NORTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 6, SpringLayout.EAST, scrollPane2);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -2, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -6, SpringLayout.WEST, run);
		springLayout.putConstraint(SpringLayout.EAST, score, -92, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane3, 77, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, score, -9, SpringLayout.NORTH, scrollPane3);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane3, -6, SpringLayout.NORTH, run);
		springLayout.putConstraint(SpringLayout.WEST, score, 6, SpringLayout.EAST, scoreLabel);
		springLayout.putConstraint(SpringLayout.NORTH, saveButton, -3, SpringLayout.NORTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.WEST, saveButton, 6, SpringLayout.EAST, score);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane1, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane1, 20, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane2, 134, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane1, 290, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane1, 116, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane2, 0, SpringLayout.SOUTH, scrollPane1);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane2, 250, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane2, 19, SpringLayout.SOUTH, listName2);
	}

	private void setupModel(Model m) {
		for (AssignmentObject a : m.getObjectList1()) {
			model1.addElement(a);
		}
		for (AssignmentObject a : m.getObjectList2()) {
			model2.addElement(a);
		} 
	}

	public ActionListener solveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			LinearProblem lp;
			if (comboBox.getSelectedItem().toString().equals("Minimize")) {
				lp = new LinearProblem("LP", model.getObjectList1(), model.getObjectList2(), model.getConstraints(), GLPKConstants.GLP_MIN, objectiveValues);
			} else if (comboBox.getSelectedItem().toString().equals("Maximize")) {
				lp = new LinearProblem("LP", model.getObjectList1(), model.getObjectList2(), model.getConstraints(), GLPKConstants.GLP_MAX, objectiveValues);
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
				if (objectiveValues[index1][index2] != null) {
					score.setText(Integer.toString(objectiveValues[index1][index2]));
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
			int s;
			try {
				s = Integer.parseInt(score.getText());
				objectiveValues[objectList1.getSelectedIndex()][objectList2.getSelectedIndex()] = s;
			} catch (Exception ex) {
				score.setText("Invalid Entry");
			}
			
		}
		
	};
}
