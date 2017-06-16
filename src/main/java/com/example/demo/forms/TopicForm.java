package com.example.demo.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TopicForm {

	@NotEmpty
	@Size(max = 10, message = "10•¶ŽšˆÈ“à‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢")
	private String submitter;

	@NotEmpty
	@Size(max = 100, message = "100•¶ŽšˆÈ“à‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢")
	private String title;

	@NotEmpty
	@Size(max = 1000, message = "1000•¶ŽšˆÈ“à‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢")
	private String content;
	
	public TopicForm() {}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
