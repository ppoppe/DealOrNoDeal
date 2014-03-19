package gameEngine;

import java.util.ArrayList;
import java.util.Random;

import interfaceImplementations.DefaultPlayer;
import interfaceImplementations.DefaultDealer;

public class Game {

	// Define the possible case values, in dollars
	public static int[] possibleCaseValues={1, 2, 5, 10, 25, 50, 75, 
		100, 200, 300, 400, 500, 750, 100, 5000, 10000, 20000,
		50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000};
	private static Random rand = new Random();

	public static void main(String[] args){
		// Go ahead and set up the stage
		Stage stage=new Stage(scrambleCases(possibleCaseValues));
		// Instantiate a player
		Player player = new DefaultPlayer();
		// Set up a dealer
		Dealer dealer = new DefaultDealer();
		// Get the player to pick an initial case
		int briefcaseNumber=player.pickCase(stage.getRemainingCaseNumbers());
		// Remove that briefcase from the stage
		stage.setPlayerCase(briefcaseNumber);
		System.out.printf("The player chose briefcase number %d\n",briefcaseNumber);

		// Set up an array to keep all the offers in
		int[] offers = new int[9];

		int numCases=6;
		int r;
		for (r=0; r<9; r++)
		{
			// Begin the round
			// Ask the player for six cases
			ArrayList<Integer> cases=new ArrayList<Integer>();
			ArrayList<Integer> values=new ArrayList<Integer>();
			for (int i=0; i<numCases; i++) {
				cases.add(player.pickCase(stage.getRemainingCaseNumbers()));
				values.add(stage.chooseBriefcase(cases.get(i)));
			}
			logRoundResult(r+1,cases,values);
			// Ask the Dealer to make an offer
			offers[r]=dealer.makeOffer(stage);
			// Ask the player what they want to do
			boolean acceptOffer=player.dealOrNoDeal(stage.scoreboard, offers[r]);
			if (acceptOffer) {
				System.out.printf("Player took the offer for $%,d, and the player's case was worth $%,d\n",offers[r],stage.getPlayerCase().openCase());
				break;
			}
			else
			{
				System.out.printf("Player refused the offer of $%,d\n",offers[r]);
			}
			// If we are in the last round, ask the player if they want to switch cases
			if (r==8)
			{
				boolean switchCases=player.switchCases(stage.scoreboard);
				if (switchCases) {
					// Open the final case
					ArrayList<Integer> lastNumber = stage.getRemainingCaseNumbers();
					int value = stage.chooseBriefcase(lastNumber.get(0));
					System.out.printf("Player decided to switch cases. The new briefcase was worth $%,d, while the player's original "
							+ "briefcase was worth $%,d\n",value,stage.getPlayerCase().openCase());
				}
				else
				{
					// Open the player's case, and reveal how much the last one would have been worth
					ArrayList<Integer> lastNumber = stage.getRemainingCaseNumbers();
					int value = stage.chooseBriefcase(lastNumber.get(0));
					System.out.printf("Player decided to keep the original case, which was worth $%,d, while the final case "
							+ "was worth $%,d\n",stage.getPlayerCase().openCase(),value);
				}
			}
			// Ask for one fewer case next round, for a minimum of one
			numCases--;
			if (numCases<1) {
				numCases=1;
			}
		}
		StringBuffer str= new StringBuffer();
		for (int i=0; i<r; i++) {
			if (offers[i]>0){
				str.append(String.format("%d ",offers[i]));
			}
			else break;
		}
		System.out.printf("Summary of offers: %s\n",str);
	}

	private static void logRoundResult(int roundNumber, ArrayList<Integer> roundXCases, ArrayList<Integer> values) {
		System.out.printf("In Round %d:\n",roundNumber);
		for (int i=0; i<roundXCases.size(); i++) {
			System.out.printf("  The player opened case %d, which was worth $%,d\n",roundXCases.get(i)+1,values.get(i));
		}
	}

	private static int[] scrambleCases(final int[] originalCaseNums) {
		@SuppressWarnings("serial")
		ArrayList<Integer> oCN = new ArrayList<Integer>() {{
			for (int i : originalCaseNums) { 
				add(i); 
			}
		}};
		int[] newCaseNums = new int[originalCaseNums.length];
		for (int i=0; i<originalCaseNums.length; i++) {
			newCaseNums[i]=oCN.remove(rand.nextInt(oCN.size()));
		}
		return newCaseNums;
	}
};

