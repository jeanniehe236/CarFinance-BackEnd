package carfinance.api;

import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import carfinance.server.ChoiceCollector;
import carfinance.server.ResponseGenerator;
import carfinance.server.Response;

@RestController
@CrossOrigin
public class CalculatorRestController {
	@GetMapping("/car-finance")
	public Map<String, Set<String>> getChoices() {
		return ChoiceCollector.getChoices();
	}
	
	
	@GetMapping("/car-finance/get-offers")
	public Response getResponse(@RequestParam Map<String,String> params){
		return ResponseGenerator.generateResponse(params);
	}
}
