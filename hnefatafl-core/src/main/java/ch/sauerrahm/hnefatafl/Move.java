package ch.sauerrahm.hnefatafl;

public class Move {
	private Field from;
	private Field to;
	
	public Move(Field from, Field to){
		this.from = from;
		this.to = to;
	}

	public Field getFrom() {
		return from;
	}

	public Field getTo() {
		return to;
	}
}
