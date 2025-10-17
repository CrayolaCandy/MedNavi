import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.cef.CefApp;
import org.cef.CefApp.CefAppState;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefAppHandlerAdapter;

import org.cef.browser.CefMessageRouter;
import tests.detailed.handler.MessageRouterHandler;
import tests.detailed.handler.MessageRouterHandlerEx;

public class GoogleMapAPI implements ActionListener, MouseListener {
	private JFrame window;
	private JPanel buttonsPanel, refreshButtonPanel, backButtonPanel;
	private JButton refeshButton, backButton;

	// Label Icon
	private ImageIcon infoIcon;
	private JPanel infoIconPanel;
	private JLabel infoIconLabel, tableLabel;

	
	
	public static void main(String args[]) {
		new GoogleMapAPI("jhu17", "11355", "Cardiology", "patient");
	}
	

	public GoogleMapAPI(String userName, String zipCode, String doctor, String table) {
		/*CefApp.addAppHandler(new CefAppHandlerAdapter(null) {
			@Override
			public void stateHasChanged(CefAppState state) {
				if (state == CefAppState.TERMINATED) {
					System.exit(0);
				}
			}
		});
		CefSettings settings = new CefSettings();
		settings.windowless_rendering_enabled = false;
		CefApp cefApp = CefApp.getInstance(settings);
		CefClient client = cefApp.createClient();
		CefMessageRouter msgRouter = CefMessageRouter.create();
		msgRouter.addHandler(new MessageRouterHandler(), true);
		msgRouter.addHandler(new MessageRouterHandlerEx(client), false);
		client.addMessageRouter(msgRouter);
		 */
	    CefApp cefApp = CefAppManager.getCefAppInstance();
	    CefClient client = CefAppManager.getClientInstance();
		
		GoogleMapHTMLContent.HTMLMapContent(zipCode, doctor); // Replace with the actual file path
		File htmlFile = new File("src/GoogleMap.html");
		String htmlURL = htmlFile.toURI().toString();

		CefBrowser browser = client.createBrowser(htmlURL, false, false);
		Component browserComponent = browser.getUIComponent();

		// Frame
		this.window = new JFrame("Google Map");
		this.window.setSize(1200, 1000);
		this.window.getContentPane().setBackground(new Color(100, 136, 234));
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.window.setLayout(null);
		this.window.setResizable(false);

		// Info Icon
		this.infoIcon = new ImageIcon(getClass().getClassLoader().getResource("InfoIcon(S).png"));

		this.infoIconPanel = new JPanel();
		this.infoIconPanel.setBounds(1110, 0, 80, 30);

		this.infoIconLabel = new JLabel("Help");
		this.infoIconLabel.setIcon(infoIcon);
		this.infoIconLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		this.infoIconLabel.setForeground(new Color(14, 34, 68));

		this.infoIconPanel.add(infoIconLabel);
		this.infoIconPanel.setOpaque(false);

		this.infoIconLabel.addMouseListener(this);

		// Buttons Panel
		this.buttonsPanel = new JPanel();
		this.buttonsPanel.setPreferredSize(new Dimension(1200, 30));
		this.buttonsPanel.setLayout(null);
		this.buttonsPanel.setBackground(Color.white);
		this.buttonsPanel.setOpaque(true);

		// Register Button Panel
		this.refreshButtonPanel = new JPanel();
		this.refreshButtonPanel.setBounds(0, 0, 80, 30);
		this.refreshButtonPanel.setBackground(Color.black);
		this.refreshButtonPanel.setOpaque(false);

		// Refresh Button
		this.refeshButton = new JButton("Refresh");
		this.refeshButton.setBounds(0, 0, 50, 50);
		this.refeshButton.setBackground(Color.black);
		this.refeshButton.setForeground(Color.black);
		this.refeshButton.setOpaque(false);

		this.refeshButton.addActionListener(e -> browser.reload());

		// Back Button Panel
		this.backButtonPanel = new JPanel();
		this.backButtonPanel.setBounds(80, 0, 70, 30);
		this.backButtonPanel.setBackground(Color.black);
		this.backButtonPanel.setOpaque(false);

		// Back Button
		this.backButton = new JButton("Back");
		this.backButton.setBounds(0, 0, 50, 50);
		this.backButton.setBackground(Color.black);
		this.backButton.setForeground(Color.black);
		this.backButton.setOpaque(false);

		// Table Label
		this.tableLabel = new JLabel(table);

		String tableL = this.tableLabel.getText();

		this.backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new PatientSearch(userName, tableL);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				window.dispose();
			}
		});

		this.backButtonPanel.add(backButton);
		this.refreshButtonPanel.add(refeshButton);

		this.buttonsPanel.add(refreshButtonPanel);
		this.buttonsPanel.add(backButtonPanel);
		this.buttonsPanel.add(infoIconPanel);

		// window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		window.add(browserComponent, BorderLayout.CENTER);
		window.add(buttonsPanel, BorderLayout.SOUTH);
		window.setLocationRelativeTo(null);
		this.window.setVisible(true);
		mapInstructions();
	}

	public static void mapInstructions() {
		String msg = "1. Getting Started:\r\n" + "       -Launch the application to view Google Maps.\r\n"
				+ "       -The default location is based on your ZIP code.\r\n" + "2. Searching for Places:\r\n"
				+ "       -Use the search box to find places related to your needs.\r\n"
				+ "       -Click the search box and select the place to navigate.\r\n" + "3. Navigating the Map:\r\n"
				+ "       -Explore the map with markers indicating search results.\r\n" + "4. Refreshing the Map:\r\n"
				+ "       -Click the \"Refresh\" button at the bottom to reload the map if needed.\r\n"
				+ "5. Returning to Search:\r\n"
				+ "       -To go back to the medical search application, click the \"Back\" button.\r\n"
				+ "6. Troubleshooting:\r\n"
				+ "       -If the map is blank and/or freezes, use the \"Refresh\" button to reload it.\r\n"
				+ "7. Getting Help:\r\n" + "       -Click the \"Help\" icon to access these instructions again.\r\n"
				+ "8. Exiting the Application:\r\n" + "       -Close the window using the exit button.\r\n";
		JOptionPane.showMessageDialog(null, msg, "Google Map Instructions", JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.infoIconLabel) {
			mapInstructions();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
