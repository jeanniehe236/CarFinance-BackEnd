package carfinance.server;

public class Response {
	private Offer[] offers;
	private String message;
	private int totCost;
	private int downPayment;
	private int term;
	private AnswerSheet answersheet;
	
	public Response(AnswerSheet answersheet) {
		this.answersheet = answersheet;
		this.message = "Sorry, no offers are produced. Please check your inputs or contact the bank.";
	}
	
	public Response(Offer[] offers, AnswerSheet answersheet) {	
		this.offers = offers;
		this.message = generateCongratMessage(answersheet);
		this.totCost =answersheet.getTotCost();
		this.downPayment = answersheet.getDownPayment();
		this.term = answersheet.getTerm();
	}
	
	private String generateCongratMessage(AnswerSheet answersheet) {
		int numOfOffers = offers.length;
		String carType = answersheet.getCarType();
		String channel = answersheet.getChannel();
		
		return String.format("Congratulations! You've got %d offer%s "
				+ " The interest rate is calculated for your purchase of %s %s %s-based car as %s %s. The annual payment%s generated "
				+ "based on that interest rate along with the price of your car, your down payment and loan term.",
				numOfOffers,
				numOfOffers == 1 ? "!" : "s! The offers are sorted in increasing order by our recommendation.",
				GrammarHelper.getArticle(carType), carType.toLowerCase().replace("_", " "), answersheet.getFuelType().toLowerCase(), 
				GrammarHelper.getArticle(channel), channel.toLowerCase().replace("_", " "), numOfOffers == 1 ? " is":"s are");
	
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public Offer[] getOffers(){
		return this.offers;
	}
	
	public int getTotCost(){
		return this.totCost;
	}
	
	public int getDownPayment() {
		return this.downPayment;
	}
	
	public int getTerm() {
		return this.term;
	}
	
	public AnswerSheet getAnswersheet() {
		return this.answersheet;
	}
	
	
}
