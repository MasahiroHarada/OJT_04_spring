package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Topic;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.TopicRepository;

@Service
public class TopicService {

	@Autowired
	TopicRepository topicRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	public List<Topic> findAll() {
		return topicRepository.findAll();
	}
	
	public Topic findById(int id) {
		return topicRepository.findById(id);
	}
	
	public Topic save(Topic topic) {
		return topicRepository.save(topic);
	}
	
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}
}
