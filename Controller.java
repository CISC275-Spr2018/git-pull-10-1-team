import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
	final int drawDelay = 30; // msec
	Action drawAction;

	// Set of currently pressed keys
	private final Set<Integer> pressed = new HashSet<Integer>();

	public Controller() {
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		view.getStop_button().addKeyListener(new ArrowKey());
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (view.isFlag()) {
				} else {
					model.updateLocationAndDirection();
				}
				// increment the x and y coordinates, alter direction if necessary
				view.update(model.getX(), model.getY(), model.getDirect());
				view.rollback(model.getXloc2(), model.getYloc2());
				// view.repaint();
			}
		};
		;

	}

	class ArrowKey implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			pressed.add(e.getKeyCode());
			if (e.getKeyCode() == 70) {
				// Fire
				view.setAct(Action_Enum.FIRE);
			}
			if (e.getKeyCode() == 74) {
				// Jump
				view.setAct(Action_Enum.JUMP);
			}

			if (pressed.size() > 1) {
			       if (pressed.contains(KeyEvent.VK_LEFT) && pressed.contains(KeyEvent.VK_UP)&&pressed.contains(KeyEvent.VK_Z)){
			    	   		// UP-LEFT
						model.setyIncr((-1) * Math.abs(model.getyIncr2()));
						model.setxIncr((-1) * Math.abs(model.getxIncr2()));
						model.setFlag_north(true);
						model.setFlag_south(false);
						model.setFlag_west(true);
						model.setFlag_east(false);
			    	   
			        } else if (pressed.contains(KeyEvent.VK_UP) && pressed.contains(KeyEvent.VK_RIGHT)&&pressed.contains(KeyEvent.VK_Z)) {
			        		// UP-RIGHT
						model.setyIncr((-1) * Math.abs(model.getyIncr2()));
						model.setxIncr(Math.abs(model.getxIncr2()));
						model.setFlag_north(true);
						model.setFlag_south(false);
						model.setFlag_west(false);
						model.setFlag_east(true);
			        	
			        } else if (pressed.contains(KeyEvent.VK_RIGHT) && pressed.contains(KeyEvent.VK_DOWN)&&pressed.contains(KeyEvent.VK_Z)) {
		        		// DOWN-RIGHT
					model.setyIncr(Math.abs(model.getyIncr2()));
					model.setxIncr(Math.abs(model.getxIncr2()));
					model.setFlag_north(false);
					model.setFlag_south(true);
					model.setFlag_west(false);
					model.setFlag_east(true);
			        	
			        } else if (pressed.contains(KeyEvent.VK_LEFT) && pressed.contains(KeyEvent.VK_DOWN)&&pressed.contains(KeyEvent.VK_Z)) {
		        		// DOWN-LEFT
					model.setyIncr(Math.abs(model.getyIncr2()));
					model.setxIncr((-1) *Math.abs(model.getxIncr2()));
					model.setFlag_north(false);
					model.setFlag_south(true);
					model.setFlag_west(true);
					model.setFlag_east(false);
			
			        }
			} else {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					// UP
					view.setAct(Action_Enum.FORWARD);
					model.setyIncr((-1) * Math.abs(model.getyIncr2()));
					model.setxIncr(0);
					model.setFlag_north(true);
					model.setFlag_south(false);
					model.setFlag_west(false);
					model.setFlag_east(false);

				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					// DOWN
					view.setAct(Action_Enum.FORWARD);
					model.setyIncr(Math.abs(model.getyIncr2()));
					model.setxIncr(0);
					model.setFlag_north(false);
					model.setFlag_south(true);
					model.setFlag_west(false);
					model.setFlag_east(false);


				}

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					// LEFT
					view.setAct(Action_Enum.FORWARD);
					model.setxIncr((-1) * Math.abs(model.getxIncr2()));
					model.setyIncr(0);
					model.setFlag_north(false);
					model.setFlag_south(false);
					model.setFlag_west(true);
					model.setFlag_east(false);

				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// RIGHT
					view.setAct(Action_Enum.FORWARD);
					model.setxIncr(Math.abs(model.getxIncr2()));
					model.setyIncr(0);
					model.setFlag_north(false);
					model.setFlag_south(false);
					model.setFlag_west(false);
					model.setFlag_east(true);
				}
			}
			//	To test input value 
//			System.out.print(pressed);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			 pressed.remove(e.getKeyCode());

		}
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer timer = new Timer(drawDelay, drawAction);
				timer.start();
			}
		});
	}
}
