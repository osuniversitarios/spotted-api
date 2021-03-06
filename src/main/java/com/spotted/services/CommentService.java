package com.spotted.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotted.models.Comment;
import com.spotted.repositories.CommentRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment) {
		comment.setDatetime(LocalDateTime.now(ZoneId.of("America/Recife")));
        return this.commentRepository.save(comment);
    }

    public List<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    public Comment getComment(Long id) throws Exception {
    	if(!this.commentRepository.existsById(id)) {
    		throw new Exception("There is no comment registered with this id in the system");
    	}
        return this.commentRepository.findById(id).get();
    }
    
    public void delete(Long id) {
    	this.commentRepository.deleteById(id);
	}
	
	public Comment edit(Comment comment) {
		return this.commentRepository.save(comment);
	}
}

