package ch.sauerrahm.hnefatafl;

public interface Player {
	public Move getNextMove(Board board);
	public void signalIllegalMove(); 
}
