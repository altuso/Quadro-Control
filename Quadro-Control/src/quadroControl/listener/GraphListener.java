package quadroControl.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quadroControl.GUI.MainFrame;

public class GraphListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getInstance().updateGraph(-10.53);
		MainFrame.getInstance().renderGraph();
		
	}

}
