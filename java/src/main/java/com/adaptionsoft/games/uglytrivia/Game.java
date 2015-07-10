package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private enum Category {
		Pop, Science, Sports, Rock;

		private int cnt = 0;

		private String getQuestion() {
			return name() + " Question " + cnt++;
		}
	}

	List<Player> players = new ArrayList<Player>();

	int currentPlayer = 0;

	public Game() {
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public void add(String playerName) {
		players.add(new Player(playerName));
		print(playerName + " was added");
		print("They are player number " + howManyPlayers());
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		Player pl = getCurrentPlayer();
		print(pl + " is the current player");
		print("They have rolled a " + roll);

		if (pl.canMove(roll)) {
			normalMovement(roll);
		}
	}

	private Player getCurrentPlayer() {
		Player pl = (Player) players.get(currentPlayer);
		return pl;
	}

	private void normalMovement(int roll) {
		Player pl = getCurrentPlayer();
		pl.move(roll);
		askQuestion();
	}

	private void askQuestion() {
		Category cat = currentCategory();
		print("The category is " + currentCategory());
		print(cat.getQuestion());
	}

	private Category currentCategory() {
		Player pl = getCurrentPlayer();
		int index = pl.getPlace() % 4;

		switch (index) {
		case 0:
			return Category.Pop;
		case 1:
			return Category.Science;
		case 2:
			return Category.Sports;
		default:
			break;
		}
		return Category.Rock;
	}

	public void wasCorrectlyAnswered() {
		Player pl = getCurrentPlayer();

		if (pl.canAnswer()) {
			questionCorrectAnswered();
		}
	}

	private void questionCorrectAnswered() {
		Player pl = getCurrentPlayer();
		pl.addPurse();
	}

	public void wrongAnswer() {
		print("Question was incorrectly answered");
		Player pl = getCurrentPlayer();
		pl.toPenaltyBox();
	}

	private void print(Object s) {
		System.out.println(s);
	}

	public boolean notAWinner() {
		if (getCurrentPlayer().hasWin()) {
			return false;
		}
		nextPlayer();
		return true;
	}

	private void nextPlayer() {
		currentPlayer++;
		currentPlayer = currentPlayer % players.size();
	}
}
