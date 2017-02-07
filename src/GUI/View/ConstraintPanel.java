package GUI.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import Main.HorizontalConstraint;
import Main.VerticalConstraint;

public class ConstraintPanel extends JPanel {
	private Model model;
	private JList<Constraint> constraintList;
	private DefaultListModel<Constraint> model1;
	private JScrollPane scrollPane1;
	private JLabel constraintLabel;
	private JButton addButton;
	private JButton removeButton;
	
	public ConstraintPanel(Model model) {
		this.model = model;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		model1 = new DefaultListModel<Constraint>(); 
		constraintList = new JList<>(model1);
		scrollPane1 = new JScrollPane(constraintList);
		constraintLabel = new JLabel("Restrictions");
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		addButton.addActionListener(addListener);
		add(scrollPane1);
		add(constraintLabel);
		add(addButton);
		add(removeButton);
		setUILayout(springLayout);
	}
	
	public void setUILayout(SpringLayout springLayout) {
		springLayout.putConstraint(SpringLayout.NORTH, addButton, 23, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, constraintLabel, 4, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, constraintLabel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane1, 26, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane1, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, removeButton, 9, SpringLayout.SOUTH, addButton);
		springLayout.putConstraint(SpringLayout.WEST, addButton, 0, SpringLayout.WEST, removeButton);
		springLayout.putConstraint(SpringLayout.EAST, removeButton, -10, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane1, 268, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane1, 344, SpringLayout.WEST, this);
	}
	
	public Model getModel() {
		return model;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	private ActionListener addListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Constraint> constraints = new ArrayList<Constraint>();
			int choice = JOptionPane.showOptionDialog(null, "What kind of Restriction?", "Restriction Type", JOptionPane.YES_NO_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, new String[]{"Horizontal Bound","Vertical Constraint","Custom"}, null);
			if (choice == 2) System.out.println("Custom");
			if (choice == 1 || choice == 0) {
				String name = JOptionPane.showInputDialog("Constraint Name?");
				int min = Integer.parseInt(JOptionPane.showInputDialog("Minimum Number?"));
				int max = Integer.parseInt(JOptionPane.showInputDialog("Maximum Number?"));
				if (choice == 1) {
					Constraint c;
					if (min == max) {
						c = new VerticalConstraint(name, new Bound(GLPKConstants.GLP_FX, min, max));
						model.addConstraint(c);
					} else {
						c = new VerticalConstraint(name, new Bound(GLPKConstants.GLP_DB, min, max));
						model.addConstraint(c);
					}
					model1.addElement(c);
				} else if (choice == 0) {
					Constraint c;
					if (min == max) {
						c = new HorizontalConstraint(name, new Bound(GLPKConstants.GLP_FX, min, max));
						model.addConstraint(c);
					} else {
						c = new HorizontalConstraint(name, new Bound(GLPKConstants.GLP_DB, min, max));
						model.addConstraint(c);
					}
					model1.addElement(c);
				}
			}
		}
	};
}
