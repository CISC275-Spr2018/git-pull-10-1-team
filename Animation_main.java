import java.awt.EventQueue;

import javax.swing.Timer;

public class Animation_main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
			Controller c = new Controller();
			Timer timer= new Timer(c.drawDelay,c.drawAction);
			timer.start();
		}
	});

	}
}
