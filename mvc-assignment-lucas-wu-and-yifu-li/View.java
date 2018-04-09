/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
import java.awt.Color;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel{

	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[] pics;
    int xloc = 0;
    int yloc = 0;
	JFrame frame;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    Direction direction;
	
    public int getHeight(){return frameHeight;}
	public int getWidth(){return frameWidth;}
	public int getImageHeight(){return imgHeight;}
	public int getImageWidth(){return imgWidth;}
    public View(){
		frame = new JFrame();
		frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	ArrayList<BufferedImage> imgList = new ArrayList<BufferedImage>();
    	imgList.add(createImage("orc_animation/orc_forward_north.png")); //0
    	imgList.add(createImage("orc_animation/orc_forward_northeast.png")); //1
    	imgList.add(createImage("orc_animation/orc_forward_east.png")); //2
    	imgList.add(createImage("orc_animation/orc_forward_southeast.png")); //3
    	imgList.add(createImage("orc_animation/orc_forward_south.png")); //4
    	imgList.add(createImage("orc_animation/orc_forward_southwest.png")); //5
    	imgList.add(createImage("orc_animation/orc_forward_west.png")); //6
    	imgList.add(createImage("orc_animation/orc_forward_northwest.png")); //7
		pics = new BufferedImage[80];
    	for(int j = 0; j < 8; j++){
        	for(int i = 0; i < frameCount; i++)
        		pics[j*10 + i] = imgList.get(j).getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
        	
        	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    	}
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  
    
    //Read image from file and return
    private BufferedImage createImage(String dir){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(dir));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	
    	//"orc_animation/orc_forward_southeast.png"
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
  
	public void update(int x,int y, Direction dir){
		xloc=x;
		yloc=y;
		direction=dir;
		picNum = (picNum + 1) % frameCount;
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	public void paint(Graphics g) {
	    g.drawImage(pics[10*direction.ordinal()+picNum], xloc, yloc, Color.gray, this);
		
	}
}