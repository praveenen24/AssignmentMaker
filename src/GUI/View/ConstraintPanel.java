package GUI.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import org.gnu.glpk.GLPKConstants;

import GUI.Model.Model;
import Main.Bound;
import Main.Constraint;
import Main.ConstraintBoundType;
import Main.CustomConstraint;
import Main.CustomConstraintBound;
import Main.CustomConstraintVariable;
import Main.HorizontalConstraint;
import Main.ScoreRestriction;
import Main.ScoreType;
import Main.VerticalConstraint;

public class ConstraintPanel extends JPanel {
	private Model model;
	private JList<Constraint> constraintList;
	private DefaultListModel<Constraint> model1;
	private JScrollPane scrollPane1;
	private JLabel constraintLabel;
	private JButton addButton;
	private JButton removeButton;
	private JLabel scoreLabel;
	private JLabel typeLabel;
	private JComboBox<ScoreType> scoreType;
	private JCheckBox continuous;
	
	public ConstraintPanel(Model model) {
		this.model = model;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		model1 = new DefaultListModel<Constraint>(); 
		constraintList = new JList<>(model1);
		scrollPane1 = new JScrollPane(constraintList);
		scoreLabel = new JLabel("Score Restriction");
		typeLabel = new JLabel("Score Type");
		scoreType = new JComboBox<ScoreType>(ScoreType.values());
		continuous = new JCheckBox("Scores based on rankings?");
		scoreType.addActionListener(scoreListener);
		continuous.addActionListener(scoreListener);
		constraintLabel = new JLabel("Restrictions");
		addButton = new JButton("+");
		removeButton = new JButton("-");
		addButton.addActionListener(addListener);
		removeButton.addActionListener(removeListener);
		add(scrollPane1); add(constraintLabel);
		add(addButton); 
		add(removeButton);
		add(scoreLabel); add(typeLabel);
		add(continuous); add(scoreType);
		setUILayout(springLayout);
	}
	
	public void setUILayout(SpringLayout springLayout) {
		springLayout.putConstraint(SpringLayout.EAST, scrollPane1, 354, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scoreLabel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, addButton, 35, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, addButton, 6, SpringLayout.EAST, scrollPane1);
		springLayout.putConstraint(SpringLayout.EAST, addButton, 37, SpringLayout.EAST, scrollPane1);
		springLayout.putConstraint(SpringLayout.NORTH, removeButton, 6, SpringLayout.SOUTH, addButton);
		springLayout.putConstraint(SpringLayout.WEST, removeButton, 6, SpringLayout.EAST, scrollPane1);
		springLayout.putConstraint(SpringLayout.EAST, removeButton, 0, SpringLayout.EAST, addButton);
		springLayout.putConstraint(SpringLayout.NORTH, constraintLabel, 4, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, constraintLabel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane1, 26, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane1, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane1, 165, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, typeLabel, 0, SpringLayout.EAST, constraintLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, scoreLabel, -6, SpringLayout.NORTH, continuous);
		springLayout.putConstraint(SpringLayout.WEST, continuous, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, continuous, -6, SpringLayout.NORTH, scoreType);
		springLayout.putConstraint(SpringLayout.NORTH, typeLabel, 4, SpringLayout.NORTH, scoreType);
		springLayout.putConstraint(SpringLayout.WEST, scoreType, 97, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scoreType, -24, SpringLayout.SOUTH, this);
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	private ActionListener scoreListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ScoreType type = (ScoreType) scoreType.getSelectedItem();
			if (type != null) {
				ScoreRestriction restriction = new ScoreRestriction((ScoreType) scoreType.getSelectedItem(), continuous.isSelected());
				model.setScoreRestriction(restriction);
			}
		}
	};
	
	private ActionListener removeListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (constraintList.getSelectedIndex() != -1) {
				Constraint c = constraintList.getSelectedValue();
				model.removeConstraint(c);
				model1.removeElement(c);
			}
		}
	};
	
	private ActionListener addListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Constraint> constraints = new ArrayList<Constraint>();
			int choice = JOptionPane.showOptionDialog(null, "What kind of Restriction?", "Restriction Type", JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, new String[]{"Horizontal Bound","Vertical Constraint","Custom"}, null);
			if (choice == 2) {
				if (!model.getObjectList1().isEmpty() && !model.getObjectList2().isEmpty()) {
					CustomConstraintPanel panel = new CustomConstraintPanel(model);
					panel.setMinimumSize(new Dimension(500,325));
					panel.setPreferredSize(new Dimension(500,325));
					JFrame tempFrame = new JFrame("Create Custom Restriction");
					int result = JOptionPane.showOptionDialog(tempFrame, panel, "Create Custom Restriction", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (result == 0) {
						String constraintName = panel.getConstraintName();
						List<CustomConstraintVariable> varbs = panel.getVariables();
						int bound;
						if (panel.getBoundType().equals(ConstraintBoundType.LESS_THAN)) {
							bound = GLPKConstants.GLP_UP;
						} else {
							bound = GLPKConstants.GLP_LO;
						}
						Double limit = panel.getLimit();
						CustomConstraint c = new CustomConstraint(constraintName, new CustomConstraintBound(bound, limit), varbs);
						model.addConstraint(c);
						model1.addElement(c);
					}
					tempFrame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "There must be objects in both lists", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (choice == 1 || choice == 0) {
				int min;
				int max;
				try {
					min = Integer.parseInt(JOptionPane.showInputDialog("Minimum Number?"));
					max = Integer.parseInt(JOptionPane.showInputDialog("Maximum Number?"));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Entry. Only Numbers Can Be Entered", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (choice == 1) {
					Constraint c;
					if (min == max) {
						c = new VerticalConstraint("Vertical", new Bound(GLPKConstants.GLP_FX, min, max));
						model.addConstraint(c);
					} else {
						c = new VerticalConstraint("Vertical", new Bound(GLPKConstants.GLP_DB, min, max));
						model.addConstraint(c);
					}
					model1.addElement(c);
				} else if (choice == 0) {
					Constraint c;
					if (min == max) {
						c = new HorizontalConstraint("Horizontal", new Bound(GLPKConstants.GLP_FX, min, max));
						model.addConstraint(c);
					} else {
						c = new HorizontalConstraint("Horizontal", new Bound(GLPKConstants.GLP_DB, min, max));
						model.addConstraint(c);
					}
					model1.addElement(c);
				} else {
					
				}
			}
		}
	};
}
