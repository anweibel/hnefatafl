package ch.sauerrahm.hnefatafl.online;

import ch.sauerrahm.hnefatafl.Game;
import ch.sauerrahm.hnefatafl.Player;
import ch.sauerrahm.hnefatafl.Side;

public class OnlinePlayer implements Player {

	private Side side;
	private String username;
	
	public OnlinePlayer(Side side, String username){
		this.side = side;
		this.username = username;
	}
	
	@Override
	public void handOver(Game game) {
		// Don't do anything, just wait until the human play does a draw
	}

	@Override
	public void signalIllegalMove() {
		// TODO Auto-generated method stub
	}

	@Override
	public Side getSide() {
		return side;
	}

	public String getUsername() {
		return username;
	}

}
