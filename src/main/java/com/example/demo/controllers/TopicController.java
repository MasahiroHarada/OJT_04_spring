package com.example.demo.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Topic;
import com.example.demo.forms.CommentForm;
import com.example.demo.forms.TopicForm;
import com.example.demo.services.TopicService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/topics")
public class TopicController {
	
	@Autowired
	TopicService topicService;

	/**
	 * �g�s�b�N�ꗗ
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("")
	public String index(Model model) {
		List<Topic> topics = topicService.findAll();
		model.addAttribute("topicList", topics);
		return "index";
	}
	
	/**
	 * �g�s�b�N�ڍ�
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws NotFoundException
	 */
	@GetMapping("/{id}")
	public String show(@PathVariable int id, ModelMap model) throws NotFoundException {

		Topic topic = topicService.findById(id);
		
		// �g�s�b�N��������Ȃ������ꍇ
		if (topic == null) {
			throw new EntityNotFoundException();
		}

		model.addAttribute("topic", topic);

		// �R�����g�ǉ����̓��̓G���[�Ń��_�C���N�g���Ă����ꍇ���l������
		if (model.containsKey("commentFormErrors")) {
			String key = BindingResult.MODEL_KEY_PREFIX + "commentForm";
			model.addAttribute(key, model.get("commentFormErrors")); // �G���[�̏��
			model.addAttribute("commentForm", model.get("commentForm")); // ���͒l���
		} else {
			// �����\��
			model.addAttribute("commentForm", new CommentForm());
		}

		return "show";
	}
	
	/**
	 * �g�s�b�N�V�K�쐬�t�H�[���\��
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("topicForm", new TopicForm());
		return "create";
	}
	
	/**
	 * �g�s�b�N�V�K�쐬
	 * 
	 * @param topicForm
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute TopicForm topicForm, BindingResult bindingResult) {

		// �o���f�[�V�����`�F�b�N
		if (bindingResult.hasErrors()) {
			return "create";
		}
		
		Topic topic = new Topic();
		topic.setSubmitter(topicForm.getSubmitter());
		topic.setTitle(topicForm.getTitle());
		topic.setContent(topicForm.getContent());
		
		Topic newTopic = topicService.save(topic);
		
		return "redirect:/topics/" + newTopic.getId();
	}

	/**
	 * �R�����g�ǉ�
	 * 
	 * @param topicId
	 * @param commentForm
	 * @param bindingResult
	 * @param redirectAttributes
	 * @return
	 * @throws NotFoundException
	 */
	@PostMapping("/{topicId}/comments")
	public String addComment(
			@PathVariable int topicId,
			@Valid @ModelAttribute CommentForm commentForm, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes) throws NotFoundException {

		// �o���f�[�V�����`�F�b�N
		if (bindingResult.hasErrors()) {
			// flash�ɃG���[���i�[���ă��_�C���N�g
			redirectAttributes.addFlashAttribute("commentFormErrors", bindingResult);
			redirectAttributes.addFlashAttribute("commentForm", commentForm);
			return "redirect:/topics/" + topicId;
		}
		
		Topic topic = topicService.findById(topicId);
		
		if (topic == null) {
			throw new EntityNotFoundException();
		}
		
		Comment comment = new Comment();
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());
		comment.setTopic(topic);
		
		topicService.addComment(comment);
		
		return "redirect:/topics/" + topicId;
	}
}