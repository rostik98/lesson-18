package ua.lviv.lgs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.lgs.domain.Participant;
import ua.lviv.lgs.service.ParticipantService;

@Controller
public class ParticipantController {
	@Autowired
	private ParticipantService participantService;

	@GetMapping("/")
	public String init(HttpServletRequest req) {
		req.setAttribute("participants", participantService.findAllParticipants());
		req.setAttribute("mode", "PARTICIPANT_VIEW");
		return "index";
	}

	@GetMapping("/new")
	public String newParticipant(HttpServletRequest req) {
		req.setAttribute("mode", "PARTICIPANT_CREATE");
		return "index";
	}

	@RequestMapping(path = { "/save" }, method = RequestMethod.POST)
	public String save(@ModelAttribute Participant participant, HttpServletRequest request) {
		participantService.save(participant);
		request.setAttribute("participants", participantService.findAllParticipants());
		request.setAttribute("mode", "PARTICIPANT_VIEW");
		return "index";
	}

	@GetMapping("/update")
	public String update(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("participant", participantService.findOne(id));
		request.setAttribute("mode", "PARTICIPANT_EDIT");
		return "index";
	}

	@GetMapping("/delete")
	public String deleteParticipant(@RequestParam int id, HttpServletRequest request) {
		participantService.delete(id);
		request.setAttribute("participants", participantService.findAllParticipants());
		request.setAttribute("mode", "PARTICIPANT_VIEW");
		return "index";
	}
}
