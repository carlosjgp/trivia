package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameRunner {

	public static void main(String[] args) {
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = new Random(1);

		if (aGame.isPlayable()) {
			do {
				aGame.roll(rand.nextInt(5) + 1);

				if (rand.nextInt(9) == 7) {
					aGame.wrongAnswer();
				} else {
					aGame.wasCorrectlyAnswered();
				}

			} while (aGame.notAWinner());
		}
	}
}
