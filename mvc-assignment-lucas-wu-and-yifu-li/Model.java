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

class Model{
	int x = 0;
	int y = 0;
	Direction direct = Direction.SOUTHEAST;

	int width;
	int height;
	int imgWidth;
	int imgHeight;
	
    final int xIncr = 8;
    final int yIncr = 2;
    
    
	
	public Model(int width, int height, int imgWidth, int imgHeight){
		this.width = width;
		this.height = height;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}
	
	public void updateLocationAndDirection(){
		
		if((x + imgWidth) >= width || (y + imgHeight) >= height || x <= 0 || y <= 0 ){
			int random = (int )(Math.random() * 5 + 2);
			int currentOrder = direct.ordinal();
			direct = Direction.values()[random + currentOrder];
		}
		
		
		if(direct.getName() == "north"){
			 y -=yIncr;
		}
		else if(direct.getName() == "northeast"){
			x +=xIncr;
			y -=yIncr;
		}
		else if(direct.getName() == "east"){
			x +=xIncr;
		}
		else if(direct.getName() == "southeast"){
			x +=xIncr;
			y +=yIncr;
		}
		else if(direct.getName() == "south"){
			y +=yIncr;
		}
		else if(direct.getName() == "southwest"){
			x -=xIncr;
			y +=yIncr;
		}
		else if(direct.getName() == "west"){
			x -=xIncr;
		}
		else if(direct.getName() == "northwest"){
			x -=xIncr;
			y -=yIncr;
		}
	}
	
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirect() {
		return direct;
	}

}