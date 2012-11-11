package ch.sauerrahm.hnefatafl;

import java.util.Set;

public class Result {
	private Board board;
	private Set<Field> deletedPieces;
	private Side winner;
	private String message;
	
	public Result(Board board, Set<Field> deletedPieces, Side winner){
		this.board = board;
		this.deletedPieces = deletedPieces;
		this.winner = winner;
	}
	
	public Set<Field> getDeletedPieces() {
		return deletedPieces;
	}
	
	public Board getBoard() {
		return board;
	}

	public Side getWinner() {
		return winner;
	}

	public void setWinner(Side winner) {
		this.winner = winner;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
