import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * View: Contains everything about graphics and images Know size of world, which
 * images to load etc
 *
 * has methods to provide boundaries use proper images for direction load images
 * for all direction (an image should only be loaded once!!! why?)
 **/
@SuppressWarnings("serial")
public class View extends JPanel {

	private JButton stop_button;
	private JButton direction_button;

	boolean stopFlag;
	boolean directFlag = false;

	boolean key_up;
	boolean key_down;
	boolean key_left;
	boolean key_right;

	private ActionListener actionListener;
	private ActionListener actionListener2;

	JFrame frame = new JFrame("MVC_Animation");
	private int picNum;
	BufferedImage[] current_pics;
	BufferedImage[][] curr_piclist;

	BufferedImage[][] picslist_jump = new BufferedImage[8][];
	BufferedImage[][] picslist_fire = new BufferedImage[8][];
	BufferedImage[][] picslist_walk = new BufferedImage[8][];

	final int frameCount_walk = 10;
	final int frameCount_fire = 4;
	final int frameCount_jump = 8;
	final private int Width = 500;
	final private int Height = 300;
	final private int ImageWidth = 165;
	final private int ImageHeight = 165;

	private int xloc;
	private int yloc;
	private int xloc2;
	private int yloc2;

	private Direction direct;
	private final int Button_x = 130; // location x
	private final int Button_y = 300; // location y
	private final int Button_sizex = 100; // size height
	private final int Button_sizey = 40; // size width

	Action_Enum act;
	

	public JButton getStop_button() {
		return stop_button;
	}

	public Action_Enum getAct() {
		return act;
	}

	public void setAct(Action_Enum act) {
		this.act = act;
	}

	public void setCurr_piclist(BufferedImage[][] curr_piclist) {
		this.curr_piclist = curr_piclist;
	}
	
	public Direction getDirect() {
		return direct;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}

	public int getImageWidth() {
		return ImageWidth;
	}

	public int getImageHeight() {
		return ImageHeight;
	}

	public boolean isFlag() {
		return stopFlag;
	}

	public int getXloc() {
		return xloc;
	}

	public void setXloc(int xloc) {
		this.xloc = xloc;
	}

	public int getYloc() {
		return yloc;
	}

	public void setYloc(int yloc) {
		this.yloc = yloc;
	}

	public void setDirect(Direction direct) {
		this.direct = direct;
	}

	public void setPicNum(int num) {
		this.picNum = num;
	}

	public boolean isKey_up() {
		return key_up;
	}

	public boolean isKey_down() {
		return key_down;
	}

	public boolean isKey_left() {
		return key_left;
	}

	public boolean isKey_right() {
		return key_right;
	}

	public void setKey_up(boolean key_up) {
		this.key_up = key_up;
	}

	public void setKey_down(boolean key_down) {
		this.key_down = key_down;
	}

	public void setKey_left(boolean key_left) {
		this.key_left = key_left;
	}

	public void setKey_right(boolean key_right) {
		this.key_right = key_right;
	}

	public View() {
		act = Action_Enum.FORWARD;
		curr_piclist=picslist_walk;
		frame.getContentPane().setBackground(Color.gray);
		frame.setLayout(null);
		frame.setBackground(Color.gray);
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setVisible(true);

		// Stop-Button
		stop_button = new JButton("Stop");
		stop_button.setBounds(Button_x, Button_y, Button_sizex, Button_sizey);
		stop_button.setLayout(null);
		stop_button.addActionListener(actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				change_button_text_stop();
				if (actionEvent.getSource() == stop_button) {
					if (stopFlag) {
						stopFlag = false;
					} else {
						stopFlag = true;
					}
				}
			}
		});
		frame.getContentPane().add(stop_button);

		for (int i = 0; i < picslist_walk.length; i++) {
			BufferedImage img = createImage(new File("Images/orc/orc_forward_" + Direction.values()[i] + ".png"));
			BufferedImage[] pics = new BufferedImage[frameCount_walk];
			for (int j = 0; j < frameCount_walk; j++) {
				pics[j] = img.getSubimage(ImageWidth * j, 0, ImageWidth, ImageHeight);
			}
			picslist_walk[i] = pics;
		}

		for (int i = 0; i < picslist_fire.length; i++) {
			BufferedImage img = createImage(new File("Images/orc/orc_fire_" + Direction.values()[i] + ".png"));
			BufferedImage[] pics = new BufferedImage[frameCount_fire];
			for (int j = 0; j < frameCount_fire; j++) {
				pics[j] = img.getSubimage(ImageWidth * j, 0, ImageWidth, ImageHeight);
			}
			picslist_fire[i] = pics;
		}

		for (int i = 0; i < picslist_jump.length; i++) {
			BufferedImage img = createImage(new File("Images/orc/orc_jump_" + Direction.values()[i] + ".png"));
			BufferedImage[] pics = new BufferedImage[frameCount_jump];
			for (int j = 0; j < frameCount_jump; j++) {
				pics[j] = img.getSubimage(ImageWidth * j, 0, ImageWidth, ImageHeight);
			}
			picslist_jump[i] = pics;
		}
	}

	public void update(int x, int y, Direction direction) {
		if (stopFlag == false) {
			setXloc(x);
			setYloc(y);
			setDirect(direction);
			if (act == (Action_Enum.FORWARD)) {
				curr_piclist=picslist_walk;
			}else if(act == (Action_Enum.FIRE)) {
				curr_piclist=picslist_fire;
			}else if (act == (Action_Enum.JUMP)) {
				curr_piclist=picslist_jump;
			}

			if (direct.equals(Direction.EAST)) {
				current_pics = curr_piclist[1];
			} else if (direct.equals(Direction.WEST)) {
				current_pics = curr_piclist[2];
			} else if (direct.equals(Direction.NORTH)) {
				current_pics = curr_piclist[3];
			} else if (direct.equals(Direction.SOUTH)) {
				current_pics = curr_piclist[4];
			} else if (direct.equals(Direction.SOUTHEAST)) {
				current_pics = curr_piclist[0];
			} else if (direct.equals(Direction.SOUTHWEST)) {
				current_pics = curr_piclist[5];
			} else if (direct.equals(Direction.NORTHEAST)) {
				current_pics = curr_piclist[7];
			} else if (direct.equals(Direction.NORTHWEST)) {
				current_pics = curr_piclist[6];
			}
	
			frame.repaint();
		}
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	public void paint(Graphics g) {
		try {
			setPicNum((picNum + 1) % current_pics.length);

			g.drawImage(current_pics[picNum], getXloc(), getYloc(), Color.gray, this);
		} catch (RuntimeException e) {
		}

	}

	private BufferedImage createImage(File filename) {

		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(filename);
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void change_key_button() {

	}

	public void change_button_text_stop() {
		if (stopFlag) {
			stop_button.setText("Stop");
		} else {
			stop_button.setText("Start");
		}

	}

	public void chang_direct_button() {
		if (directFlag) {
			directFlag = false;

		} else {
			directFlag = true;
		}
	}

	public void rollback(int x, int y) {
		this.xloc2 = x;
		this.yloc2 = y;
	}

}