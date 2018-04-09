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
	
	Action drawAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	};;
	final int drawDelay = 30; // msec
	
	boolean stopFlag;
	boolean directFlag = false;
	private ActionListener actionListener;
	private ActionListener actionListener2;

	JFrame frame = new JFrame("MVC_Animation");
	int picNum;
	BufferedImage[] pics;
	BufferedImage[] pics1;
	BufferedImage[] pics2;
	BufferedImage[] pics3;
	BufferedImage[] pics4;
	BufferedImage[] pics5;
	BufferedImage[] pics6;
	BufferedImage[] pics7;
	BufferedImage[] pics8;
	BufferedImage[][] picslist = new BufferedImage[8][];

	final int frameCount = 10;
	final private int Width = 500;
	final private int Height = 300;
	final private int ImageWidth = 165;
	final private int ImageHeight = 165;
	
	private int xloc;
	private int yloc;
	private int xloc2;
	private int yloc2;

	private String direct;
	private final int Button_x = 130; // location x
	private final int Button_y = 300; // location y
	private final int Button_sizex = 100; // size height
	private final int Button_sizey = 40; // size width

	public JButton getButton_stop() {
		return stop_button;
	}

	public JButton getButton_direct() {
		return direction_button;
	}

	public int getButton_x() {
		return Button_x;
	}

	public int getButton_y() {
		return Button_y;
	}

	public int getButton_sizex() {
		return Button_sizex;
	}

	public int getButton_sizey() {
		return Button_sizey;
	}

	public boolean isFlag() {
		return stopFlag;
	}

	public boolean isdirectFlag() {
		return directFlag;
	}

	public String getDirect() {
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

	public void setDirect(String direct) {
		this.direct = direct;
	}

	public View() {

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

		// Direction-Button
		direction_button = new JButton("Go Back");
		direction_button.setBounds(2 * Button_x + 30, Button_y, Button_sizex + 30, Button_sizey);
		direction_button.setLayout(null);
		direction_button.addActionListener(actionListener2 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (actionEvent.getSource() == direction_button) {
						Direct_ButtonListener();
				}
			}
		});
		frame.getContentPane().add(direction_button);

		BufferedImage img1 = createImage(new File("Images/orc/orc_forward_" + Direction.SOUTHEAST + ".png"));
		pics1 = new BufferedImage[10];
		for (int i = 0; i < frameCount; i++)
			pics1[i] = img1.getSubimage(ImageWidth * i, 0, ImageWidth, ImageHeight);

		BufferedImage img2 = createImage(new File("Images/orc/orc_forward_" + Direction.EAST + ".png"));
		pics2 = new BufferedImage[10];
		for (int i = 0; i < frameCount; i++)
			pics2[i] = img2.getSubimage(ImageWidth * i, 0, ImageWidth, ImageHeight);

		BufferedImage img3 = createImage(new File("Images/orc/orc_forward_" + Direction.WEST + ".png"));
		pics3 = new BufferedImage[10];
		for (int i = 0; i < frameCount; i++)
			pics3[i] = img3.getSubimage(ImageWidth * i, 0, ImageWidth, ImageHeight);

		BufferedImage img4 = createImage(new File("Images/orc/orc_forward_" + Direction.NORTH + ".png"));
		pics4 = new BufferedImage[10];
		for (int i = 0; i < frameCount; i++)
			pics4[i] = img4.getSubimage(ImageWidth * i, 0, ImageWidth, ImageHeight);

		BufferedImage img5 = createImage(new File("Images/orc/orc_forward_" + Direction.SOUTH + ".png"));
		pics5 = new BufferedImage[10];
		for (int i = 0; i < frameCount; i++)
			pics5[i] = img5.getSubimage(ImageWidth * i, 0, ImageWidth, ImageHeight);

		BufferedImage img6 = createImage(new File("Images/orc/orc_forward_" + Direction.SOUTHWEST + ".png"));
		pics6 = new BufferedImage[10];
		for (int i = 0; i < frameCount; i++)
			pics6[i] = img6.getSubimage(ImageWidth * i, 0, ImageWidth, ImageHeight);

		BufferedImage img7 = createImage(new File("Images/orc/orc_forward_" + Direction.NORTHWEST + ".png"));
		pics7 = new BufferedImage[10];
		for (int i = 0; i < frameCount; i++)
			pics7[i] = img7.getSubimage(ImageWidth * i, 0, ImageWidth, ImageHeight);

		BufferedImage img8 = createImage(new File("Images/orc/orc_forward_" + Direction.NORTHEAST + ".png"));
		pics8 = new BufferedImage[10];
		for (int i = 0; i < frameCount; i++)
			pics8[i] = img8.getSubimage(ImageWidth * i, 0, ImageWidth, ImageHeight);

		picslist[0] = pics1;
		picslist[1] = pics2;
		picslist[2] = pics3;
		picslist[3] = pics4;
		picslist[4] = pics5;
		picslist[5] = pics6;
		picslist[6] = pics7;
		picslist[7] = pics8;
	}

	public void update(int x, int y, String direct) {
		if (stopFlag == false) {
			setXloc(x);
			setYloc(y);
			setDirect(direct);
			if (direct.equals(Direction.EAST.getName())) {
				pics = picslist[1];
			} else if (direct.equals(Direction.WEST.getName())) {
				pics = picslist[2];
			} else if (direct.equals(Direction.NORTH.getName())) {
				pics = picslist[3];
			} else if (direct.equals(Direction.SOUTH.getName())) {
				pics = picslist[4];
			} else if (direct.equals(Direction.SOUTHEAST.getName())) {
				pics = picslist[0];
			} else if (direct.equals(Direction.SOUTHWEST.getName())) {
				pics = picslist[5];
			} else if (direct.equals(Direction.NORTHEAST.getName())) {
				pics = picslist[7];
			} else if (direct.equals(Direction.NORTHWEST.getName())) {
				pics = picslist[6];
			}
			frame.repaint();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {

		picNum = (picNum + 1) % frameCount;
		g.drawImage(pics[picNum], getXloc(), getYloc(), Color.gray, this);
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

	public void change_button_text_stop() {
		if (stopFlag) {
			getButton_stop().setText("Stop");
		} else {
			getButton_stop().setText("Start");
		}

	}

	public void Direct_ButtonListener() {
		if (directFlag) {
			directFlag = false;
			if (getDirect() == Direction.NORTH.getName()) {
				setDirect(Direction.SOUTH.getName());
			}
			if (getDirect() == Direction.SOUTH.getName()) {
				setDirect(Direction.NORTH.getName());
			}
			if (getDirect() == Direction.NORTHEAST.getName()) {
				setDirect(Direction.NORTHWEST.getName());
			}
			if (getDirect() == Direction.NORTHWEST.getName()) {
				setDirect(Direction.NORTHEAST.getName());
			}
			if (getDirect() == Direction.SOUTHWEST.getName()) {
				setDirect(Direction.SOUTHEAST.getName());
			}
			if (getDirect() == Direction.SOUTHEAST.getName()) {
				setDirect(Direction.SOUTHWEST.getName());
			}
		} else {
			directFlag = true;
		}
	}

	public void rollback(int x, int y) {
		this.xloc2 = x;
		this.yloc2 = y;
	}

}