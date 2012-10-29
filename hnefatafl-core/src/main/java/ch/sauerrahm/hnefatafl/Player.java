package ch.sauerrahm.hnefatafl;

public interface Player {
	public void handOver(Game game);
	public void signalIllegalMove();
	public boolean isSynchronous(); 
}
