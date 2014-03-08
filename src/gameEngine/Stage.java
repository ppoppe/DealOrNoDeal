package gameEngine;
import java.util.ArrayList;


public class Stage {

	public Stage(int[] possibleValues) {
		for (int i=0; i<possibleValues.length; i++){
			Briefcase temp=new Briefcase(i, possibleValues[i]);
			briefcases.add(temp);
			scoreboard.add(possibleValues[i]);
		}
	}

	private ArrayList<Briefcase> briefcases = new ArrayList<Briefcase>();
	private Briefcase playerCase;
	public ArrayList<Integer> scoreboard = new ArrayList <Integer>();

	public int chooseBriefcase(int caseNumber){
		// Find the case
		int caseLocation = findCase(caseNumber);
		// Make sure the briefcase we were asked for hasn't been opened yet
		if (!briefcases.get(caseLocation).getState())	{
			int value = briefcases.get(caseLocation).openCase();
			// Remove that case from the scoreboard
			removeValueFromScoreboard(value);
			return value;
		}
		else return 0;
	}
	
	public int[] getRemainingCaseNumbers() {
		ArrayList <Integer> temp = new ArrayList <Integer>();
		for (int i=0; i<briefcases.size(); i++) {
			if (!briefcases.get(i).getState()) {
				temp.add(briefcases.get(i).number);
			}
		}
		int[] caseNums = new int[temp.size()];
		for (int i=0; i<temp.size(); i++) {
			caseNums[i]=temp.get(i);
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
		for (int i=0; i<briefcases.size(); i++) {
			if (briefcases.get(i).number==caseNumber) return i;
		}
		return -1;
	}
	
	private void removeValueFromScoreboard(int value) {
		for (int i=0; i<scoreboard.size(); i++) {
			if (scoreboard.get(i)==value) {
				scoreboard.remove(i);
				return;
			}
		}
	}
	
	public Briefcase getPlayerCase(){
		return playerCase;
	}

	public int getTotalValueScoreboard(){
		int total = 0;
		for (int i=0; i<scoreboard.size(); i++)	{
			total = total + scoreboard.get(i);
		}
		return total;
	}

	public double getAverageValueCases(){
		int temp=getTotalValueScoreboard();
		return temp/scoreboard.size();
	}

}
