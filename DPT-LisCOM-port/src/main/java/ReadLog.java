import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ReadLog {

    private static SerialPort serialPort;

    public static void main(String[] args) {
        serialPort = new SerialPort("COM3");

        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            serialPort.addEventListener(new EventListener());
        }catch (SerialPortException ex){
            System.out.println(ex);
        }

    }

    private static class EventListener implements SerialPortEventListener{

        public void serialEvent(SerialPortEvent event){
            if(event.isRXCHAR() && event.getEventValue()>0)
            try{
                String data = serialPort.readString(event.getEventValue());
                System.out.println(data);
            }catch (SerialPortException ex){
                System.out.println(ex);
            }
        }


    }
}

