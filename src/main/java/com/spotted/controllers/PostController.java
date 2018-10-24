package com.spotted.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spotted.models.Post;
import com.spotted.services.PostService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class PostController {
	
	@Autowired
	PostService postService;
	
	private boolean notText(String text) {
		return text == null || text.isEmpty();
	}
	
	private boolean notImage(byte[] image) {
		return image == null || image.length == 0;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public Post save(@RequestBody Post post) throws Exception {
		if (this.notText(post.getText()) && this.notImage(post.getImage())) {
			throw new Exception("Text and image can not be empty or null simultaneously'");
		}
		return this.postService.save(post);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public List<Post> getAll() {
		return this.postService.getAll();
	}
	
	@RequestMapping(value = "/post/{nickname}", method = RequestMethod.GET)
	public List<Post> searchByNickname(@PathVariable String nickname) {
		return this.postService.searchByNickname(nickname);
	}
	
}
