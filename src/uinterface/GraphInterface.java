package uinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import handlingPack.UserRepository;
import model.User;
import model.UserRole;
import security.AppSecurity;

public class GraphInterface extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	private JPanel textPanel;
	private JPanel buttonsPanel;
	private JTextField userName;
	// private JTextField password;
	private Container mainContainer;
	private JButton login;
	private JButton createNewAcc;
	private UserRepository userRepository = new UserRepository();
	private JPasswordField passwordField;

	private static final String ROLEUSER = "ROLE_USER";
	private static final String ROLEADMIN = "ROLE_ADMIN";

	public GraphInterface() {
		super("Login Into your personal account");
		initialize();
	}

	private void initialize() {

		setBounds(400, 500, 600, 400);// Frame dimensions
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainContainer = this.getContentPane();
		// mainContainer.setLayout(new GridLayout(4, 2));

		mainPanel = new JPanel();
		textPanel = new JPanel();
		buttonsPanel = new JPanel();
		passwordField = new JPasswordField("Password", 10);
		userName = new JTextField("Enter your username", 25);
		// password = new JTextField();
		login = new JButton("Login");
		createNewAcc = new JButton("Create New Account");
		login.addActionListener(new loginButtonActionListener());
		createNewAcc.addActionListener(new createNewAccButtonListener());

		userName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (userName.getText().equals("Enter your username")) {
					userName.setText("");
					repaint();
					revalidate();
				}
			}
		});

		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (passwordField.getText().equals("Password")) {
					passwordField.setText("");
					repaint();
					revalidate();
				}
			}
		});

		textPanel.setLayout(new GridLayout(0, 1));

		textPanel.add(userName);
		textPanel.add(passwordField);

		buttonsPanel.setLayout(new GridLayout(1, 1));
		buttonsPanel.add(login);
		buttonsPanel.add(createNewAcc);
		mainPanel.add(textPanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10, true));
		mainContainer.add(mainPanel);
	}

	private class loginButtonActionListener implements ActionListener {

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent event) {

			try {
				String pass;

				if (!userName.getText().equals("") && !passwordField.getText().equals("")) {
					User user = userRepository.findByUsername(userName.getText());
					pass = AppSecurity.encrypt(passwordField.getText());
					Set<UserRole> roleSet = new HashSet<UserRole>(0);
					System.out.println("USERPASSWORD" + user.getSafePass() + "  pass " + pass);
					if (user.getSafePass().equals(pass)) {
						for (UserRole s : user.getUserRole()) {
							if (s.getRole().equals(ROLEADMIN)) {
								LoggedIn uau = new LoggedIn(ROLEADMIN);
								uau.setVisible(true);
							} else if (s.getRole().equals(ROLEUSER)) {
								LoggedIn uau = new LoggedIn(ROLEUSER);
								uau.setVisible(true);
							}

						}
					} else
						JOptionPane.showMessageDialog(null, "Wrong password!");

					if (user.getUserName().equals("") || user.getPassword().equals("")) {
						JOptionPane.showMessageDialog(null, "Wrong username or password!");

					}

				}

			} catch (NumberFormatException | JAXBException | ParserConfigurationException | SAXException
					| IOException e) {
				JOptionPane.showMessageDialog(null, "Wrong username or password!");

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "problema");
			}

		}
	}

	private class createNewAccButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			try {

				CreateUser uau = new CreateUser();
				uau.setVisible(true);

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "II BAI");

			}

		}
	}

}
