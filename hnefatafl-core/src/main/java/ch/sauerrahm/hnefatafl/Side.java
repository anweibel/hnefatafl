package ch.sauerrahm.hnefatafl;

public enum Side {
	BLACK,
	WHITE;
	
	public Side otherSide(){
		if(this.equals(BLACK))
			return WHITE;
		else
			return BLACK;
	} 
	
	public static Side fromString(String string){
		if(string.equalsIgnoreCase("white"))
			return WHITE;
		else
			return BLACK;
	}
}
