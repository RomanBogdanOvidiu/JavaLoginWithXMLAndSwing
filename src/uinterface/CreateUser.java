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

public class CreateUser extends JFrame {

	private JPanel mypanel;
	private JPanel textPanels;
	private JPanel buttons;
	private JButton createAcc;
	private Container mainContainer;
	private JTextField nume;
	private JTextField prenume;
	private JTextField userName;
	private JTextField password;
	private JLabel bravo;

	public CreateUser() {
		super("Create new Account");
		setBounds(400, 500, 600, 400);// Frame dimensions
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainContainer = this.getContentPane();
		// mainContainer.setLayout(new GridLayout(4, 2));
		mypanel = new JPanel();
		buttons = new JPanel();
		textPanels = new JPanel();

		nume = new JTextField("Nume", 25);
		prenume = new JTextField("Prenume", 25);
		userName = new JTextField("Username", 25);
		password = new JTextField("Password", 25);
		bravo = new JLabel("Account Creation in Process");
		createAcc = new JButton("Submit");
		createAcc.addActionListener(new createAccButtonListener());

		nume.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				nume.setText("");
			}
		});
		/**
		 * Makes the text disappear when you click the prenume textfield
		 */
		prenume.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				prenume.setText("");
			}
		});

		userName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				userName.setText("");
			}
		});
		/**
		 * Makes the text disappear when you click the prenume textfield
		 */
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				password.setText("");
			}
		});

		textPanels.setLayout(new GridLayout(0, 1));
		buttons.setLayout(new GridLayout(0, 1));

		textPanels.add(nume);
		textPanels.add(prenume);
		textPanels.add(userName);
		textPanels.add(password);
		buttons.add(createAcc);

		mypanel.add(buttons, BorderLayout.SOUTH);
		mypanel.add(textPanels, BorderLayout.NORTH);
		mypanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10, true));
		mypanel.add(bravo);
		mainContainer.add(mypanel);

		setVisible(true);
	}

	class createAccButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			try {
				if (!nume.getText().equals("") && !prenume.getText().equals("") && !userName.getText().equals("")
						&& !password.getText().equals("")) {

					List<User> users = new ArrayList<User>();
					if (users.iterator() != null) {
						User user = new User();
						Set<UserRole> roleSet = new HashSet<UserRole>(0);
						UserRole uR = new UserRole(user, "ROLE_USER");
						roleSet.add(uR);

						user.setUserRole(roleSet);
						user.setNume(nume.getText());
						user.setPrenume(prenume.getText());
						user.setUserName(userName.getText());
						user.setPassword(password.getText());

						users = UserHandler.unmarshal(new File("users.xml"));
						// List<User> users = new ArrayList<User>();
						users.add(user);
						UserHandler.marshal(users, new File("users.xml"));

						// DocumentBuilderFactory dbf =
						// DocumentBuilderFactory.newInstance();
						// DocumentBuilder db = dbf.newDocumentBuilder();
						// File xml = new File("users.xml");
						// Document document = db.parse(xml);
						//
						// JAXBContext jc = JAXBContext.newInstance(User.class);
						//
						// Binder<Node> binder = jc.createBinder();
						// user = (User) binder.unmarshal(document);
						//
						// binder.updateXML(user);
						//
						// TransformerFactory tf =
						// TransformerFactory.newInstance();
						// Transformer t = tf.newTransformer();
						// t.transform(new DOMSource(document), new
						// StreamResult(System.out));

						bravo.setText("Succesfully created the account");

					}
				}
			} catch (NumberFormatException | IOException | JAXBException e) {
				JOptionPane.showMessageDialog(null,
						"Wrong input! Complete all the fields in order to create a new account!");

			}
		}
	}
}
