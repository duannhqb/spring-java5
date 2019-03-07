package edu.poly.sprg.manager.controller.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.poly.sprg.manager.service.DepartsService;
import edu.poly.sprg.manager.service.StaffsService;

@Controller
public class MainController {

	@Autowired
	private StaffsService staffsService;

	@Autowired
	private DepartsService departsService;

	@GetMapping("/")
	public String defaultMapping(Model model, HttpServletRequest httpServletRequest) {
		model.addAttribute("staffPoint", getPointByStaff());
		model.addAttribute("departPoint", getPointByDepart());
		model.addAttribute("staffs", getStaff());

		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "login";
	}

	private List<Object[]> getPointByStaff() {
		return staffsService.getPoint();
	}

	private List<Object[]> getPointByDepart() {
		return departsService.getPoint();
	}

	private List<Object[]> getStaff() {
		return staffsService.getStaff();
	}
}
