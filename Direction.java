public enum Direction {

	SOUTHEAST("southeast"),
	EAST("east"),
	WEST("west"),
	NORTH("north"),
	SOUTH("south"),
	SOUTHWEST("southwest"),
	NORTHWEST("northwest"),
	NORTHEAST("northeast");
	
	private String name = null;
	
	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
	


}
