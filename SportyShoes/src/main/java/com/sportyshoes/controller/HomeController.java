package com.sportyshoes.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.dao.ProductCategoryRepository;
import com.sportyshoes.dao.ProductRepository;
import com.sportyshoes.dao.UserRepository;
import com.sportyshoes.helper.Message;
import com.sportyshoes.model.ProductCategory;
import com.sportyshoes.model.Products;
import com.sportyshoes.model.User;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	ProductCategoryRepository productCategoryRepo;
	@Autowired
	ProductRepository productRepo;

	@RequestMapping("/")
	public String home(Model model, Principal principal) {
		if (principal != null) {
			System.out.println("Username : " + principal.getName());
			User olduser = userRepository.getUserByUserName(principal.getName());
		}
		User user = new User();
		List<Products> products = this.productRepo.findAll();
		List<ProductCategory> productCategory = this.productCategoryRepo.findAll();
		model.addAttribute("product_category", productCategory);
		model.addAttribute("product", products);
		model.addAttribute("user", user);
		model.addAttribute("title", "Sporty Shoes : Home");
		return "home";
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		try {
			if (!agreement) {
				System.out.println("You have not agreed the terms and conditions");
				throw new Exception("You have not agreed the terms and conditions");
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole("ROLL_USER");
			user.setEnabled(true);
			user.setImage("default");
			this.userRepository.save(user);
			System.out.println("Agreement: " + agreement);
			System.out.println("User:" + user);
			model.addAttribute("user", new User());
			session.setAttribute("Successfully Registered !!", "alert-success");
		} catch (Exception e) {
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something Went Wrong !!" + e.getMessage(), "alert-danger"));
		}
		return "home";
	}

	@RequestMapping(path="/test")
	public String test(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("title", "Sporty Shoes : Home");
		return "test";
	}
}
