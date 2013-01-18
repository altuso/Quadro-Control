package quadroControl.portControl;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import quadroControl.GUI.MainFrame;
import quadroControl.listener.SerialPortListener;


public class SerialPortController {
	
	CommPortIdentifier serialPortId;
	Enumeration<?> enumComm;
	SerialPort serialPort;
	OutputStream outputStream;
	InputStream inputStream;
	Boolean serialPortGeoeffnet = false;
	private String finalMessage = "";

	int baudrate = 9600;
	int dataBits = SerialPort.DATABITS_8;
	int stopBits = SerialPort.STOPBITS_1;
	int parity = SerialPort.PARITY_NONE;
	private static SerialPortController instance;
	
	private SerialPortController() {

	}
	
	public static synchronized SerialPortController getInstance() {
		if(instance == null) {
			instance = new SerialPortController();
		}
		return instance;
	}
	
	public boolean openSerialPort(String portName)
	{
		Boolean foundPort = false;
		if (serialPortGeoeffnet != false) {
			return false;
		}
		enumComm = CommPortIdentifier.getPortIdentifiers();
		while(enumComm.hasMoreElements()) {
			serialPortId = (CommPortIdentifier) enumComm.nextElement();
			if (portName.contentEquals(serialPortId.getName())) {
				foundPort = true;
				break;
			}
		}
		if (foundPort != true) {
			System.out.println("Serialport nicht gefunden: " + portName);
			return false;
		}
		try {
			serialPort = (SerialPort) serialPortId.open("Öffnen und Senden", 500);
		} catch (PortInUseException e) {
			MainFrame.getInstance().logForDebug("Port belegt");
		}
		try {
			outputStream = serialPort.getOutputStream();
		} catch (IOException e) {
			MainFrame.getInstance().logForDebug("Keinen Zugriff auf OutputStream");
		}
		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
			MainFrame.getInstance().logForDebug("Keinen Zugriff auf InputStream");
		}
		try {
			serialPort.addEventListener(new SerialPortListener());
		} catch (TooManyListenersException e) {
			MainFrame.getInstance().logForDebug("TooManyListenersException für Serialport");
		}
		serialPort.notifyOnDataAvailable(true);
		try {
			serialPort.setSerialPortParams(baudrate, dataBits, stopBits, parity);
		} catch(UnsupportedCommOperationException e) {
			MainFrame.getInstance().logForDebug("Konnte Schnittstellen-Paramter nicht setzen");
		}
		
		serialPortGeoeffnet = true;
		return true;
	}
	
	public void closeSerialPort()
	{
		if ( serialPortGeoeffnet == true) {
			serialPort.close();
			serialPortGeoeffnet = false;
		} else {
		}
	}
	
	public void refreshSerialPort()
	{
		if (serialPortGeoeffnet != false) {
			return;
		}
		MainFrame.getInstance().portComboBox.removeAllItems();
		enumComm = CommPortIdentifier.getPortIdentifiers();
		while(enumComm.hasMoreElements()) {
			serialPortId = (CommPortIdentifier) enumComm.nextElement();
			if (serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				MainFrame.getInstance().logForDebug("Found:" + serialPortId.getName());
				MainFrame.getInstance().portComboBox.addItem(serialPortId.getName());
			}
		}
	}
	
	public void sendSerialPort(String nachricht)
	{
		MainFrame.getInstance().logForDebug("Send: " + nachricht);
		if (serialPortGeoeffnet != true)
			return;
		try {
			outputStream.write(nachricht.getBytes());
		} catch (IOException e) {
			MainFrame.getInstance().logForDebug("Fehler beim Senden");
		}
	}
	
	public void serialPortDatenVerfuegbar() {
		try {
			byte[] data = new byte[150];
			int num;
			String recievedMessage;
			while(inputStream.available() > 0) {
				num = inputStream.read(data, 0, data.length);
				recievedMessage = new String(data, 0, num);
				
				if(! recievedMessage.endsWith("A")) {
					finalMessage = finalMessage + recievedMessage;
				} 
				else if (recievedMessage.endsWith("A")) {
					finalMessage = finalMessage + recievedMessage;
					System.out.println("Empfange: "+ finalMessage);
					MainFrame.getInstance().setRecievedMessage(finalMessage);
					finalMessage = "";
				}
			}
		} catch (IOException e) {
			MainFrame.getInstance().logForDebug("Fehler beim Lesen empfangener Daten");
		} 
	}

	public int getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(int baudrate) {
		this.baudrate = baudrate;
	}
}
