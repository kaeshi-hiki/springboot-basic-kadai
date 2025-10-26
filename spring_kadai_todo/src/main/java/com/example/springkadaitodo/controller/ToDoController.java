package com.example.springkadaitodo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.service.ToDoService;

@Controller
public class ToDoController {
	
	private final ToDoService  toDoService;
	public ToDoController(ToDoService toDoService) {
		this.toDoService = toDoService;
	}
	
	@GetMapping("/todo")
	public String adminToDo(Model model) {
		List<ToDo> todos = toDoService.getAllToDos();
		model.addAttribute("todos", todos);
		return "todoView";
	}
	
	@PostMapping("/register")
	public String registerToDo(RedirectAttributes redirectAttributes,
			@RequestParam("title") String title,
			@RequestParam("priority") String priority,
			@RequestParam("status") String status) {
		try {
			toDoService.createToDo(title, priority, status);
			redirectAttributes.addFlashAttribute("successMessage","タスク登録が完了しました。");
		} catch(IllegalArgumentException e){
			redirectAttributes.addFlashAttribute("failureMessage",e.getMessage());
			redirectAttributes.addFlashAttribute("title",title);
			redirectAttributes.addFlashAttribute("priority",priority);
			redirectAttributes.addFlashAttribute("status",status);
		}
		return "redirect:/todo";
		
	}

}
