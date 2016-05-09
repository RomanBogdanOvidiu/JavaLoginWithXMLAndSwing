package uinterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import handlingPack.UserHandler;
import model.User;
import model.UserRole;
import security.AppSecurity;

public class CreateUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel textPanels;
	private JPanel buttons;
	private JButton createAcc;
	private Container mainContainer;
	private JTextField nume;
	private JTextField prenume;
	private JTextField userName;
	private JTextField password;
	private JTextField safePass;
	private JLabel succesfullyCreated;

	private static final String ROLEUSER = "ROLE_USER";
	private static final String USERNAME = "Username";
	private static final String NUME = "Nume";
	private static final String PRENUME = "Prenume";
	private static final String PASSWORD = "Password";
	private static final String SAFEPASS = "SafePass";

	public CreateUser() {
		super("Create new Account");
		initialize();

	}

	private void initialize() {
		setBounds(400, 500, 600, 400);// Frame dimensions
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainContainer = this.getContentPane();
		mainPanel = new JPanel();
		buttons = new JPanel();
		textPanels = new JPanel();

		nume = new JTextField(NUME, 25);
		prenume = new JTextField(PRENUME, 25);
		userName = new JTextField(USERNAME, 25);
		password = new JTextField(PASSWORD, 25);
		safePass = new JTextField(SAFEPASS, 25);
		succesfullyCreated = new JLabel("Account Creation in Process");
		createAcc = new JButton("Submit");
		createAcc.addActionListener(new createAccButtonListener());

		nume.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (nume.getText().equals(NUME)) {
					nume.setText("");
					repaint();
					revalidate();
				}
			}
		});

		prenume.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (prenume.getText().equals(PRENUME)) {
					prenume.setText("");
					repaint();
					revalidate();
				}
			}
		});

		userName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (userName.getText().equals(USERNAME)) {
					userName.setText("");
					repaint();
					revalidate();
				}
			}
		});

		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (password.getText().equals(PASSWORD)) {
					password.setText("");
					repaint();
					revalidate();
				}
			}
		});
		safePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (safePass.getText().equals(SAFEPASS)) {
					safePass.setText("");
					repaint();
					revalidate();
				}
			}
		});

		textPanels.setLayout(new GridLayout(0, 1));
		buttons.setLayout(new GridLayout(0, 1));

		textPanels.add(nume);
		textPanels.add(prenume);
		textPanels.add(userName);
		textPanels.add(password);
		textPanels.add(safePass);
		buttons.add(createAcc);

		mainPanel.add(buttons, BorderLayout.SOUTH);
		mainPanel.add(textPanels, BorderLayout.NORTH);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10, true));
		mainPanel.add(succesfullyCreated);
		mainContainer.add(mainPanel);

		setVisible(true);
	}

	// private void trimThemAll(String nume, String prenume, String username,
	// String password) {
	// nume = nume.trim();
	// prenume = prenume.trim();
	// username = username.trim();
	// password = password.trim();
	//
	// }

	private User checkuser(User user, List<User> users) {
		for (User u : users) {
			if (u.getUserName().equals(user.getUserName())) {
				JOptionPane.showMessageDialog(null, "There is already an account with this username");
				return user;
			}
		}
		return null;
	}

	private class createAccButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			String name = nume.getText();
			String lastN = prenume.getText();
			String username = userName.getText();
			String pass = password.getText();
			String safeP = safePass.getText();
			try {
				name = name.trim();
				lastN = lastN.trim();
				username = username.trim();
				pass = pass.trim();
				safeP = safeP.trim();
				if (!name.equals("") && !lastN.equals("") && !username.equals("") && !pass.equals("")
						&& !safeP.equals("") && (safeP.equals(pass)) && !name.equals(" ") && !lastN.equals(" ")
						&& !username.equals(" ") && !pass.equals(" ")) {

					List<User> users = new ArrayList<User>();

					users = UserHandler.unmarshal(new File("users.xml"));
					if (users.iterator() != null) {
						User user = new User();
						Set<UserRole> roleSet = new HashSet<UserRole>(0);
						UserRole uR = new UserRole(user, ROLEUSER);
						roleSet.add(uR);

						try {
							safeP = AppSecurity.encrypt(safeP);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						user.setUserRole(roleSet);
						user.setNume(name);
						user.setPrenume(lastN);
						user.setUserName(username);
						user.setPassword(pass);
						user.setSafePass(safeP);

						if (checkuser(user, users) == null)
							users.add(user);

						UserHandler.marshal(users, new File("users.xml"));

						succesfullyCreated.setText("Succesfully created the account");

					}
				} else
					JOptionPane.showMessageDialog(null,
							"Wrong input! Complete all the fields in order to create a new account!");

			} catch (NumberFormatException | IOException | JAXBException e) {
				JOptionPane.showMessageDialog(null,
						"Wrong input! Complete all the fields in order to create a new account!");

			}
		}
	}
}
