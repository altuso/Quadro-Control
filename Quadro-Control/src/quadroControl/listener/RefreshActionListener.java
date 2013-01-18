package quadroControl.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quadroControl.portControl.SerialPortController;

public class RefreshActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		SerialPortController.getInstance().refreshSerialPort();
	}

}
