package quadroControl.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quadroControl.GUI.MainFrame;
import quadroControl.portControl.SerialPortController;

public class CloseActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().portClosed();
		SerialPortController.getInstance().closeSerialPort();
	}

}
