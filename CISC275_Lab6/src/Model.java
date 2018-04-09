import java.util.Random;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
	private int random_num;
	private int framewidth;
	private int frameheight;
	private int imgwidth;
	private int imgheight;
	
	private int Xloc=0;
	private int Yloc=0;

	private int Xloc2=0;
	private int Yloc2=0;
	
	private int xIncr = 8;
	private int yIncr = 2;
	
	private String direction;

	public void setDirect(String direction) {
		this.direction = direction;
	}

	static boolean flag_east = true;
	static boolean flag_south = true;
	static boolean flag_north =false;
	static boolean flag_west = false;

	int horizontal_change = -1;
	int verticle_change = -1;

	public int getxIncr() {
		return xIncr;
	}

	public int getyIncr() {
		return yIncr;
	}

	public void setxIncr(int xIncr) {
		this.xIncr = xIncr;
	}

	public void setyIncr(int yIncr) {
		this.yIncr = yIncr;
	}

	
	public String getDirect() {
		return direction;
	}
	
	public int getWidth() {
		return framewidth;
	}

	public int getHeight() {
		return frameheight;
	}

	public int getImgwidth() {
		return imgwidth;
	}

	public int getImgheight() {
		return imgheight;
	}

	public int getX() {
		return Xloc;
	}

	public int getY() {
		return Yloc;
	}
	
	public int getXloc2() {
		return Xloc2;
	}


	public int getYloc2() {
		return Yloc2;
	}
	
	
	public Model(int width, int height, int imageWidth, int imageHeight) {
		this.framewidth=width;
		this.frameheight=height;
		this.imgheight=imageHeight;
		this.imgwidth=imageWidth;
	}

	public void direct_button() {
		this.xIncr=-xIncr;
		this.yIncr=-yIncr;
		if(xIncr>0) {
			flag_east = true;
			flag_west = false;
		}else {
			flag_west = true;
			flag_east = false;
		}
		if(yIncr>0) {
			flag_north = false;
			flag_south = true;
		}else {
			flag_north = true;
			flag_south = false;
		}
	}
	public void updateLocationAndDirection() {

		if (getX() > getWidth()-getImgwidth()) {
			xIncr *= horizontal_change;
			flag_west = true;
			flag_east = false;
			
		} else if (getX() < 0) {
			xIncr *= horizontal_change;
			flag_east = true;
			flag_west = false;
		}

		if (getY() > getHeight() -getImgheight() ) {
			yIncr *= verticle_change;
			flag_south = false;
			flag_north = true;

		} else if (getY() < 0) {
			yIncr *= verticle_change;
			flag_north = false;
			flag_south = true;
		}
		
		Xloc2=Xloc;
		Yloc2=Yloc;
		
		Xloc+=xIncr;
		Yloc+=yIncr;
		
		
		if(flag_east==true &&flag_north==false &&flag_south==false) {
			setDirect( Direction.EAST.getName());
		}
		if(flag_west==true &&flag_north==false &&flag_south==false) {
			setDirect( Direction.WEST.getName());
		}
		if(flag_north==true &&flag_west==false &&flag_east==false) {
			setDirect( Direction.NORTH.getName());
		}
		if(flag_south==true &&flag_west==false &&flag_east==false) {
			setDirect( Direction.SOUTH.getName());
		}
		if(flag_north &&flag_west) {
			setDirect( Direction.NORTHWEST.getName());
		}
		if(flag_north &&flag_east) {
			setDirect(  Direction.NORTHEAST.getName());
		}
		if(flag_south &&flag_west) {
			setDirect( Direction.SOUTHWEST.getName());
		}
		if(flag_south &&flag_east) {
			setDirect( Direction.SOUTHEAST.getName());
		}
	}
	

	
}