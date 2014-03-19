package gameEngine;
import java.util.ArrayList;
import java.util.Iterator;


public class Stage {

	public Stage(int[] possibleValues) {
		for (int i=0; i<possibleValues.length; i++){
			Briefcase temp=new Briefcase(i, possibleValues[i]);
			briefcases.add(temp);
		}
		scoreboard.initialize(possibleValues);
	}

	private ArrayList<Briefcase> briefcases = new ArrayList<Briefcase>();
	private Briefcase playerCase;
	public Scoreboard scoreboard = new Scoreboard();

	public int chooseBriefcase(int caseNumber){
		// Find the case
		int caseLocation = findCase(caseNumber);
		// Make sure the briefcase we were asked for hasn't been opened yet
		if (!briefcases.get(caseLocation).getState())	{
			int value = briefcases.get(caseLocation).openCase();
			// Remove that case from the scoreboard
			scoreboard.removeValue(value);
			return value;
		}
		else return 0;
	}
	
	public ArrayList<Integer> getRemainingCaseNumbers() {
		ArrayList <Integer> caseNums = new ArrayList <Integer>();
		
		Iterator<Briefcase> itBC = briefcases.iterator();
		while (itBC.hasNext()) {
			Briefcase bc=itBC.next();
			if (!bc.getState()) {
				caseNums.add(bc.number);
			}
		}
		return caseNums;
	}

	public void setPlayerCase(int caseNumber) {
		// Find the case
		int caseLocation = findCase(caseNumber);
		// Make sure the briefcase we were asked for hasn't been opened yet
		if (!briefcases.get(caseLocation).getState())	{
			// Pull the case from the stage and give it to the player to hold
			playerCase=briefcases.remove(caseLocation);
		}
	}
	
	private int findCase(int caseNumber) {
		Iterator<Briefcase> it = briefcases.iterator();
		int i=0;
		while (it.hasNext()) {
			if (it.next().number==caseNumber) return i;
			else i++;
		}
		return -1;
	}
	
	public Briefcase getPlayerCase(){
		return playerCase;
	}

}
