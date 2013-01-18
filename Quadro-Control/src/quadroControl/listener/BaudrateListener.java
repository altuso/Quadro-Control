package quadroControl.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quadroControl.GUI.MainFrame;
import quadroControl.portControl.SerialPortController;

public class BaudrateListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			SerialPortController.getInstance().setBaudrate(Integer.parseInt(MainFrame.getInstance().getBaudrateFieldTest()));
		} catch (Exception ex) {
			MainFrame.getInstance().logForDebug("Nur Zahlen als baudrate erlaubt!");
		}
		
	}

}
