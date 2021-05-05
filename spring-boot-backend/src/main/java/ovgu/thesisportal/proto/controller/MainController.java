package ovgu.thesisportal.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ovgu.thesisportal.proto.model.ThesisTopic;
import ovgu.thesisportal.proto.repository.ThesisTopicRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public @ResponseBody String addThesisTopic(@RequestParam String title, @RequestParam(required = false) String desc,
											   @RequestParam String supervisor, @RequestParam String contact_email,
											   @RequestParam(required = false) String must_have, @RequestParam(required = false) String nice_to_have,
											   @RequestParam String start_date) {
		ThesisTopic thesisTopic = new ThesisTopic();

		thesisTopic.setTopic_title(title);
		thesisTopic.setTopic_desc(desc);
		try {
			thesisTopic.setStart_date(new SimpleDateFormat("dd/MM/yyyy").parse(start_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		thesisTopic.setSupervisor(supervisor);
		thesisTopic.setContact_email(contact_email);
		thesisTopic.setMust_have(must_have);
		thesisTopic.setNice_to_have(nice_to_have);
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
