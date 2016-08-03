package rome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Main controller.
 * 
 * @author Rome
 * @version 1.0
 * @since 7/26/2016
 *
 */
@Controller
public class MainController {
	
	/**
	 * Displays the home page.
	 *
	 * @return the UI home page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
