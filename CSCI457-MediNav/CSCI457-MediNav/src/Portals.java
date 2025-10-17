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


public class Portals implements ActionListener {
	private JFrame window;
	private JPanel studentPanel, patientPanel;
	private JButton studentButton, patientButton;
	private JLabel tableLabel;
	private Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 75);
	private Container con;
	
	// Panel Background
	private ImageIcon titleImage;
	private JPanel titleImagePanel;
	private JLabel titleImageLabel;

	public static void main(String[] args) {
		new Portals();
	}

	public Portals() {
		
		// Frame
		this.window = new JFrame("Main Portal");
		this.window.setSize(800, 538);
		this.window.getContentPane().setBackground(new Color(74, 146, 218, 255));
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(null);
		this.con = window.getContentPane();

		// Background Image
		this.titleImage = new ImageIcon(getClass().getClassLoader().getResource("MainPortal.png"));

		this.titleImagePanel = new JPanel();
		this.titleImagePanel.setBounds(0, 0, 800, 538);

		this.titleImageLabel = new JLabel();
		this.titleImageLabel.setIcon(titleImage);
		this.titleImagePanel.add(this.titleImageLabel);

		// Student Button
		this.studentButton = new JButton("Student");
		this.studentButton.setBackground(new Color(210, 229, 251, 255));
		this.studentButton.setForeground(new Color(14, 34, 68));
		this.studentButton.setOpaque(true);
		this.studentButton.setFont(normalFont);

		this.studentButton.setToolTipText("Press it to login");
		this.studentButton.addActionListener(this);

		// Student Button Panel
		this.studentPanel = new JPanel();
		this.studentPanel.setBounds(225, 350, 200, 70);
		this.studentPanel.setBackground(Color.white);
		this.studentPanel.setOpaque(false);

		this.studentPanel.add(studentButton);

		// Register Button
		this.patientButton = new JButton("Patient");
		this.patientButton.setBackground(new Color(210, 229, 251, 255));
		this.patientButton.setForeground(new Color(14, 34, 68));
		this.patientButton.setOpaque(true);
		this.patientButton.setFont(normalFont);

		this.patientButton.addActionListener(this);

		// Register Button Panel
		this.patientPanel = new JPanel();
		this.patientPanel.setBounds(375, 350, 200, 70);
		this.patientPanel.setBackground(Color.white);
		this.patientPanel.setOpaque(false);

		this.patientPanel.add(patientButton);

		// Table Label
		this.tableLabel = new JLabel("");

		// Merge
		this.con.add(studentPanel);
		this.con.add(patientPanel);
		this.con.add(titleImagePanel);
		this.window.setResizable(false);
		this.window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		String tableL = this.tableLabel.getText();

		if (event.getSource() == this.studentButton) {
			this.window.dispose();
			this.tableLabel.setText("Student");
			tableL = this.tableLabel.getText();
			new StudentHub(tableL);
			// JOptionPane.showMessageDialog(null, "Student Portal", "Message",
			// JOptionPane.INFORMATION_MESSAGE);
		}

		if (event.getSource() == this.patientButton) {
			this.window.dispose();
			this.tableLabel.setText("Patient");
			tableL = this.tableLabel.getText();
			new PatientHub(tableL);
		}
	}
}
