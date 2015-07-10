package com.adaptionsoft.games.uglytrivia;

public class Player {
	private final String name;
	private int place = 0;
	private int purses = 0;
	private boolean inPenaltyBox = false;
	private boolean isGettingOutOfPenaltyBox = false;

	public Player(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public boolean isInPenaltyBox() {
		return inPenaltyBox;
	}

	public void move(int roll) {
		place = (place + roll) % 12;
		print(this + "'s new location is " + getPlace());
	}

	public int getPlace() {
		return place;
	}

	public void addPurse() {
		print("Answer was correct!!!!");
		purses++;
		print(this + " now has " + getPurses() + " Gold Coins.");
	}

	public int getPurses() {
		return purses;
	}

	public void toPenaltyBox() {
		print(this + " was sent to the penalty box");
		inPenaltyBox = true;
		isGettingOutOfPenaltyBox = false;
	}

	public boolean canAnswer() {
		return checkConditions();
	}

	public boolean hasWin() {
		return getPurses() == 6;
	}

	private void resolvePenaltyBoxStatus(int roll) {
		if (isInPenaltyBox()) {
			isGettingOutOfPenaltyBox = roll % 2 != 0;

			if (isGettingOutOfPenaltyBox) {
				print(this + " is getting out of the penalty box");
			} else {
				print(this + " is not getting out of the penalty box");
			}
		}
	}

	public boolean canMove(int roll) {
		resolvePenaltyBoxStatus(roll);
		return checkConditions();
	}

	private boolean checkConditions() {
		return !isInPenaltyBox()
				|| (isInPenaltyBox() && isGettingOutOfPenaltyBox);
	}

	private void print(Object string) {
		System.out.println(string);

	}
}
