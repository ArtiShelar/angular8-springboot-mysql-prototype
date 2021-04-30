package ovgu.thesisportal.proto.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AppRestController {

	@RequestMapping("/hello")
	public String hello() {
		return "Hello, spring boot is working!";
	}

}
