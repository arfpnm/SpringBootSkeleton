package com.arif.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class Controller {

	static Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
		    @RequestMapping("/")
		    public String greetings() {
		        return "Greetings from Arif M!";
		    }
		    
		    @RequestMapping("/test")
		    public String index() {
		        return "Response with the test data";
		    }


	
	

	//@Autowired
	//SomeService someservice;

	/**
	@RequestMapping(value= "/some/api/{param1}/{param2}/{param3}")
	@Cacheable("documentFolder")
	public ReturnObject findDocuments(@PathVariable("param1") String param1, 
			@PathVariable("param2") String param2, 
			@PathVariable("param3") String param3, 
			ModelMap model) 
					throws  IOException, Exception{
		ReturnObject returnObject = param1 != null ?  Service.findXyz(param1) :  null;
		return returnObject;
	}
	**/

}