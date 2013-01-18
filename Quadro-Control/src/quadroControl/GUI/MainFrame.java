package quadroControl.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import quadroControl.Entity.Graph;
import quadroControl.listener.BaudrateListener;
import quadroControl.listener.CloseActionListener;
import quadroControl.listener.DebugListener;
import quadroControl.listener.GraphListener;
import quadroControl.listener.OpenActionListener;
import quadroControl.listener.RefreshActionListener;
import quadroControl.listener.SendActionListener;
import quadroControl.listener.WindowListener;

public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -372058005089345999L;

	private JPanel mainPanel, optionsPanel, recievePanel, sendPanel, graphPanel, graphScreen;
	private JButton openPortButton, closePortButton, refreshPortsButton, sendButton, debugButton, baudrateButton, testButton;
	private JTextField recievedField1, recievedField2, recievedField3, recievedField4, recievedField5, sendField1, sendField2, sendField3, sendField4, sendField5;
	private JLabel baudrateLabel, kLabel, pLabel, iLabel, dLabel, iLimitLabel;
	private JScrollPane debugPanel;
	private Graph graph1;
	private JCheckBox checkSendMessage;
	public JComboBox<String> portComboBox;
	private JComboBox<String> baudrateComboBox;
	private Dimension textFieldDimension;
	private JTextArea debugTextArea;
	private static MainFrame instance;
	private String[] baudrates = { "300", "600", "1200", "2400", "4800", "9600", "14400",
			"19200", "28800", "38400", "56000", "57600", "115200", "128000", "256000"};
	private String version = "V1.3.0";

	public static synchronized MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	private MainFrame() {
		initComponents();
		setTitle("Quadro-Control "+ version);
		addWindowListener(new WindowListener());
		setVisible(true);
		setResizable(false);
		portClosed();
		pack();
		setLocationRelativeTo(null);
		disableDebug();
	}

	private void initComponents() {

		GridBagConstraints c = new GridBagConstraints();

		graph1 = new Graph(Color.GREEN);
		graph1.setyPos(150);

		debugTextArea = new JTextArea();
		debugTextArea.setRows(5);

		mainPanel = new JPanel(new GridBagLayout());
		optionsPanel = new JPanel(new GridBagLayout());
		recievePanel = new JPanel(new GridBagLayout());
		sendPanel = new JPanel(new GridBagLayout());
		graphPanel = new JPanel(new GridBagLayout());
		graphScreen = new JPanel();
		graphScreen.setPreferredSize(new Dimension(getWidth(), 300));
		graphScreen.setBackground(Color.WHITE);

		debugPanel = new JScrollPane(debugTextArea);
		optionsPanel.setBorder(BorderFactory.createTitledBorder("Optionen"));
		recievePanel.setBorder(BorderFactory.createTitledBorder("Empfangen"));
		sendPanel.setBorder(BorderFactory.createTitledBorder("Senden"));
		debugPanel.setBorder(BorderFactory.createTitledBorder("Debug"));
		graphPanel.setBorder(BorderFactory.createTitledBorder("Graph"));

		openPortButton = new JButton("Öffnen");
		openPortButton.addActionListener(new OpenActionListener());
		closePortButton = new JButton("Schließen");
		closePortButton.addActionListener(new CloseActionListener());
		refreshPortsButton = new JButton("Aktualisieren");
		refreshPortsButton.addActionListener(new RefreshActionListener());
		sendButton = new JButton("Senden");
		sendButton.addActionListener(new SendActionListener());
		debugButton = new JButton("Debug");
		debugButton.addActionListener(new DebugListener());
		baudrateButton = new JButton("Ändern");
		baudrateButton.addActionListener(new BaudrateListener());
		testButton = new JButton("TEST");
		testButton.addActionListener(new GraphListener());

		checkSendMessage = new JCheckBox("Überprüfen");
		portComboBox = new JComboBox<>();
		baudrateLabel = new JLabel("baudrate: ");
		kLabel = new JLabel("            K");
		pLabel = new JLabel("            P");
		iLabel = new JLabel("             I");
		dLabel = new JLabel("            D");
		iLimitLabel = new JLabel("       I-Bregenzung");
		textFieldDimension = new Dimension(70, 22);
		baudrateComboBox = new JComboBox<String>(baudrates);
		baudrateComboBox.setSelectedItem("9600");
		recievedField1 = new JTextField();
		recievedField2 = new JTextField();
		recievedField3 = new JTextField();
		recievedField4 = new JTextField();
		recievedField5 = new JTextField();
		sendField1 = new JTextField();
		sendField2 = new JTextField();
		sendField3 = new JTextField();
		sendField4 = new JTextField();
		sendField5 = new JTextField();

		recievedField1.setPreferredSize(textFieldDimension);
		recievedField2.setPreferredSize(textFieldDimension);
		recievedField3.setPreferredSize(textFieldDimension);
		recievedField4.setPreferredSize(textFieldDimension);
		recievedField5.setPreferredSize(textFieldDimension);
		recievedField1.setEnabled(false);
		recievedField2.setEnabled(false);
		recievedField3.setEnabled(false);
		recievedField4.setEnabled(false);
		recievedField5.setEnabled(false);
		recievedField1.setHorizontalAlignment(JTextField.CENTER);
		recievedField2.setHorizontalAlignment(JTextField.CENTER);
		recievedField3.setHorizontalAlignment(JTextField.CENTER);
		recievedField4.setHorizontalAlignment(JTextField.CENTER);
		recievedField5.setHorizontalAlignment(JTextField.CENTER);
		recievedField1.setDisabledTextColor(Color.BLACK);
		recievedField2.setDisabledTextColor(Color.BLACK);
		recievedField3.setDisabledTextColor(Color.BLACK);
		recievedField4.setDisabledTextColor(Color.BLACK);
		recievedField5.setDisabledTextColor(Color.BLACK);

		sendField1.setPreferredSize(textFieldDimension);
		sendField2.setPreferredSize(textFieldDimension);
		sendField3.setPreferredSize(textFieldDimension);
		sendField4.setPreferredSize(textFieldDimension);
		sendField5.setPreferredSize(textFieldDimension);
		sendField1.setHorizontalAlignment(JTextField.CENTER);
		sendField2.setHorizontalAlignment(JTextField.CENTER);
		sendField3.setHorizontalAlignment(JTextField.CENTER);
		sendField4.setHorizontalAlignment(JTextField.CENTER);
		sendField5.setHorizontalAlignment(JTextField.CENTER);

		debugPanel.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		debugPanel.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		debugPanel.setViewportView(debugTextArea);

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.insets = new Insets(5, 5, 5, 5);
		optionsPanel.add(portComboBox, c);

		c.gridx = 1;
		c.weightx = 0;
		optionsPanel.add(openPortButton, c);

		c.gridx = 2;
		optionsPanel.add(closePortButton, c);

		c.gridx = 3;
		optionsPanel.add(refreshPortsButton, c);

		c.gridx = 0;
		c.gridy = 1;
		optionsPanel.add(baudrateLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		optionsPanel.add(baudrateComboBox, c);

		c.gridx = 2;
		c.gridy = 1;
		optionsPanel.add(baudrateButton, c);

		c.gridx = 3;
		c.gridy = 1;
		optionsPanel.add(debugButton, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		mainPanel.add(optionsPanel, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		recievePanel.add(kLabel, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		recievePanel.add(pLabel, c);

		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 1;
		recievePanel.add(iLabel, c);

		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 1;
		recievePanel.add(dLabel, c);

		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 1;
		recievePanel.add(iLimitLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		recievePanel.add(recievedField1, c);

		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		recievePanel.add(recievedField2, c);

		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		recievePanel.add(recievedField3, c);

		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		recievePanel.add(recievedField4, c);

		c.gridx = 4;
		c.gridy = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		recievePanel.add(recievedField5, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		mainPanel.add(recievePanel, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		sendPanel.add(sendField1, c);

		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		sendPanel.add(sendField2, c);

		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		sendPanel.add(sendField3, c);

		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		sendPanel.add(sendField4, c);

		c.gridx = 4;
		c.gridy = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		sendPanel.add(sendField5, c);

		c.gridx = 5;
		c.gridy = 0;
		c.weightx = 0;
		sendPanel.add(sendButton, c);

		c.gridx = 5;
		c.gridy = 1;
		c.weightx = 1;
		sendPanel.add(checkSendMessage, c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		mainPanel.add(sendPanel, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		graphPanel.add(testButton, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		graphPanel.add(graphScreen, c);

		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		mainPanel.add(graphPanel, c);

		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		mainPanel.add(debugPanel, c);


		setContentPane(mainPanel);
	}

	public String getSelectedPort() {
		return (String) portComboBox.getSelectedItem();
	}

	public void portOpened() {
		openPortButton.setEnabled(false);
		refreshPortsButton.setEnabled(false);
		sendButton.setEnabled(true);
		closePortButton.setEnabled(true);	
	}

	public void portClosed() {
		openPortButton.setEnabled(true);
		refreshPortsButton.setEnabled(true);
		closePortButton.setEnabled(false);
		sendButton.setEnabled(false);
	}

	public String assembleMessage() {

		if(sendField1.getText().equals("") && sendField2.getText().equals("") && sendField3.getText().equals("") && sendField4.getText().equals("") && sendField5.getText().equals("")) {
			return "";
		} else {
			return sendField1.getText() + "," + sendField2.getText() + "," + sendField3.getText() + "," + sendField4.getText() + "," + sendField5.getText() + "A";
		}

	}

	public void setRecievedMessage(String recievedMessage) {
		String[] splitString = recievedMessage.split(",");

		if(splitString.length > 0) {
			try {
				recievedField1.setText(splitString[0]);
				updateGraph(Double.parseDouble(splitString[0]));
				renderGraph();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(splitString.length > 1) {
			recievedField2.setText(splitString[1]);
		}
		if(splitString.length > 2) {
			recievedField3.setText(splitString[2]);
		}
		if(splitString.length > 3) {
			recievedField4.setText(splitString[3]);
		}
		if(splitString.length > 4) {
			recievedField5.setText(splitString[4].replace("A", ""));
		}
	}

	public String getBaudrateFieldTest() {
		return baudrateComboBox.getSelectedItem().toString();
	}

	public void enableDebug() {
		debugPanel.setVisible(true);
		repaint();
		pack();
	}

	public void disableDebug() {
		debugPanel.setVisible(false);
		repaint();
		pack();
	}

	public boolean isDebugEnabled() {
		if(debugPanel.isVisible() == true) {
			return true;
		} else {
			return false;
		}
	}

	public void logForDebug(String message) {
		debugTextArea.append(message + "\n");	
	}

	public boolean checkMessageIsSelected() {
		return checkSendMessage.isSelected();
	}

	public void renderGraph() {

		Graphics g = graphScreen.getGraphics();
		g.setColor(graph1.getColor());
		g.drawLine(graph1.getxPos(), graph1.getyPos(), graph1.getNewXPos(), graph1.getNewYPos());
		
		g.setColor(Color.WHITE);
		g.fillRect(graph1.getNewXPos() + 1, 0, 10, 300);
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(0, graphScreen.getHeight() / 2, graphScreen.getWidth() , graphScreen.getHeight() / 2);
		
		g.dispose();
	}

	public void updateGraph(double newYPos) {
		int speed = 10;

		graph1.setyPos(graph1.getNewYPos());
		graph1.setxPos(graph1.getNewXPos());
		if(graph1.getxPos() > graphScreen.getWidth()) {
			graph1.setxPos(-1 - speed);
		} 
		graph1.setNewXPos(graph1.getxPos() + speed);
		if(newYPos > 0 ) {
			graph1.setNewYPos(graphScreen.getHeight() / 2 - (int) newYPos);
		} else if(newYPos < 0) {
			graph1.setNewYPos(graphScreen.getHeight() / 2 - (int) newYPos);
		} else if (newYPos == 0) {
			graph1.setNewYPos(graphScreen.getHeight() / 2);
		}
	}

}
