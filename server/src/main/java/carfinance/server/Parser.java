package carfinance.server;

import java.util.Map;

public class Parser {
	
	private String[] nameOfRequiredStringInputs;
	private String[] nameOfRequiredIntegerInputs;
	
	public Parser() {
		this.nameOfRequiredStringInputs = new String[] {"channel", "car-type", "fuel-type"};	
		this.nameOfRequiredIntegerInputs = new String[] {"tot-cost", "down-payment", "term"};	
	}
	
	public Parser(String[] nameOfRequiredStrings, String[] nameOfRequiredIntegers) {
		this.nameOfRequiredStringInputs = nameOfRequiredStrings;
		this.nameOfRequiredIntegerInputs = nameOfRequiredIntegers;
	}
	
	public AnswerSheet parse(Map<String, String> params) {
		return new AnswerSheet(parseStringInputs(params), parseIntegerInputs(params));
	}
	
	private String[] parseStringInputs(Map<String, String> params) {
		String[] res = new String[nameOfRequiredStringInputs.length];
		for (int i = 0; i < nameOfRequiredStringInputs.length; i++) {
			try {
				res[i] = params.get(nameOfRequiredStringInputs[i]);
				System.out.println("got" + res[i]);
			} catch (Exception e){
				System.out.println("Invalid input at: " + nameOfRequiredIntegerInputs[i]);
				return null;
 			}
		}
		
		return res;
	}
	
	private int[] parseIntegerInputs(Map<String, String> params) {
		int[] res = new int[nameOfRequiredIntegerInputs.length];
		for (int i = 0; i < nameOfRequiredIntegerInputs.length; i++) {
			try {
				
				res[i] = Integer.parseInt(params.get(nameOfRequiredIntegerInputs[i]));
				System.out.println("got" + res[i]);
 				
			} catch (Exception e){
				System.out.println("Invalid input at: " + nameOfRequiredIntegerInputs[i]);
				return null;
 			}
		}
		return res;
	}
}
