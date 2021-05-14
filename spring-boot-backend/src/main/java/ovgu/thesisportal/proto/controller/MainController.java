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

	@PostMapping("/update/{id}")
	public ResponseEntity<ThesisTopic> updateThesisTopic(@PathVariable(value = "id") Long id,
														 @RequestParam(required = false) String title,
														 @RequestParam(required = false) String desc,
														 @RequestParam(required = false) String supervisor,
														 @RequestParam(required = false) String contact_email,
														 @RequestParam(required = false) String must_have,
														 @RequestParam(required = false) String nice_to_have,
														 @RequestParam(required = false) String start_date)
			throws ResourceNotFoundException {
		ThesisTopic thesisTopic = thesisTopicRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Thesis not found for this id :: " + id));
		System.out.println(thesisTopic);
		if (title!=null) {
			thesisTopic.setTopic_title(title);
		}
		if (desc!=null) {
			thesisTopic.setTopic_desc(desc);
		}
		try {
			if (start_date!=null) {
				thesisTopic.setStart_date(new SimpleDateFormat("dd/MM/yyyy").parse(start_date));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (supervisor!=null) {
			thesisTopic.setSupervisor(supervisor);
		}
		if (contact_email!=null) {
			thesisTopic.setContact_email(contact_email);
		}
		if (must_have!=null) {
			thesisTopic.setMust_have(must_have);
		}
		if (nice_to_have!=null) {
			thesisTopic.setNice_to_have(nice_to_have);
		}
		final ThesisTopic updatedTopic = thesisTopicRepository.save(thesisTopic);
		return ResponseEntity.ok(updatedTopic);
	}



	@GetMapping("/readall")
	public @ResponseBody Iterable<ThesisTopic> getAllTopics() {
		return thesisTopicRepository.findAll();
	}

	@PostMapping("/delete")
	public @ResponseBody void deleteThesisTopic (@RequestParam long thesis_id) throws ResourceNotFoundException {
		ThesisTopic thesisTopic = thesisTopicRepository.findById(thesis_id)
				.orElseThrow(() -> new ResourceNotFoundException("Thesis Topic not found for this id:: "+ thesis_id));
		thesisTopicRepository.deleteById(thesis_id);
	}
}
