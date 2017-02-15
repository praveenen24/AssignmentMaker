package GUI.View;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GUI.Model.Model;

public class ScoreRestrictionsPanel extends JPanel {

	private Model model;
	private String[] scoreType = {""};

	public ScoreRestrictionsPanel(Model model) {
		this.model = model;
		
		setLayout(new SpringLayout());
	}
	
	public void buildUI() {
		
	}

	public Model getModel() {
		return model;
	}

}
