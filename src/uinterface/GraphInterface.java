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
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import handlingPack.UserRepository;
import model.User;
import model.UserRole;

public class GraphInterface extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel textPanel;
	private JPanel buttonsPanel;
	private JTextField userName;
	private JTextField password;
	private Container mainContainer;
	private JButton login;
	private JButton createNewAcc;
	private UserRepository userRepository = new UserRepository();

	public GraphInterface() {
		super("Login Into your personal account");
		setBounds(400, 500, 600, 400);// Frame dimensions
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainContainer = this.getContentPane();
		// mainContainer.setLayout(new GridLayout(4, 2));

		mainPanel = new JPanel();
		textPanel = new JPanel();
		buttonsPanel = new JPanel();

		userName = new JTextField("Enter your username", 25);
		password = new JTextField("Enter password", 25);
		login = new JButton("Login");
		createNewAcc = new JButton("Create New Account");
		login.addActionListener(new loginButtonActionListener());
		createNewAcc.addActionListener(new createNewAccButtonListener());

		userName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				userName.setText("");
			}
		});

		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				password.setText("");
			}
		});

		textPanel.setLayout(new GridLayout(0, 1));

		textPanel.add(userName);
		textPanel.add(password);

		buttonsPanel.setLayout(new GridLayout(1, 1));
		buttonsPanel.add(login);
		buttonsPanel.add(createNewAcc);
		mainPanel.add(textPanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10, true));
		mainContainer.add(mainPanel);
	}

	class loginButtonActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			try {
				if (!userName.getText().equals("") && !password.getText().equals("")) {

					User user = userRepository.findByUsername(userName.getText());
					System.out.println(user.getNume() + user.getPassword() + user.getPrenume() + " "
							+ user.getUserName() + " " + user.getUserRole());
					Set<UserRole> roleSet = new HashSet<UserRole>(0);

					for (UserRole s : user.getUserRole()) {
						if (s.getRole().equals("ROLE_ADMIN")) {
							LoggedIn uau = new LoggedIn("ROLE_ADMIN");
							uau.setVisible(true);
						} else if (s.getRole().equals("ROLE_USER")) {
							LoggedIn uau = new LoggedIn("ROLE_USER");
							uau.setVisible(true);
						}

					}

					if (user.getUserName().equals("") || user.getPassword().equals("")) {
						JOptionPane.showMessageDialog(null, "Wrong username or password!");

					}

				}
				// if ((user.getUserName().equals(userName.getText()))
				// && user.getPassword().equals(password.getText())) {
				//
				// // //LoggedIn uau=new LoggedIn();
				// // uau.setVisible(true);
				// }
				//
				// }

			} catch (NumberFormatException | JAXBException | ParserConfigurationException | SAXException
					| IOException e) {
				JOptionPane.showMessageDialog(null, "Wrong username or password!");

			}

		}
	}

	class createNewAccButtonListener implements ActionListener {

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
