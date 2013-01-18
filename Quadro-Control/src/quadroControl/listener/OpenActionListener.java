package quadroControl.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quadroControl.GUI.MainFrame;
import quadroControl.portControl.SerialPortController;

public class OpenActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().portOpened();
		SerialPortController.getInstance().openSerialPort((String) MainFrame.getInstance().getSelectedPort());
	}

}
