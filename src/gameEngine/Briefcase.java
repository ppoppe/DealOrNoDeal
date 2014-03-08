package gameEngine;


public class Briefcase {
	public Briefcase(int number, int value){
		this.number=number;
		this.value=value;
		this.state=false;
	}
	
	private int value; // value in cents
	private boolean state; // false = closed, true = open
	public int number;

	public int openCase() {
		state=true;
		return value;
	}
	
	public boolean getState() {
		return state;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
