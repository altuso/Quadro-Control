package quadroControl.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quadroControl.GUI.MainFrame;

public class DebugListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(MainFrame.getInstance().isDebugEnabled() == true) {
			MainFrame.getInstance().disableDebug();
		} else if(MainFrame.getInstance().isDebugEnabled() == false) {
			MainFrame.getInstance().enableDebug();
		}
		
	}

}
