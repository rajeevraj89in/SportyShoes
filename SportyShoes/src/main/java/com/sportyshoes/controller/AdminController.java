package com.sportyshoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sportyshoes.dao.UserRepository;
import com.sportyshoes.model.Products;
import com.sportyshoes.model.User;

@Controller
public class AdminController {
	
	@Autowired
	UserRepository userRepo;

	@RequestMapping("/admin")
	public String home(Model model) {
		model.addAttribute("title", "Sporty Shoes: Dashboard");
		return "admin/home";
	}

	@RequestMapping("/manageUser")
	public String manageUser(Model model) {
		List<User> users = this.userRepo.findAll();
		model.addAttribute("title", "Sporty Shoes: Manage User");
		model.addAttribute("user", users);
		return "admin/manage_user";
	}

}
