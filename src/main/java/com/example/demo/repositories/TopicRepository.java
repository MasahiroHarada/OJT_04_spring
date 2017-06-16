package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer>{

	public Topic findById(int id);
}
