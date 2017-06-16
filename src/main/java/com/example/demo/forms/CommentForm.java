package com.example.demo.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CommentForm {

	@NotEmpty
	@Size(max = 10)
	private String name;
	
	@NotEmpty
	@Size(max = 1000)
	private String content;
	
	public CommentForm() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
