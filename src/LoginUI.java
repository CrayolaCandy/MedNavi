import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.ColorAttribute;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginUI implements ActionListener {
	private JFrame window;
	private JPanel titlePanel, loginButtonPanel, backButtonPanel, usernamePanel, usernameTextPanel, passwordPanel,
			passwordTextPanel;
	private JButton loginButton, backButton;
	private JLabel titleNameLabel, usernameLabel, passwordLabel, tableLabel;
	private JTextField usernameText;
	private Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 65);
	private JPasswordField userPasswordText;
	private Container con;

	// Panel Background
	private ImageIcon titleImage;
	private JPanel titleImagePanel;
	private JLabel titleImageLabel;

	public static void main(String[] args) {
		new LoginUI("Student");
	}

	public LoginUI(String portal) {
		// Frame
		this.window = new JFrame("Login");
		this.window.setSize(800, 516);
		this.window.getContentPane().setBackground(Color.black);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(null);
		this.con = window.getContentPane();

		// Panel Background
		this.titleImage = new ImageIcon(getClass().getClassLoader().getResource("LogoInfo.jpg"));

		this.titleImagePanel = new JPanel();
		this.titleImagePanel.setBounds(0, 0, 800, 516);

		this.titleImageLabel = new JLabel();
		this.titleImageLabel.setIcon(titleImage);
		this.titleImagePanel.add(this.titleImageLabel);

		// Title Panel
		this.titlePanel = new JPanel();
		this.titlePanel.setBounds(100, 80, 600, 150);
		this.titlePanel.setBackground(Color.white);
		this.titlePanel.setOpaque(false);

		// Title Label
		this.titleNameLabel = new JLabel(portal + " Login");
		this.titleNameLabel.setForeground(Color.black);
		this.titleNameLabel.setFont(titleFont);

		this.titlePanel.add(this.titleNameLabel);

		// Username Label
		this.usernameLabel = new JLabel("Username:");
		this.usernameLabel.setForeground(Color.black);
		this.usernameLabel.setFont(normalFont);

		// Username Label Panel
		this.usernamePanel = new JPanel();
		this.usernamePanel.setBounds(175, 210, 150, 40);
		this.usernamePanel.setBackground(Color.white);
		this.usernamePanel.setOpaque(false);

		this.usernamePanel.add(usernameLabel);

		// Username Text Field
		this.usernameText = new JTextField("");
		this.usernameText.setPreferredSize(new Dimension(300, 40));
		this.usernameText.setFont(normalFont);

		// Username Text Field Panel
		this.usernameTextPanel = new JPanel();
		this.usernameTextPanel.setBounds(325, 210, 300, 40);
		this.usernameTextPanel.setBackground(Color.black);
		this.usernameTextPanel.setOpaque(false);

		this.usernameTextPanel.add(usernameText);

		// Password Label Panel
		this.passwordPanel = new JPanel();
		this.passwordPanel.setBounds(175, 260, 150, 40);
		this.passwordPanel.setBackground(Color.white);
		this.passwordPanel.setOpaque(false);

		// Password Label
		this.passwordLabel = new JLabel("Password:");
		this.passwordLabel.setForeground(Color.black);
		this.passwordLabel.setFont(normalFont);

		this.passwordPanel.add(passwordLabel);

		// Password Text Panel
		this.passwordTextPanel = new JPanel();
		this.passwordTextPanel.setBounds(325, 260, 300, 40);
		this.passwordTextPanel.setBackground(Color.black);
		this.passwordTextPanel.setOpaque(false);

		// Password Text
		this.userPasswordText = new JPasswordField();
		this.userPasswordText.setPreferredSize(new Dimension(300, 40));
		this.userPasswordText.setFont(normalFont);

		this.passwordTextPanel.add(userPasswordText);

		// Login Button
		this.loginButton = new JButton("Login");
		this.loginButton.setBackground(Color.black);
		this.loginButton.setForeground(Color.black);
		this.loginButton.setOpaque(false);
		this.loginButton.setFont(normalFont);

		this.loginButton.setToolTipText("Press it to login");
		this.loginButton.addActionListener(this);

		// Login Button Panel
		this.loginButtonPanel = new JPanel();
		this.loginButtonPanel.setBounds(225, 350, 200, 70);
		this.loginButtonPanel.setBackground(Color.white);
		this.loginButtonPanel.setOpaque(false);

		this.loginButtonPanel.add(loginButton);

		// Back Button
		this.backButton = new JButton("Back");
		this.backButton.setBackground(Color.black);
		this.backButton.setForeground(Color.black);
		this.backButton.setOpaque(false);
		this.backButton.setFont(normalFont);

		this.backButton.addActionListener(this);

		// Register Button Panel
		this.backButtonPanel = new JPanel();
		this.backButtonPanel.setBounds(375, 350, 200, 70);
		this.backButtonPanel.setBackground(Color.white);
		this.backButtonPanel.setOpaque(false);

		this.backButtonPanel.add(backButton);

		// Table Label
		this.tableLabel = new JLabel(portal);

		// Merge
		this.con.add(titlePanel);
		this.con.add(loginButtonPanel);
		this.con.add(backButtonPanel);
		this.con.add(usernamePanel);
		this.con.add(usernameTextPanel);
		this.con.add(passwordPanel);
		this.con.add(passwordTextPanel);
		this.con.add(titleImagePanel);

		this.window.setResizable(false);
		this.window.setVisible(true);
		
		System.out.println(this.tableLabel.getText());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		String portal = this.tableLabel.getText();

		if (event.getSource() == this.loginButton) {
			String username = this.usernameText.getText();
			String password = String.valueOf(this.userPasswordText.getPassword());

			try {
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter both username and password", "Message",
							JOptionPane.PLAIN_MESSAGE);
					this.usernameText.setText("");
					this.userPasswordText.setText("");
				} else {
					if (MySQLConnector.login(username, password, portal)) {
						this.window.dispose();
						if (portal.equalsIgnoreCase("patient")) {
							new PatientSearch(username, portal);
							PatientSearch.searchInstructions();
						} else {
							new StudentSearch(username, portal);
							StudentSearch.searchInstructions();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Username or password wrong", "Message",
								JOptionPane.PLAIN_MESSAGE);
						this.usernameText.setText("");
						this.userPasswordText.setText("");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (event.getSource() == this.backButton) {
			if (portal.equalsIgnoreCase("patient")) {
				try {
					// MySQLConnector.register(null, null);
					this.window.dispose();
					new PatientHub(portal);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				this.window.dispose();
				new StudentHub(portal);
			}

		}
	}

}