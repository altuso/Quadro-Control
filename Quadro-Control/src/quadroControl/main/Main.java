package quadroControl.main;

import quadroControl.GUI.MainFrame;
import quadroControl.portControl.SerialPortController;

public class Main {
	
	public static void main(String[] args) {
		MainFrame.getInstance();
		SerialPortController.getInstance().refreshSerialPort();
	}
}
