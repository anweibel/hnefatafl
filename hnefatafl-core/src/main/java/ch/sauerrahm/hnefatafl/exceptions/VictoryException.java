package ch.sauerrahm.hnefatafl.exceptions;

import ch.sauerrahm.hnefatafl.Side;

@SuppressWarnings("serial")
public class VictoryException extends Exception {
	
	private Side side;
	
	public VictoryException(Side side){
		this.side = side;
	}
	
	public Side getSide(){
		return side;
	}
}
