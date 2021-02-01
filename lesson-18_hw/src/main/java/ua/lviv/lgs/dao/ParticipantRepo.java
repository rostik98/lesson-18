package ua.lviv.lgs.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import ua.lviv.lgs.domain.Participant;

@Repository
public class ParticipantRepo {

	private List<Participant> participants = new ArrayList<>();

	@PostConstruct
	public void init() {
	}

	public List<Participant> findAllParticipants() {
		return participants;
	}

	public Participant findOne(Integer id) {
		return participants.stream().filter(participant -> participant.getId() == id).findFirst().orElse(null);
	}

	public void save(Participant participant) {
		Participant participantToUpdate = null;

		if (participant.getId() != null) {
			participantToUpdate = findOne(participant.getId());
			int participantIndex = participants.indexOf(participantToUpdate);
			participantToUpdate.setName(participant.getName());
			participantToUpdate.setEmail(participant.getEmail());
			participantToUpdate.setLevel(participant.getLevel());
			participantToUpdate.setPrimarySkills(participant.getPrimarySkills());
			participants.set(participantIndex, participantToUpdate);
		} else {
			// save
			participant.setId(participants.size() + 1);
			participants.add(participant);
		}
	}

	public void delete(Integer id) {
		Iterator<Participant> iter = participants.iterator();
		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				iter.remove();
			}
		}
	}
}
