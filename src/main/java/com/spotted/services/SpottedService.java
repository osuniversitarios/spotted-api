package com.spotted.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotted.models.Comment;
import com.spotted.models.Spotted;
import com.spotted.repositories.SpottedRepository;

@Service
public class SpottedService {

	@Autowired
	SpottedRepository spottedRepository;

	@Autowired
	CommentService commentService;

	public Spotted save(Spotted spotted) {
		spotted.setDatetime(LocalDateTime.now(ZoneId.of("America/Recife")));
		return this.spottedRepository.save(spotted);
	}

	public List<Spotted> getAll() {
		return this.spottedRepository.findAll();
	}

	public List<Spotted> getVisible(){
		return this.spottedRepository.findVisible();
	}

	public List<Spotted> getHidden(){
		return this.spottedRepository.findHidden();
	}
	
	public Spotted get(Long id) throws Exception {
		if (!this.spottedRepository.existsById(id)) {
			throw new Exception("This id is not registered in the system.");
		}
		return this.spottedRepository.findById(id).get();
	}

	public Comment addComment(Long id, Comment comment) throws Exception {
		if (!this.spottedRepository.existsById(id)) {
			throw new Exception("There is not a spotted registered with this id in the system");
		}
		
		this.commentService.save(comment);
		Spotted spotted = this.spottedRepository.getOne(id);
		spotted.addComment(comment);
		this.spottedRepository.save(spotted);
		return comment;
	}

	public Set<Comment> getComments(Long id) {
		Spotted spotted = this.spottedRepository.findById(id).get();
		return spotted.getComments();
	}

	public Spotted setVisible(Long id) throws Exception {
		if (!this.spottedRepository.existsById(id)) {
			throw new Exception("This id is not registered in the system.");
		}
		Spotted spotted;
		spotted = this.spottedRepository.getOne(id);
		spotted.setNumberOfComplaints(spotted.getNumberOfComplaints() + 1);
		if (spotted.getNumberOfComplaints() == 5) {
			boolean visible = !spotted.getVisible();
			spotted.setVisible(visible);
		}
		return this.spottedRepository.save(spotted);
	}
	
	public void delete(Long id) {
		this.spottedRepository.deleteById(id);
	}
}
