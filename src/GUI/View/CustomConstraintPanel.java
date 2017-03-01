package GUI.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GUI.Model.Model;
import Main.AssignmentObject;
import Main.ConstraintBoundType;
import Main.CustomConstraintVariable;

public class CustomConstraintPanel extends JPanel {
	
	private Model model;
	private JLabel nameLabel;
	private JLabel list1Name;
	private JLabel list2Name;
	private JLabel multiplierLabel;
	private JTextField nameField;
	private JTextField valueField;
	private JTextField multiplierField;
	private SpringLayout springLayout;
	private JComboBox<ConstraintBoundType> typeBox;
	private String lhs = "";
	private JList<AssignmentObject> objectList1;
	private JList<AssignmentObject> objectList2;
	private JList<CustomConstraintVariable> variables;
	private DefaultListModel<AssignmentObject> model1;
	private DefaultListModel<AssignmentObject> model2;
	private DefaultListModel<CustomConstraintVariable> model3;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JButton addTermButton;
	private JButton removeTermButton;
	
	public CustomConstraintPanel(Model model) {
		springLayout = new SpringLayout();
		setLayout(springLayout);
		this.model = model;
		typeBox = new JComboBox<ConstraintBoundType>(ConstraintBoundType.values());
		nameLabel = new JLabel("Restriction Name");
		list1Name = new JLabel(model.getList1Name());
		list2Name = new JLabel(model.getList2Name());
		multiplierLabel = new JLabel("Multiplier");
		nameField = new JTextField();
		valueField = new JTextField();
		multiplierField = new JTextField();
		model1 = new DefaultListModel<AssignmentObject>(); 
		model2 = new DefaultListModel<AssignmentObject>();
		model3 = new DefaultListModel<CustomConstraintVariable>();
		objectList1 = new JList<>(model1);
		objectList2 = new JList<>(model2);
		variables = new JList<>(model3);
		scrollPane1 = new JScrollPane(objectList1);
		scrollPane2 = new JScrollPane(objectList2);
		scrollPane3 = new JScrollPane(variables);
		addTermButton = new JButton("Add");
		addTermButton.addActionListener(addTermListener);
		removeTermButton = new JButton("Remove");
		removeTermButton.addActionListener(removeTermListener);
		add(nameLabel); add(nameField);
		add(valueField); add(scrollPane3);
		add(typeBox); add(addTermButton);
		add(scrollPane1); add(scrollPane2);
		add(list1Name); add(list2Name);
		add(multiplierLabel); add(multiplierField);
		add(removeTermButton);
		setMinimumSize(new Dimension(500, 325));
		setBorder(BorderFactory.createLineBorder(Color.black, 2));
		populateLists();
		buildUI();
	}
	
	public void populateLists() {
		for (AssignmentObject o : model.getObjectList1()) {
			model1.addElement(o);
		}
		for (AssignmentObject o : model.getObjectList2()) {
			model2.addElement(o);
		}
	}
	
	public String getConstraintName() {
		return nameField.getText();
	}
	
	public List<CustomConstraintVariable> getVariables() {
		 List<CustomConstraintVariable> varbs = new ArrayList<CustomConstraintVariable>();
		 for (int i = 0; i < model3.getSize(); i++) {
			 varbs.add(model3.getElementAt(i));
		 }
		 return varbs;
	}
	
	public ConstraintBoundType getBoundType() {
		return (ConstraintBoundType) typeBox.getSelectedItem();
	}
	
	public Double getLimit() {
		return Double.parseDouble(valueField.getText());
	}
	
	private void buildUI() {
		springLayout.putConstraint(SpringLayout.NORTH, removeTermButton, 6, SpringLayout.SOUTH, addTermButton);
		springLayout.putConstraint(SpringLayout.WEST, removeTermButton, 0, SpringLayout.WEST, addTermButton);
		springLayout.putConstraint(SpringLayout.NORTH, addTermButton, 16, SpringLayout.SOUTH, multiplierField);
		springLayout.putConstraint(SpringLayout.WEST, addTermButton, 6, SpringLayout.EAST, scrollPane2);
		springLayout.putConstraint(SpringLayout.NORTH, multiplierField, -5, SpringLayout.NORTH, multiplierLabel);
		springLayout.putConstraint(SpringLayout.WEST, multiplierField, 1, SpringLayout.EAST, multiplierLabel);
		springLayout.putConstraint(SpringLayout.EAST, multiplierField, 0, SpringLayout.EAST, valueField);
		springLayout.putConstraint(SpringLayout.WEST, multiplierLabel, 6, SpringLayout.EAST, scrollPane2);
		springLayout.putConstraint(SpringLayout.SOUTH, multiplierLabel, -107, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, nameField, 16, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 6, SpringLayout.EAST, nameLabel);
		springLayout.putConstraint(SpringLayout.EAST, nameField, -20, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, nameField);
		springLayout.putConstraint(SpringLayout.NORTH, typeBox, 35, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane3, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane3, -6, SpringLayout.WEST, typeBox);
		springLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, scrollPane3);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane3, 13, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane3, -14, SpringLayout.NORTH, list1Name);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane1, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane2, 210, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane1, -14, SpringLayout.WEST, scrollPane2);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane2, -124, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, typeBox, 237, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, typeBox, -105, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, list1Name, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, list1Name, -143, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, list2Name, 0, SpringLayout.NORTH, list1Name);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane1, 6, SpringLayout.SOUTH, list1Name);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane1, -22, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane2, 6, SpringLayout.SOUTH, list2Name);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane2, -22, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, list2Name, 0, SpringLayout.WEST, scrollPane2);
		springLayout.putConstraint(SpringLayout.NORTH, valueField, -1, SpringLayout.NORTH, typeBox);
		springLayout.putConstraint(SpringLayout.WEST, valueField, 6, SpringLayout.EAST, typeBox);
		springLayout.putConstraint(SpringLayout.EAST, valueField, -6, SpringLayout.EAST, this);
	}
	
	private ActionListener addTermListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			double multiplier = Double.parseDouble(multiplierField.getText());
			String value = objectList1.getSelectedValue().getName() + objectList2.getSelectedValue().getName();
			CustomConstraintVariable varb = new CustomConstraintVariable(multiplier, value);
			model3.addElement(varb);
			objectList1.setSelectedValue(null, true);
			objectList2.setSelectedValue(null, true);
			multiplierField.setText("0.0");
		}
	};
	
	private ActionListener removeTermListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!variables.isSelectionEmpty()) model3.removeElement(variables.getSelectedValue());
		}
		
	};
}
