package ovgu.thesisportal.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ovgu.thesisportal.proto.model.ThesisTopic;
import ovgu.thesisportal.proto.repository.ThesisTopicRepository;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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

	@PutMapping("/update/{id}")
	public ResponseEntity<ThesisTopic> updateThesisTopic(@RequestBody ThesisTopic newTopic, @PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		ThesisTopic thesisTopic = thesisTopicRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Thesis not found for this id :: " + id));
		thesisTopic.setTopic_title(newTopic.getTopic_title());
		thesisTopic.setTopic_desc(newTopic.getTopic_desc());
		thesisTopic.setStart_date(newTopic.getStart_date());
		thesisTopic.setSupervisor(newTopic.getSupervisor());
		thesisTopic.setContact_email(newTopic.getContact_email());
		thesisTopic.setMust_have(newTopic.getMust_have());
		thesisTopic.setNice_to_have(newTopic.getNice_to_have());
		final ThesisTopic updatedTopic = thesisTopicRepository.save(thesisTopic);
		return ResponseEntity.ok(updatedTopic);
	}



	@GetMapping("/readall")
	public @ResponseBody Iterable<ThesisTopic> getAllTopics() {
		return thesisTopicRepository.findAll();
	}
}
