package gameEngine;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class Scoreboard extends ArrayList<Integer>{
	
	
	public double averageValue() {
		return (double)(this.totalValue())/this.size();
	}
		
	public int totalValue() {
		int sum=0;
		for (int i : this) {
			sum+=i;
		}
		return sum;
	}
	
	public void removeValue(int value){
		Iterator<Integer> it = this.iterator();
		while (it.hasNext()) {
			if(it.next()==value){
				it.remove();
				return;
			}
		}
	}

	public void initialize(int[] values) {
		for (int i : values) {
			this.add(i);
		}
	}
}
