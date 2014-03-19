package interfaceImplementations;

import gameEngine.Dealer;
import gameEngine.Stage;

public class DefaultDealer implements Dealer {
	public int makeOffer(Stage stage){
		// for now, just offer 5% less than the average value
		double offer = stage.scoreboard.averageValue();
		offer = offer*.95;
		// Do some quantizing so the numbers look nice
		double rounder;
		if (offer<1000)				rounder=100;
		else if (offer<10000)	    rounder=1000;
		else						rounder=10000;
		return (int) (Math.round(offer/rounder)*rounder);
	}
}
