package edu.poly.sprg.manager.controller.view;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.sprg.manager.service.UsersService;

@Controller
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@SuppressWarnings("deprecation")
	@GetMapping("/")
	public String listRecords(Model model, @RequestParam(value = "page", required = false) Optional<Integer> pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber.orElse(0), 4);
		model.addAttribute("pages", usersService.findAll(pageRequest));
		model.addAttribute("users", usersService.findAll());

		return "users/listUsers";
	}
}
