package uinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoggedIn extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JLabel hello;

	private Container mainContainer;

	public LoggedIn(String role) {
		super("Congrats, you logged in!");
		initilize(role);

	}

	private void initilize(String role) {
		setBounds(400, 500, 600, 400);// Frame dimensions
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainContainer = this.getContentPane();
		mainPanel = new JPanel();
		hello = new JLabel("John Snow is alive and healthy ! John your role is : " + role);

		mainPanel.add(hello, BorderLayout.CENTER);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10, true));
		mainContainer.add(mainPanel);
	}

}
