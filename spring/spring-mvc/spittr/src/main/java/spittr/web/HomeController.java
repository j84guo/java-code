package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// marks class as a component to be created as a bean
@Controller 
@RequestMapping(value={"/", "/homepage"})
public class HomeController {

	@RequestMapping(method=GET)
	public String home() {
		return "home";
	}


}
