package GUI.View;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.gnu.glpk.GLPKConstants;

import GUI.Model.Model;
import Main.AssignmentObject;
import Main.Bound;
import Main.Constraint;
import Main.ObjectList;

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

	}

	public Model getModel() {
		return model;
	}

}
