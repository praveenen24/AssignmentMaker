package GUI.View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GUI.Model.Model;
import Main.AssignmentObject;

public class CustomConstraintPanel extends JPanel {
	
	private Model model;
	private JLabel nameLabel;
	private JLabel list1Name;
	private JLabel list2Name;
	private JTextField nameField;
	private JTextField valueField;
	private JTextArea area;
	private SpringLayout springLayout;
	private JScrollPane scrollPane;
	private JComboBox typeBox;
	private enum type {GreaterThan, LessThan, Equal};
	private String lhs = "";
	private JList<AssignmentObject> objectList1;
	private JList<AssignmentObject> objectList2;
	private DefaultListModel<AssignmentObject> model1;
	private DefaultListModel<AssignmentObject> model2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JButton addTermButton;
	
	public CustomConstraintPanel(Model model) {
		springLayout = new SpringLayout();
		setLayout(springLayout);
		this.model = model;
		typeBox = new JComboBox<>();
		nameLabel = new JLabel("Restriction Name");
		list1Name = new JLabel(model.getList1Name());
		list2Name = new JLabel(model.getList2Name());
		nameField = new JTextField();
		valueField = new JTextField();
		area = new JTextArea();
		scrollPane = new JScrollPane(area);
		model1 = new DefaultListModel<AssignmentObject>(); 
		model2 = new DefaultListModel<AssignmentObject>();
		objectList1 = new JList<>(model1);
		objectList2 = new JList<>(model2);
		scrollPane1 = new JScrollPane(objectList1);
		scrollPane2 = new JScrollPane(objectList2);
		addTermButton = new JButton("Add Term");
		springLayout.putConstraint(SpringLayout.SOUTH, addTermButton, -71, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, addTermButton, 0, SpringLayout.EAST, valueField);
		add(nameLabel); add(nameField);
		add(valueField); add(scrollPane);
		add(typeBox); add(addTermButton);
		add(scrollPane1); add(scrollPane2);
		add(list1Name); add(list2Name);
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
	
	private void buildUI() {
		springLayout.putConstraint(SpringLayout.NORTH, nameField, 16, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 6, SpringLayout.EAST, nameLabel);
		springLayout.putConstraint(SpringLayout.EAST, nameField, -20, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 5, SpringLayout.NORTH, nameField);
		springLayout.putConstraint(SpringLayout.NORTH, typeBox, 35, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -6, SpringLayout.WEST, typeBox);
		springLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 13, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -14, SpringLayout.NORTH, list1Name);
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
	
	public void refresh() {
		area.setText("");
		area.append("\n");
		area.append("\n");
		area.append(lhs);
	}

}
