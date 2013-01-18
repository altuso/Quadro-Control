package quadroControl.listener;

import quadroControl.portControl.SerialPortController;
import gnu.io.SerialPortEvent;

public class SerialPortListener implements gnu.io.SerialPortEventListener{

	@Override
	public void serialEvent(SerialPortEvent event) {

		switch (event.getEventType()) {
		case SerialPortEvent.DATA_AVAILABLE:
			SerialPortController.getInstance().serialPortDatenVerfuegbar();
			break;
		case SerialPortEvent.BI:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.FE:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
		case SerialPortEvent.PE:
		case SerialPortEvent.RI:
		default:
		}
	}

}
