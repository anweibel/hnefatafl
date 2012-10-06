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
}
