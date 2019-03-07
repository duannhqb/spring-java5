package edu.poly.sprg.manager.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.sprg.manager.entity.Departs;
import edu.poly.sprg.manager.model.DepartsDTO;
import edu.poly.sprg.manager.service.DepartsService;
import edu.poly.sprg.manager.validation.DepartsValidation;

@Controller
@RequestMapping("/depart")
public class DepartsController {

	@Autowired
	private DepartsService departsService;

	@Autowired
	private DepartsValidation departsValidation;
	
	@GetMapping("/add-depart")
	public String addDepart(Model model) {
		model.addAttribute("depart", new DepartsDTO());

		return "departs/addDeparts";
	}

	@PostMapping("/add-depart")
	public String addDepart(Model model, @ModelAttribute("depart") DepartsDTO departsDTO, BindingResult bindingResult) {
		departsValidation.validate(departsDTO, bindingResult);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("depart", departsDTO);
			
			return "departs/addDeparts";
		}
		
		Departs depart = new Departs();
		depart.setName(departsDTO.getName());

		departsService.save(depart);
		model.addAttribute("msg", "OK");

		return "redirect:/depart/add-depart";
	}

	@GetMapping("/")
	public String listDepart(Model model) {
		model.addAttribute("listDepart", departsService.findAll());
		model.addAttribute("sizeList", departsService.findAll().size());

		return "departs/listDeparts";
	}

	@GetMapping("/update-depart/{id}")
	public String updateDepart(Model model, @PathVariable int id) {
		DepartsDTO departsDTO = new DepartsDTO();
		Departs departs = departsService.getOne(id);

		if (departs != null) {
			departsDTO.setId(departs.getId());
			departsDTO.setName(departs.getName());
		}

		model.addAttribute("depart", departsDTO);

		return "departs/editDeparts";
	}

	@PostMapping("/update-depart")
	public String updateDepart(Model model, @ModelAttribute("depart") DepartsDTO departsDTO, BindingResult bindingResult) {
		departsValidation.validate(departsDTO, bindingResult);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("depart", departsDTO);
			
			return "departs/editDeparts";
		}
		
		Departs departs = new Departs();
		departs.setId(departsDTO.getId());
		departs.setName(departsDTO.getName());

		departsService.save(departs);

		return "redirect:/depart/";
	}

	@GetMapping("/remove-depart/{id}")
	public String removeDepart(@PathVariable int id) {
		departsService.delete(departsService.getOne(id));

		return "redirect:/depart/";
	}
	
}
