package quadroControl.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quadroControl.GUI.MainFrame;
import quadroControl.portControl.SerialPortController;

public class SendActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String message = MainFrame.getInstance().assembleMessage();
		if(! message.equals("")) {
			SerialPortController.getInstance().sendSerialPort(message);
		}
	}
}
