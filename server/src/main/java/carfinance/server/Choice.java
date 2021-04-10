package carfinance.server;

public class Choice {
	
	private String choice;
	private int choiceID;
	
	public Choice(String choice, int choiceID){
		this.choice = choice;
		this.choiceID = choiceID;
	}
	
	@Override
	public String toString(){
		return this.choice;
	}
	
	public int getIndex(){
		return this.choiceID;
	}
}
