import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class PatientSearch implements ActionListener, MouseListener {
	private JFrame window;
	private JPanel usernameLabelPanel, userInputPanel, reloadButtonPanel, searchButtonPanel, logoutButtonPanel,
			mapButtonPanel, deleButtonPanel, doctorListPanel, doctor1RBPanel, doctor2RBPanel;
	private JLabel usernameLabel, dLabel, tableLabel;
	private JTextField userInput;
	private JTextArea respArea;
	private JScrollPane scroll;
	private JComboBox searchList;
	private JButton searchButton, logoutButton, mapButton, reloadButton, deleButton;
	private JRadioButton doctor1RButton, doctor2RButton;
	private ButtonGroup dGroup;

	private Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
	private Container con;

	// Window Background
	private ImageIcon titleImage;
	private JPanel titleImagePanel;
	private JLabel titleImageLabel;

	// Label Icon
	private ImageIcon infoIcon;
	private JPanel infoIconPanel;
	private JLabel infoIconLabel;

	private String doctor = "";
	private String textFieldHolder = "Please enter your symptom(s)";

	
	public static void main(String[] args) {

		try {
			new PatientSearch("jhu17", "patient");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public PatientSearch(String username, String portal) throws SQLException {
		// Frame
		this.window = new JFrame("Patient Search");
		this.window.setSize(1199, 772);
		// this.window.getContentPane().setBackground(new Color(74,146,218,255));
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.con = window.getContentPane();

		// Panel Background
		this.titleImage = new ImageIcon(getClass().getClassLoader().getResource("PatientSearch.png"));

		this.titleImagePanel = new JPanel();
		this.titleImagePanel.setBounds(0, 0, 1199, 772);

		this.titleImageLabel = new JLabel();
		this.titleImageLabel.setIcon(titleImage);
		this.titleImagePanel.add(this.titleImageLabel);

		// Info Icon
		this.infoIcon = new ImageIcon(getClass().getClassLoader().getResource("InfoIcon(S).png"));

		this.infoIconPanel = new JPanel();
		this.infoIconPanel.setBounds(30, 700, 80, 30);

		this.infoIconLabel = new JLabel("Help");
		this.infoIconLabel.setIcon(infoIcon);
		this.infoIconLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		this.infoIconLabel.setForeground(new Color(14, 34, 68));

		this.infoIconPanel.add(infoIconLabel);
		this.infoIconPanel.setOpaque(false);

		this.infoIconLabel.addMouseListener(this);

		// Username Label Panel
		this.usernameLabelPanel = new JPanel();
		this.usernameLabelPanel.setBounds(950, 15, 240, 40);
		this.usernameLabelPanel.setBackground(Color.white);
		this.usernameLabelPanel.setOpaque(false);

		// Username Label
		this.usernameLabel = new JLabel(username);
		this.usernameLabel.setForeground(new Color(14, 34, 68));
		this.usernameLabel.setFont(normalFont);

		this.usernameLabelPanel.add(this.usernameLabel);

		// User Input Text Field
		this.userInput = new JTextField(textFieldHolder);
		// this.userInput.setEditable(false);
		this.userInput.setPreferredSize(new Dimension(800, 40));
		this.userInput.setBackground(Color.white);
		this.userInput.setFont(normalFont);

		this.userInput.addMouseListener(this);

		// User Input Panel
		this.userInputPanel = new JPanel();
		this.userInputPanel.setBounds(30, 30, 800, 40);
		this.userInputPanel.setBackground(Color.white);
		this.userInputPanel.setOpaque(false);

		this.userInputPanel.add(userInput);

		// Search Button
		this.searchButton = new JButton("Search");
		this.searchButton.setBackground(new Color(210, 229, 251, 255));
		this.searchButton.setForeground(new Color(14, 34, 68));
		this.searchButton.setOpaque(true);
		this.searchButton.setFont(normalFont);

		this.searchButton.addActionListener(this);

		// Search Button Panel
		this.searchButtonPanel = new JPanel();
		this.searchButtonPanel.setBounds(830, 30, 100, 40);
		this.searchButtonPanel.setBackground(Color.black);
		this.searchButtonPanel.setOpaque(false);

		this.searchButtonPanel.add(searchButton);

		// Logout Button
		this.logoutButton = new JButton("Logout");
		this.logoutButton.setBackground(new Color(210, 229, 251, 255));
		this.logoutButton.setForeground(new Color(14, 34, 68));
		this.logoutButton.setOpaque(true);
		this.logoutButton.setFont(normalFont);

		this.logoutButton.addActionListener(this);

		// Logout Button Panel
		this.logoutButtonPanel = new JPanel();
		this.logoutButtonPanel.setBounds(1000, 60, 150, 60);
		this.logoutButtonPanel.setBackground(Color.white);
		this.logoutButtonPanel.setOpaque(false);

		this.logoutButtonPanel.add(logoutButton);

		// Reload Button
		this.reloadButton = new JButton("Reload");
		this.reloadButton.setBackground(new Color(210, 229, 251, 255));
		this.reloadButton.setForeground(new Color(14, 34, 68));
		this.reloadButton.setOpaque(true);
		this.reloadButton.setFont(normalFont);

		this.reloadButton.addActionListener(this);

		// Reload Button Panel
		this.reloadButtonPanel = new JPanel();
		this.reloadButtonPanel.setBounds(1000, 650, 150, 60);
		this.reloadButtonPanel.setBackground(Color.white);
		this.reloadButtonPanel.setOpaque(false);

		this.reloadButtonPanel.add(reloadButton);

		// Google Map Button
		this.mapButton = new JButton("Map");
		this.mapButton.setBackground(new Color(210, 229, 251, 255));
		this.mapButton.setForeground(new Color(14, 34, 68));
		this.mapButton.setOpaque(true);
		this.mapButton.setFont(normalFont);

		this.mapButton.addActionListener(this);

		// Google Map Button Panel
		this.mapButtonPanel = new JPanel();
		this.mapButtonPanel.setBounds(1000, 585, 150, 60);
		this.mapButtonPanel.setBackground(Color.white);
		this.mapButtonPanel.setOpaque(false);

		this.mapButtonPanel.add(this.mapButton);

		// Doctor Label
		this.dLabel = new JLabel("Doctor: ");
		this.dLabel.setBounds(1010, 270, 200, 30);
		this.dLabel.setForeground(new Color(210, 229, 251, 255));
		this.dLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));

		// List of Doctors Panel
		this.doctorListPanel = new JPanel();
		this.doctorListPanel.setBounds(970, 310, 200, 280);
		this.doctorListPanel.setBackground(Color.white);
		this.doctorListPanel.setOpaque(true);
		this.doctorListPanel.setLayout(null);

		// Doctor 1 Radio BUtton Panel
		this.doctor1RBPanel = new JPanel();
		this.doctor1RBPanel.setBounds(0, 0, 200, 30);
		this.doctor1RBPanel.setBackground(Color.white);

		// Doctor 1 Radio Button
		this.doctor1RButton = new JRadioButton("");
		this.doctor1RButton.setBounds(70, 10, 20, 20);
		this.doctor1RButton.setForeground(new Color(14, 34, 68));
		this.doctor1RButton.setVisible(false);
		this.doctor1RButton.setOpaque(false);

		this.doctor1RBPanel.add(doctor1RButton);

		// Doctor 2 Radio BUtton Panel
		this.doctor2RBPanel = new JPanel();
		this.doctor2RBPanel.setBounds(0, 30, 200, 30);
		this.doctor2RBPanel.setBackground(Color.white);

		// Doctor 2 Radio Button
		this.doctor2RButton = new JRadioButton("");
		this.doctor2RButton.setBounds(10, 30, 20, 20);
		this.doctor2RButton.setForeground(new Color(14, 34, 68));
		this.doctor2RButton.setVisible(false);
		this.doctor2RButton.setOpaque(false);

		this.doctor2RBPanel.add(doctor2RButton);

		// Add Radio Button Panels to the Doctor List Panel
		this.doctorListPanel.add(doctor1RBPanel);
		this.doctorListPanel.add(doctor2RBPanel);

		this.doctor1RButton.addActionListener(this);
		this.doctor2RButton.addActionListener(this);

		// Radio Button Group
		this.dGroup = new ButtonGroup();
		this.dGroup.add(doctor1RButton);
		this.dGroup.add(doctor2RButton);

		// Respond Area
		this.respArea = new JTextArea();
		// this.respArea.setBackground(Color.white);
		// this.respArea.setEditable(false);
		this.respArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		this.respArea.setLineWrap(true);
		this.respArea.setWrapStyleWord(true);

		// Scroll Text Area
		this.scroll = new JScrollPane(this.respArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scroll.setBounds(30, 100, 900, 600);

		// Show a list of history searches
		List<String> aryList = MySQLConnector.searchList(username, portal);
		String[] strList = new String[aryList.size() + 1];
		strList[0] = "clear";
		for (int i = 0; i < strList.length - 1; i++) {
			strList[i + 1] = aryList.get(i);
		}

		this.searchList = new JComboBox(strList);
		this.searchList.setFont(normalFont);
		this.searchList.setForeground(new Color(14, 34, 68));
		this.searchList.setBounds(1000, 130, 150, 60);
		this.searchList.addActionListener(this);

		// Delete Button
		this.deleButton = new JButton("Delete");
		this.deleButton.setBackground(new Color(210, 229, 251, 255));
		this.deleButton.setForeground(new Color(14, 34, 68));
		this.deleButton.setOpaque(true);
		this.deleButton.setFont(normalFont);

		this.deleButton.addActionListener(this);

		// Delete Button Panel
		this.deleButtonPanel = new JPanel();
		this.deleButtonPanel.setBounds(1000, 185, 150, 60);
		this.deleButtonPanel.setBackground(Color.white);
		this.deleButtonPanel.setOpaque(false);

		this.deleButtonPanel.add(deleButton);

		//Table Label
		this.tableLabel = new JLabel(portal);
		
		// Merge
		this.con.add(infoIconPanel);
		this.con.add(userInputPanel);
		this.con.add(usernameLabelPanel);
		this.con.add(searchButtonPanel);
		this.con.add(logoutButtonPanel);
		this.con.add(mapButtonPanel);
		this.con.add(deleButtonPanel);
		this.con.add(reloadButtonPanel);
		this.con.add(searchList);
		this.con.add(scroll);
		this.con.add(dLabel);
		this.con.add(doctorListPanel);
		this.con.add(titleImagePanel);
		this.window.setVisible(true);
		
		System.out.println(portal);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		String tableL = this.tableLabel.getText();
		
		if (event.getSource() == this.logoutButton) {
			this.window.dispose();
			new Portals();
		}

		if (event.getSource() == this.searchButton) {
			Boolean isContains = MedicalWordFilter.containsMedicalTerm(this.userInput.getText());
			this.doctor1RButton.setVisible(false);
			this.doctor2RButton.setVisible(false);
			
			if (this.userInput.getText().equals("") || this.userInput.getText().equalsIgnoreCase(textFieldHolder)) {
				JOptionPane.showMessageDialog(null, "Invalid, please retry.", "Error", JOptionPane.ERROR_MESSAGE);
				userInput.setText(textFieldHolder);
			} else {
				//Check the medical term exception.
				if(isContains) {
					JOptionPane.showMessageDialog(null, "Please wait ", "Message", JOptionPane.PLAIN_MESSAGE);
					String result = ChatGPTAPI.chatGPT(this.userInput.getText());
					String newResult = "";
					String chatOutput = replaceSingleQuote(result);

					String userEnter = replaceSingleQuote(userInput.getText());
					String key = this.searchList.getItemCount() + ". "
							+ replaceSingleQuote(MedicalWordFilter.filterMedicalWords(userEnter));

					String[] doctors = DoctorFilter.filterDoctor(result);
					System.out.println(Arrays.toString(doctors));

					try {
						if (doctors.length == 2) {
							MySQLConnector.insertSearch(usernameLabel.getText(), userEnter, chatOutput, key, doctors[0],
									doctors[1], tableL);
						} else {
							if (doctors.length == 1) {
								MySQLConnector.insertSearch(usernameLabel.getText(), userEnter, chatOutput, key, doctors[0],
										"null", tableL);
							} else {
								if (doctors.length == 0) {
									MySQLConnector.insertSearch(usernameLabel.getText(), userEnter, chatOutput, key, "null",
											"null", tableL);
								}
							}
						}
						newResult = MySQLConnector.searchGPTResp(key, usernameLabel.getText(), tableL);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					updateSearchCheck(this.usernameLabel.getText(), doctors);

					respArea.setText(newResult);
					userInput.setText(textFieldHolder);
				}else {
					JOptionPane.showMessageDialog(null, "The term you entered is invaild or the term is not exist. \nPlease check the spelling.", "Error", JOptionPane.ERROR_MESSAGE);
					userInput.setText(textFieldHolder);
				}
				
			}
		}

		if (doctor1RButton.isSelected()) {
			this.doctor = doctor1RButton.getText();
		}

		if (doctor2RButton.isSelected()) {
			this.doctor = doctor2RButton.getText();
		}

		if (!doctor1RButton.isSelected() && !doctor2RButton.isSelected()) {
			this.doctor = "";
		}

		if (event.getSource() == this.mapButton) {
			String user = this.usernameLabel.getText();
			try {
				new GoogleMapAPI(user, MySQLConnector.userZipCode(user), doctor, tableL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.window.dispose();
		}

		if (event.getSource() == this.reloadButton) {
			this.window.dispose();
			try {
				new PatientSearch(this.usernameLabel.getText(), tableL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (event.getSource() == this.searchList) {
			for (int i = 0; i < this.searchList.getItemCount(); i++) {
				if (this.searchList.getSelectedItem().equals(this.searchList.getItemAt(i))) {
					String search = (String) searchList.getItemAt(i);
					if (search.equals("clear")) {
						respArea.setText("");
						this.doctor1RButton.setText("");
						this.doctor1RButton.setVisible(false);

						this.doctor2RButton.setText("");
						this.doctor2RButton.setVisible(false);
					} else {
						this.dGroup.clearSelection();

						this.doctor1RButton.setText("");
						this.doctor1RButton.setVisible(false);

						this.doctor2RButton.setText("");
						this.doctor2RButton.setVisible(false);
						try {
							respArea.setText(MySQLConnector.searchResult(this.usernameLabel.getText(), search, tableL));
							List<String> doctors = MySQLConnector.doctorList(this.usernameLabel.getText(), search);
							if (!doctors.get(0).equals("null")) {
								this.doctor1RButton.setText(doctors.get(0));
								this.doctor1RButton.setVisible(true);
							}

							if (!doctors.get(1).equals("null")) {
								this.doctor2RButton.setText(doctors.get(1));
								this.doctor2RButton.setVisible(true);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

		if (event.getSource() == this.deleButton) {
			String sKey = (String) searchList.getSelectedItem();
			if (sKey.equals("clear")) {
				JOptionPane.showMessageDialog(null, "Sorry, this can't be deleted", "Message",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure to delete history: " + sKey + "?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					try {
						MySQLConnector.deleteSearch(this.usernameLabel.getText(), sKey, tableL);
						JOptionPane.showMessageDialog(null, "Successfully Deleted", "Message",
								JOptionPane.PLAIN_MESSAGE);
						this.window.dispose();
						new PatientSearch(this.usernameLabel.getText(), tableL);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Nothing happened");
				}
			}
		}
	}

	public static String replaceSingleQuote(String input) {
		if (input.contains("'")) {
			return input.replace("'", "\\'");
		}
		return input;
	}

	public String[] historyList(String username) throws SQLException {
		List<String> aryList = MySQLConnector.searchList(username, "patient");
		String[] strList = new String[aryList.size() + 1];
		strList[0] = "clear";
		for (int i = 0; i < strList.length - 1; i++) {
			strList[i + 1] = aryList.get(i);
		}
		return strList;
	}

	public void updateSearchCheck(String usernaem, String[] doctors) {
		this.searchList.removeAllItems();
		String[] searchHis;
		try {
			searchHis = historyList(this.usernameLabel.getText());
			for (int i = 0; i < searchHis.length; i++) {
				this.searchList.addItem(searchHis[i]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (doctors.length > 0 && doctors.length == 2) {			
			this.doctor1RButton.setVisible(true);
			this.doctor2RButton.setVisible(true);
			
			this.doctor1RButton.setText(doctors[0]);
			this.doctor2RButton.setText(doctors[1]);
			
		} else {
			if (doctors.length > 0 && doctors.length == 1) {
				// Doctor 1 Check Box
				this.doctor1RButton.setText(doctors[0]);
				this.doctor1RButton.setVisible(true);
			}
		}
	}

	public static void searchInstructions() {
		String msg = "1. Getting Started:\r\n"
				+ "       -Open the application and enter your symptoms in the provided text box.\r\n"
				+ "       -Click the \"Search\" button to get information about your symptoms.\r\n"
				+ "2. Reviewing Results:\r\n"
				+ "       -View the results in the main area. If applicable, doctors' names may appear with \r\n"
				+ "       radio buttons.\r\n" + "3. Unclear Reault: \r\n"
				+ "       -If the response is brief or lacks a list without requesting details, \r\n"
				+ "        please resubmit with more specific information for accurate results. \r\n"
				+ "4. Exploring Map:\r\n"
				+ "       -Click the \"Map\" button to see locations related to your search on Google Maps.\r\n"
				+ "5. Checking History:\r\n"
				+ "       -Use the drop-down list to review past searches. Selecting an entry displays its results.\r\n"
				+ "       -Click the \"Reload\" button to update the search hsitory. \r\n" + "6. Deleting History:\r\n"
				+ "       -Remove specific entries by selecting them from the list and clicking \"Delete.\"\r\n"
				+ "7. Logging Out:\r\n" + "       -Click \"Logout\" to exit the application.\r\n"
				+ "8. Refreshing Interface:\r\n" + "       -Hit \"Reload\" to refresh the search interface.\r\n"
				+ "9. Selecting a Doctor:\r\n"
				+ "       -If multiple doctors are suggested, choose one using the radio buttons.\r\n"
				+ "10. Getting Help:\r\n" + "       -Click the \"Help\" icon to access these instructions again.\r\n"
				+ "11. Exiting the Application:\r\n" + "       -Close the window using the exit button.\r\n" + "";
		JOptionPane.showMessageDialog(null, msg, "Search Instructions", JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.infoIconLabel) {
			searchInstructions();
		}

		if (e.getSource() == this.userInput) {
			if (this.userInput.getText().equals(textFieldHolder)) {
				this.userInput.setText("");
			}
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

}