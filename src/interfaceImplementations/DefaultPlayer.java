package interfaceImplementations;

import java.util.ArrayList;
import java.util.Random;

import gameEngine.Player;

public class DefaultPlayer implements Player {
	
	private Random rand;
	public String playerName="Default";
	
	
	public DefaultPlayer(){
		rand=new Random();
	}

	@Override
	public int pickCase(ArrayList<Integer> caseNums){
		// Just pick a case at random
		int ind = rand.nextInt(caseNums.size());
		return caseNums.get(ind);
	}

	@Override
	public boolean dealOrNoDeal(ArrayList<Integer> scoreboard, int offer) {
		// If there is still a large prize out there, keep going
		boolean acceptDeal = true;
		for (int i=0; i<scoreboard.size(); i++) {
			if (scoreboard.get(i)>200000) {
				acceptDeal=false;
			}
		}
		return acceptDeal;
	}

	@Override
	public boolean switchCases(ArrayList<Integer> scoreboard) {
		// Never switch cases
		return false;
	}
	
	

}
