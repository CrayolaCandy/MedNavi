import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StudentHub implements ActionListener {
	private JFrame window;
	private JPanel loginButtonPanel, regButtonPanel, backButtonPanel;
	private JButton loginButton, regButton, backButton;
	private JLabel tableLabel;
	private Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 75);
	private Container con;

	// Panel Background
		private ImageIcon titleImage;
		private JPanel titleImagePanel;
		private JLabel titleImageLabel;
		
	public static void main(String[] args) {
		new StudentHub("student");
	}

	public StudentHub(String portal) {
		this.window = new JFrame("Student Portal");
		this.window.setSize(800, 538);
		this.window.getContentPane().setBackground(new Color(74, 146, 218, 255));
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(null);
		this.con = window.getContentPane();
		
		
		// Background Image
				this.titleImage = new ImageIcon(getClass().getClassLoader().getResource("StudentPortal.png"));

				this.titleImagePanel = new JPanel();
				this.titleImagePanel.setBounds(0, 0, 800, 538);

				this.titleImageLabel = new JLabel();
				this.titleImageLabel.setIcon(titleImage);
				this.titleImagePanel.add(this.titleImageLabel);

		// Login Button
		this.loginButton = new JButton("Login");
		this.loginButton.setBackground(new Color(210, 229, 251, 255));
		this.loginButton.setForeground(new Color(14, 34, 68));
		this.loginButton.setOpaque(true);
		this.loginButton.setFont(normalFont);

		this.loginButton.setToolTipText("Press it to login");
		this.loginButton.addActionListener(this);

		// Login Button Panel
		this.loginButtonPanel = new JPanel();
		this.loginButtonPanel.setBounds(190, 350, 200, 70);
		this.loginButtonPanel.setBackground(Color.white);
		this.loginButtonPanel.setOpaque(false);

		this.loginButtonPanel.add(loginButton);

		// Register Button
		this.regButton = new JButton("Signup");
		this.regButton.setBackground(new Color(210, 229, 251, 255));
		this.regButton.setForeground(new Color(14, 34, 68));
		this.regButton.setOpaque(true);
		this.regButton.setFont(normalFont);

		this.regButton.addActionListener(this);

		// Register Button Panel
		this.regButtonPanel = new JPanel();
		this.regButtonPanel.setBounds(320, 350, 200, 70);
		this.regButtonPanel.setBackground(Color.white);
		this.regButtonPanel.setOpaque(false);

		this.regButtonPanel.add(regButton);

		// Back Button
		this.backButton = new JButton("Back");
		this.backButton.setBackground(new Color(210, 229, 251, 255));
		this.backButton.setForeground(new Color(14, 34, 68));
		this.backButton.setOpaque(true);
		this.backButton.setFont(normalFont);

		this.backButton.addActionListener(this);

		// Back Button Panel
		this.backButtonPanel = new JPanel();
		this.backButtonPanel.setBounds(445, 350, 200, 70);
		this.backButtonPanel.setBackground(Color.white);
		this.backButtonPanel.setOpaque(false);

		this.backButtonPanel.add(backButton);

		// Table Label
		this.tableLabel = new JLabel(portal);

		// Merge
		this.con.add(loginButtonPanel);
		this.con.add(backButtonPanel);
		this.con.add(regButtonPanel);
		this.con.add(titleImagePanel);

		this.window.setResizable(false);
		this.window.setVisible(true);

		System.out.println(portal);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String portal = this.tableLabel.getText();

		if (event.getSource() == this.loginButton) {
			this.window.dispose();
			new LoginUI(portal);
		}

		if (event.getSource() == this.regButton) {
			this.window.dispose();
			new RegisterForm(portal);
		}

		if (event.getSource() == this.backButton) {
			this.window.dispose();
			new Portals();
		}
	}
}
