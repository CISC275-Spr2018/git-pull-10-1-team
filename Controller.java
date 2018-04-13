import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	Timer timer;
	final int drawDelay = 20; // msec
	Action drawAction;

	public Controller() {
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (view.isdirectFlag()) {
					model.direct_button();
					view.directFlag=false;
				}
				if (view.isFlag()) {
				} else {
					model.updateLocationAndDirection();
				}
				// increment the x and y coordinates, alter direction if necessary
				view.update(model.getX(), model.getY(), model.getDirect());
				view.rollback(model.getXloc2(), model.getYloc2());
				view.repaint();
			}
		};;
	}

	// run the simulation
//	public void start() {
//		for (int i = 0; i < 5000; i++) {
////			timer.start();
//			
//			
//			if (view.isdirectFlag()) {
//				model.direct_button();
//				view.directFlag=false;
//			}
//			if (view.isFlag()) {
//			} else {
//				model.updateLocationAndDirection();
//			}
//			// increment the x and y coordinates, alter direction if necessary
//			view.update(model.getX(), model.getY(), model.getDirect());
//			view.rollback(model.getXloc2(), model.getYloc2());
//		}
//			
//	}
}
