package gameEngine;

import java.util.ArrayList;

public interface Player {
	int pickCase(ArrayList<Integer> caseNums);
	String playerName="";
	boolean dealOrNoDeal(ArrayList<Integer> scoreboard, int offer);
	boolean switchCases(ArrayList<Integer> scoreboard);
}
