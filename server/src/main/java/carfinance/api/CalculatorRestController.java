package carfinance.api;

import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import carfinance.server.responseGenerator.OptionsCollector;
import carfinance.server.responseGenerator.Response;
import carfinance.server.responseGenerator.ResponseGenerator;
import carfinance.server.database.Catalogue;
import carfinance.server.preprocessor.Validator;
import carfinance.server.calculator.MasterInterestRateRegulator;
@RestController
@CrossOrigin
public class CalculatorRestController {
	private OptionsCollector optionsCollector;
	private Validator validator;
	
	public CalculatorRestController() {
		Catalogue catalogue = new Catalogue();
		MasterInterestRateRegulator interestRateRegulator = new MasterInterestRateRegulator();
		
		optionsCollector = new OptionsCollector(catalogue, interestRateRegulator);
		validator = new Validator(catalogue, interestRateRegulator);
	}
	
	@GetMapping("/car-finance")
	public Map<String, Set<String>> getOptions() {
		return optionsCollector.getOptions();
	}
	
	
	@GetMapping("/car-finance/get-offers")
	public Response getResponse(@RequestParam Map<String,String> params){
		return ResponseGenerator.generateResponse(params, validator);
	}
}
