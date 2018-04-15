import java.util.Random;

/**
 * Model: Contains all the state and logic Does not contain anything about
 * images or graphics, must ask view for that
 *
 * has methods to detect collision with boundaries decide next direction provide
 * direction provide location
 **/
public class Model {
	private int random_num;
	private int framewidth;
	private int frameheight;
	private int imgwidth;
	private int imgheight;

	private int Xloc = 0;
	private int Yloc = 0;
	
	//Temporary value.
	private int Xloc2 = 0;
	private int Yloc2 = 0;

	private int xIncr = 8;
	private int yIncr = 2;
	
	//Temporary value.
	private final int xIncr2 = 8;
	private final int yIncr2= 2;

	private Direction direction;
	private boolean flag_east = true;
	private boolean flag_south = true;
	private boolean flag_north = false;
	private boolean flag_west = false;

	int horizontal_change = -1;
	int verticle_change = -1;
	
	public void setFlag_east(boolean flag_east) {
		this.flag_east = flag_east;
	}

	public void setFlag_south(boolean flag_south) {
		this.flag_south = flag_south;
	}

	public void setFlag_north(boolean flag_north) {
		this.flag_north = flag_north;
	}

	public void setFlag_west(boolean flag_west) {
		this.flag_west = flag_west;
	}


	public void setDirect(Direction direction) {
		this.direction = direction;
	}

	public boolean isFlag_east() {
		return flag_east;
	}

	public boolean isFlag_south() {
		return flag_south;
	}

	public boolean isFlag_north() {
		return flag_north;
	}

	public boolean isFlag_west() {
		return flag_west;
	}
	public int getxIncr2() {
		return xIncr2;
	}

	public int getyIncr2() {
		return yIncr2;
	}


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

	public Direction getDirect() {
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
		this.framewidth = width;
		this.frameheight = height;
		this.imgheight = imageHeight;
		this.imgwidth = imageWidth;
	}

	public void updateLocationAndDirection() {

		if (getX() > getWidth() - getImgwidth()) {
			xIncr *= horizontal_change;
			flag_west = true;
			flag_east = false;

		} else if (getX() < 0) {
			xIncr *= horizontal_change;
			flag_east = true;
			flag_west = false;
		}

		if (getY() > getHeight() - getImgheight()) {
			yIncr *= verticle_change;
			flag_south = false;
			flag_north = true;

		} else if (getY() < 0) {
			yIncr *= verticle_change;
			flag_north = false;
			flag_south = true;
		}

		Xloc2 = Xloc;
		Yloc2 = Yloc;

		Xloc += xIncr;
		Yloc += yIncr;

		if (flag_east == true && flag_north == false && flag_south == false) {
			setDirect(Direction.EAST);
		}
		if (flag_west == true && flag_north == false && flag_south == false) {
			setDirect(Direction.WEST);
		}
		if (flag_north == true && flag_west == false && flag_east == false) {
			setDirect(Direction.NORTH);
		}
		if (flag_south == true && flag_west == false && flag_east == false) {
			setDirect(Direction.SOUTH);
		}
		if (flag_north && flag_west) {
			setDirect(Direction.NORTHWEST);
		}
		if (flag_north && flag_east) {
			setDirect(Direction.NORTHEAST);
		}
		if (flag_south && flag_west) {
			setDirect(Direction.SOUTHWEST);
		}
		if (flag_south && flag_east) {
			setDirect(Direction.SOUTHEAST);
		}
	}

}