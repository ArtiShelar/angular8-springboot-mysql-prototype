package ovgu.thesisportal.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ovgu.thesisportal.proto.model.ThesisTopic;
import ovgu.thesisportal.proto.repository.ThesisTopicRepository;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping(path="/prototype")
public class MainController {

	@Autowired
	private ThesisTopicRepository thesisTopicRepository;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello, spring boot is working!";
	}

	@PostMapping("/add")
	public @ResponseBody String addThesisTopic(@RequestParam String title, @RequestParam String user_id) {
		ThesisTopic thesisTopic = new ThesisTopic();

		thesisTopic.setTitle(title);
		thesisTopic.setUser_id(Integer.parseInt(user_id));
		thesisTopic.setDate(new Date());

		thesisTopicRepository.save(thesisTopic);
		return "Thesis Topic Saved";
	}

	@RequestMapping("/update")
	public void update() {
	}

	@GetMapping("/readall")
	public @ResponseBody Iterable<ThesisTopic> getAllTopics() {
		return thesisTopicRepository.findAll();
	}
}
