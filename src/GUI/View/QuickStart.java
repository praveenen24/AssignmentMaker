package GUI.View;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GUI.Model.Model;

public class QuickStart extends JPanel {

	private Model model;

	public QuickStart() {
		setLayout(new SpringLayout());
		JLabel name1 = new JLabel("Name of List 1?");
		JLabel name2 = new JLabel("Name of List 2?");
		JLabel o1number = new JLabel("How many Objects in list 1");
		JLabel o2number = new JLabel("How many Objects in list 2");
		JTextField listName1 = new JTextField();
		JTextField listName2 = new JTextField();
		JTextField o1 = new JTextField();
		JTextField o2 = new JTextField();
		buildUI();

	}
	
	public void buildUI() {
		
	}

	public Model getModel() {
		return model;
	}

}
