package ovgu.thesisportal.proto.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AppRestController {

	@RequestMapping("/hello")
	public String hello() {
		return "Hello, spring boot is working!";
	}

	@RequestMapping("/add")
	public void add() throws IOException {
	}

	@RequestMapping("/update")
	public void update() throws IOException {
	}

	@RequestMapping("/read")
	public void read() throws IOException {
	}
}
