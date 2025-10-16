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


public class StudentSearch implements ActionListener, MouseListener {
	private JFrame window;
	private JPanel usernameLabelPanel, userInputPanel, reloadButtonPanel, searchButtonPanel, logoutButtonPanel, deleButtonPanel;
	private JLabel usernameLabel, tableLabel;
	private JTextField userInput;
	private JTextArea respArea;
	private JScrollPane scroll;
	private JComboBox searchList;
	private JButton searchButton, logoutButton, reloadButton, deleButton;

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

	private String textFieldHolder = "Please enter a medical term";

	
	public static void main(String[] args) {
		try {
			new StudentSearch("jhu17", "Student");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public StudentSearch(String username, String portal) throws SQLException {
		// Frame
		this.window = new JFrame("Student Search");
		this.window.setSize(1120, 680);
		// this.window.getContentPane().setBackground(new Color(74,146,218,255));
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLayout(null);
		this.window.setResizable(false);
		this.con = window.getContentPane();

		// Panel Background
		this.titleImage = new ImageIcon(getClass().getClassLoader().getResource("StudentSearch.png"));

		this.titleImagePanel = new JPanel();
		this.titleImagePanel.setBounds(0, 0, 1107, 643);

		this.titleImageLabel = new JLabel();
		this.titleImageLabel.setIcon(titleImage);
		this.titleImagePanel.add(this.titleImageLabel);

		// Info Icon
		this.infoIcon = new ImageIcon(getClass().getClassLoader().getResource("InfoIcon(S).png"));

		this.infoIconPanel = new JPanel();
		this.infoIconPanel.setBounds(10, 610, 80, 30);

		this.infoIconLabel = new JLabel("Help");
		this.infoIconLabel.setIcon(infoIcon);
		this.infoIconLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		this.infoIconLabel.setForeground(new Color(14, 34, 68));

		this.infoIconPanel.add(infoIconLabel);
		this.infoIconPanel.setOpaque(false);

		this.infoIconLabel.addMouseListener(this);

		// Username Label Panel
		this.usernameLabelPanel = new JPanel();
		this.usernameLabelPanel.setBounds(900, 15, 240, 40);
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
		this.userInputPanel.setBounds(10, 10, 800, 40);
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
		this.searchButtonPanel.setBounds(810, 10, 100, 40);
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
		this.logoutButtonPanel.setBounds(950, 60, 150, 60);
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
		this.reloadButtonPanel.setBounds(950, 560, 150, 60);
		this.reloadButtonPanel.setBackground(Color.white);
		this.reloadButtonPanel.setOpaque(false);

		this.reloadButtonPanel.add(reloadButton);

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
		this.scroll.setBounds(10, 60, 900, 550);

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
		this.searchList.setBounds(950, 130, 150, 60);
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
		this.deleButtonPanel.setBounds(950, 185, 150, 60);
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
		this.con.add(deleButtonPanel);
		this.con.add(reloadButtonPanel);
		this.con.add(searchList);
		this.con.add(scroll);
		this.con.add(titleImagePanel);
		this.window.setVisible(true);
		
		System.out.println(portal);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		String portal = this.tableLabel.getText();
		
		if (event.getSource() == this.logoutButton) {
			this.window.dispose();
			new Portals();
		}

		if (event.getSource() == this.searchButton) {

			if (this.userInput.getText().equals("") || this.userInput.getText().equalsIgnoreCase(textFieldHolder)) {
				JOptionPane.showMessageDialog(null, "Invalid, please retry.", "Error", JOptionPane.ERROR_MESSAGE);
				userInput.setText(textFieldHolder);
			} else {
				Boolean isInFile = MedicalWordFilter.isTermInFile(this.userInput.getText());
				if(isInFile) {
					JOptionPane.showMessageDialog(null, "Please wait ", "Message", JOptionPane.PLAIN_MESSAGE);
					String result = MedicalQuestion.chatGPTResponse(this.userInput.getText());
					String newResult = "";
					String chatOutput = replaceSingleQuote(result);

					String userEnter = replaceSingleQuote(userInput.getText());
					String key = this.searchList.getItemCount() + ". "
							+ replaceSingleQuote(MedicalWordFilter.filterMedicalWords(userEnter));
					
					try {
						MySQLConnector.insertSearch(usernameLabel.getText(), userEnter, chatOutput, key,"","", portal);
						newResult = MySQLConnector.searchGPTResp(key, usernameLabel.getText(), portal);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					updateSearchCheck(this.usernameLabel.getText());

					respArea.setText(newResult);
					userInput.setText(this.textFieldHolder);
				}else {
					//this.window.dispose();
					JOptionPane.showMessageDialog(null, "The term you entered is invaild or the term is not exist. "
							+ "\nPlease check the spelling.", "Error", JOptionPane.ERROR_MESSAGE);
					userInput.setText(textFieldHolder);
				}
				
			}
		}

		if (event.getSource() == this.reloadButton) {
			this.window.dispose();
			try {
				new StudentSearch(this.usernameLabel.getText(), portal);
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
					} else {
						try {
							respArea.setText(MySQLConnector.searchResult(this.usernameLabel.getText(), search, portal));
							List<String> doctors = MySQLConnector.doctorList(this.usernameLabel.getText(), search);
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
						MySQLConnector.deleteSearch(this.usernameLabel.getText(), sKey, portal);
						JOptionPane.showMessageDialog(null, "Successfully Deleted", "Message",
								JOptionPane.PLAIN_MESSAGE);
						this.window.dispose();
						new StudentSearch(this.usernameLabel.getText(), portal);
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
		List<String> aryList = MySQLConnector.searchList(username, "student");
		String[] strList = new String[aryList.size() + 1];
		strList[0] = "clear";
		for (int i = 0; i < strList.length - 1; i++) {
			strList[i + 1] = aryList.get(i);
		}
		return strList;
	}

	public void updateSearchCheck(String username) {
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
	}

	public static void searchInstructions() {
		String msg = "1. Getting Started:\r\n"
				+ "       -Open the application and enter medical term in the provided text box.\r\n"
				+ "       -Click the \"Search\" button to get general informations about the word.\r\n"
				+ "2. Reviewing Results:\r\n"
				+ "       -View the results in the main area. \r\n"
				+ " 3. Unclear Reault: \r\n"
				+ "       -If the response is brief or lacks a list without requesting details, \r\n"
				+ "        please resubmit with more specific information for accurate results. \r\n"
				+ "4. Checking History:\r\n"
				+ "       -Use the drop-down list to review past searches. Selecting an entry displays its results.\r\n"
				+ "       -Click the \"Reload\" button to update the search hsitory. \r\n" + "6. Deleting History:\r\n"
				+ "       -Remove specific entries by selecting them from the list and clicking \"Delete.\"\r\n"
				+ "5. Logging Out:\r\n" + "       -Click \"Logout\" to exit the application.\r\n"
				+ "6. Refreshing Interface:\r\n" + "       -Hit \"Reload\" to refresh the search interface.\r\n"
				+ "7. Getting Help:\r\n" + "       -Click the \"Help\" icon to access these instructions again.\r\n"
				+ "8. Exiting the Application:\r\n" + "       -Close the window using the exit button.\r\n" + "";
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

