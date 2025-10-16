import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterForm implements ActionListener {
	private JFrame window;
	private JPanel signupPanel, regButtonPanel, clearButtonPanel, backButtonPanel, usernamePanel, usernameTextPanel, passwordLabelPanel,
			passwordTextPanel, confirmPsdLabelPanel, confirmPsdTextPanel, zipLabelPanel, zipTextPanel, userNotePanel, usernameCheckPanel;
	private JButton regButton, backButton, clearButton;
	private JLabel signupLabel, usernameLabel, passwordLabel, confirmPsdLabel, zipLabel, userNote, tableLabel;
	private JTextField usernameText, zipText;
	private JPasswordField passwordText, confirmPsdText;
	private JCheckBox usernameCheck;
	private Font normalFont = new Font("Times New Roman", Font.PLAIN, 25);
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 40);
	private Container con;

	// Panel Background
	private ImageIcon titleImage;
	private JPanel titleImagePanel;
	private JLabel titleImageLabel;
	
	public static void main(String[] args) {
		new RegisterForm("Patient");
	}

	public RegisterForm(String portal) {
		// Frame
		this.window = new JFrame("Register");
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

		// Signup Label
		this.signupLabel = new JLabel(portal + " Registration");
		this.signupLabel.setForeground(Color.black);
		this.signupLabel.setFont(titleFont);

		// Signup Panel
		this.signupPanel = new JPanel();
		this.signupPanel.setBounds(240, 10, 350, 70);
		this.signupPanel.setBackground(Color.white);
		this.signupPanel.setOpaque(false);

		this.signupPanel.add(this.signupLabel);

		// Username Text Label
		this.usernameLabel = new JLabel("Username:");
		this.usernameLabel.setForeground(Color.black);
		this.usernameLabel.setFont(normalFont);

		// Username Panel
		this.usernamePanel = new JPanel();
		this.usernamePanel.setBounds(100, 100, 300, 40);
		this.usernamePanel.setBackground(Color.black);
		this.usernamePanel.setOpaque(false);

		this.usernamePanel.add(usernameLabel);

		// Username Text Field
		this.usernameText = new JTextField("");
		this.usernameText.setPreferredSize(new Dimension(200, 30));
		this.usernameText.setFont(normalFont);

		// Username Text Panel
		this.usernameTextPanel = new JPanel();
		this.usernameTextPanel.setBounds(260, 100, 300, 40);
		this.usernameTextPanel.setBackground(Color.black);
		this.usernameTextPanel.setOpaque(false);

		this.usernameTextPanel.add(usernameText);

		//Zip Code Text Label
		this.zipLabel = new JLabel("Zip Code:");
		this.zipLabel.setForeground(Color.black);
		this.zipLabel.setFont(normalFont);
		
		//Zip Code Text Label Panel
		this.zipLabelPanel = new JPanel();
		this.zipLabelPanel.setBounds(100, 170, 300, 40);
		this.zipLabelPanel.setBackground(Color.black);
		this.zipLabelPanel.setOpaque(false);
		this.zipLabelPanel.add(zipLabel);
		
		//Zip Code Text Field
		this.zipText = new JTextField("");
		this.zipText.setPreferredSize(new Dimension(200, 30));
		this.zipText.setFont(normalFont);
		
		//Zip Code Text Field Panel
		this.zipTextPanel = new JPanel();
		this.zipTextPanel.setBounds(260, 170, 300, 40);
		this.zipTextPanel.setBackground(Color.black);
		this.zipTextPanel.setOpaque(false);
		
		this.zipTextPanel.add(zipText);
		
		// Password Label
		this.passwordLabel = new JLabel("Password:");
		this.passwordLabel.setForeground(Color.black);
		this.passwordLabel.setFont(normalFont);

		// Password Label Panel
		this.passwordLabelPanel = new JPanel();
		this.passwordLabelPanel.setBounds(100, 240, 300, 40);
		this.passwordLabelPanel.setBackground(Color.black);
		this.passwordLabelPanel.setOpaque(false);

		this.passwordLabelPanel.add(passwordLabel);

		// Password Text Field
		this.passwordText = new JPasswordField("");
		this.passwordText.setPreferredSize(new Dimension(200, 30));
		this.passwordText.setFont(normalFont);

		// Password Text Panel
		this.passwordTextPanel = new JPanel();
		//this.passwordTextPanel.setBounds(260, 170, 300, 40);
		this.passwordTextPanel.setBounds(260, 240, 300, 40);
		this.passwordTextPanel.setBackground(Color.black);
		this.passwordTextPanel.setOpaque(false);

		this.passwordTextPanel.add(passwordText);

		// Confirm Password Label
		this.confirmPsdLabel = new JLabel("Re-password:");
		this.confirmPsdLabel.setForeground(Color.black);
		this.confirmPsdLabel.setFont(normalFont);

		// Confirm Password Label Panel
		this.confirmPsdLabelPanel = new JPanel();
		this.confirmPsdLabelPanel.setBounds(85, 310, 300, 40);
		this.confirmPsdLabelPanel.setBackground(Color.black);
		this.confirmPsdLabelPanel.setOpaque(false);

		this.confirmPsdLabelPanel.add(confirmPsdLabel);

		// Confirm Password Text Field
		this.confirmPsdText = new JPasswordField("");
		this.confirmPsdText.setPreferredSize(new Dimension(200, 30));
		this.confirmPsdText.setFont(normalFont);

		// Password Text Panel
		this.confirmPsdTextPanel = new JPanel();
		this.confirmPsdTextPanel.setBounds(260, 310, 300, 40);
		this.confirmPsdTextPanel.setBackground(Color.black);
		this.confirmPsdTextPanel.setOpaque(false);

		this.confirmPsdTextPanel.add(confirmPsdText);
		
		//Username Note Label
		this.userNote = new JLabel("");
		this.userNote.setForeground(Color.black);
		this.userNote.setFont(normalFont);
		
		//Username Note Panel
		this.userNotePanel = new JPanel();
		this.userNotePanel.setBounds(310, 70, 200, 40);
		this.userNotePanel.setBackground(Color.white);
		this.userNotePanel.setOpaque(false);

		this.userNotePanel.add(userNote);

		// Register Button
		this.regButton = new JButton("Signup");
		this.regButton.setBackground(Color.black);
		this.regButton.setForeground(Color.black);
		this.regButton.setOpaque(false);
		this.regButton.setFont(normalFont);

		this.regButton.addActionListener(this);

		// Register Button Panel
		this.regButtonPanel = new JPanel();
		this.regButtonPanel.setBounds(200, 380, 200, 70);
		this.regButtonPanel.setBackground(Color.white);
		this.regButtonPanel.setOpaque(false);

		this.regButtonPanel.add(regButton);

		// Back Button
		this.backButton = new JButton("Back");
		this.backButton.setBackground(Color.black);
		this.backButton.setForeground(Color.black);
		this.backButton.setOpaque(false);
		this.backButton.setFont(normalFont);

		this.backButton.addActionListener(this);

		// Back Button Panel
		this.backButtonPanel = new JPanel();
		this.backButtonPanel.setBounds(410, 380, 200, 70);
		this.backButtonPanel.setBackground(Color.white);
		this.backButtonPanel.setOpaque(false);

		this.backButtonPanel.add(backButton);

		//Clear Button
		this.clearButton = new JButton("Clear");
		this.clearButton.setBackground(Color.black);
		this.clearButton.setForeground(Color.black);
		this.clearButton.setOpaque(false);
		this.clearButton.setFont(normalFont);

		this.clearButton.addActionListener(this);
		
		//Clear Button Panel
		this.clearButtonPanel = new JPanel();
		this.clearButtonPanel.setBounds(310, 380, 200, 70);
		this.clearButtonPanel.setBackground(Color.white);
		this.clearButtonPanel.setOpaque(false);

		this.clearButtonPanel.add(clearButton);
		
		//Check Username Availability 
		this.usernameCheck = new JCheckBox("Check Availability");
		this.usernameCheck.setVisible(false);
		
		this.usernameCheck.addActionListener(this);
		
		//Username Check Panel
		this.usernameCheckPanel = new JPanel();
		this.usernameCheckPanel.setBounds(300, 130, 150, 30);
		this.usernameCheckPanel.setBackground(Color.white);
		this.usernameCheckPanel.setOpaque(false);

		this.usernameCheckPanel.add(this.usernameCheck);
		
		//Table Label
		this.tableLabel = new JLabel(portal);
		
		// Merge
		this.con.add(this.signupPanel);
		this.con.add(this.usernamePanel);
		this.con.add(this.usernameTextPanel);
		this.con.add(this.usernameCheckPanel);
		this.con.add(this.userNotePanel);
		this.con.add(this.zipLabelPanel);
		this.con.add(this.zipTextPanel);
		this.con.add(this.passwordLabelPanel);
		this.con.add(this.passwordTextPanel);
		this.con.add(this.confirmPsdLabelPanel);
		this.con.add(this.confirmPsdTextPanel);
		this.con.add(this.regButtonPanel);
		this.con.add(this.backButtonPanel);
		this.con.add(this.clearButtonPanel);
		this.con.add(this.titleImagePanel);
		this.window.setResizable(false);
		this.window.setVisible(true);
		
		System.out.println(this.tableLabel.getText());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String portal = this.tableLabel.getText();
		
		// TODO Auto-generated method stub
		if(event.getSource() == this.backButton) 
		{
			if(portal.equalsIgnoreCase("patient")) 
			{
				this.window.dispose();
				new PatientHub(portal);
			}else {
				this.window.dispose();
				new StudentHub(portal);
			}
		}
		
		if(event.getSource() == this.clearButton) 
		{
			this.usernameText.setText("");
			this.zipText.setText("");
			this.passwordText.setText("");
			this.confirmPsdText.setText("");
		}
		
		if(this.usernameCheck.isSelected()) {
			
			String username = this.usernameText.getText();
			try {
				if(MySQLConnector.usernameCheck(username)) {
					this.userNote.setText("Username existed");
				}else {
					this.userNote.setText("Vaild Username");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			this.userNote.setText("");
		}
		
		if(event.getSource() == this.regButton) {
			String username = this.usernameText.getText();
			String password = String.valueOf(this.passwordText.getPassword());
			String re_psd =  String.valueOf(this.confirmPsdText.getPassword());
			String zip = this.zipText.getText();
			
			if(username.equals("") || password.equals("") || re_psd.equals("") || zip.equals("")) 
			{
				JOptionPane.showMessageDialog(null, "Please enter every section", "Error", JOptionPane.PLAIN_MESSAGE);
			}else {
				if(password.equals(re_psd)) 
				{
					if(zipCheck(zip)) {
						try {				
							if(MySQLConnector.register(username, password, zip, portal)) {
								new LoginUI(portal);
								this.window.dispose();
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Invaild Zip Code", "Error", JOptionPane.PLAIN_MESSAGE);
						this.zipText.setText("");
						this.passwordText.setText("");
						this.confirmPsdText.setText("");
					}
					
			
				}else {
					JOptionPane.showMessageDialog(null, "Passwords are not match", "Message", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
	
	public static boolean zipCheck(String input) {
        try {
            int number = Integer.parseInt(input);
            return (number >= 10000 && number <= 99999);
        } catch (NumberFormatException e) {
            return false; // Input is not a valid integer
        }
	}
}