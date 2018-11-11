package com.spotted.controllers;

import com.spotted.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/")
@CrossOrigin(value = "*")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		this.commentService.delete(id);
	}
}
