package edu.poly.sprg.manager.controller.view;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.sprg.manager.entity.Departs;
import edu.poly.sprg.manager.entity.Staffs;
import edu.poly.sprg.manager.model.DepartsDTO;
import edu.poly.sprg.manager.model.StaffsDTO;
import edu.poly.sprg.manager.service.DepartsService;
import edu.poly.sprg.manager.service.StaffsService;

@Controller
@RequestMapping("/staff")
public class StaffsController {

	@Autowired
	private DepartsService departsService;

	@Autowired
	private StaffsService staffsService;

	@GetMapping("/add-staff")
	public String addStaff(Model model) {
		model.addAttribute("staff", new StaffsDTO());
		model.addAttribute("listDepart", departsService.findAll());

		return "staffs/addStaffs";
	}

	@PostMapping("/add-staff")
	public String addStaff(HttpServletRequest request, Model model, @ModelAttribute("staff") StaffsDTO staffsDTO) {

		return this.saveOrUpdate(request, model, staffsDTO, "/add-staff");
	}

	@GetMapping("/")
	public String listStaffs(Model model,
			@RequestParam(value = "page", required = false) Optional<Integer> pageNumber) {

		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(pageNumber.orElse(0), 4);
		model.addAttribute("listStaff", staffsService.findAll(pageRequest));
		model.addAttribute("pages", staffsService.findAll(pageRequest));

		return "staffs/listStaffs";
	}

	@GetMapping("/update-staff/{id}")
	public String updateStaff(Model model, @PathVariable int id) {
		Staffs staff = staffsService.getOne(id);
		StaffsDTO staffsDTO = new StaffsDTO();
		staffsDTO.setId(staff.getId());
		staffsDTO.setBirthDay(staff.getBirthDay());
		staffsDTO.setDeparts(new DepartsDTO(staff.getDeparts().getId(), staff.getDeparts().getName()));
		staffsDTO.setEmail(staff.getEmail());
		staffsDTO.setGender(staff.isGender());
		staffsDTO.setName(staff.getName());
		staffsDTO.setNotes(staff.getNotes());
		staffsDTO.setPhone(staff.getPhone());
		staffsDTO.setSalary(staff.getSalary());
		staffsDTO.setPhoto(staff.getPhoto());
		;

		model.addAttribute("staff", staffsDTO);
		model.addAttribute("listDepart", departsService.findAll());

		return "staffs/editStaffs";
	}

	@PostMapping("/update-staff")
	public String updateStaff(HttpServletRequest request, Model model, @ModelAttribute("staff") StaffsDTO staffsDTO) {
		return this.saveOrUpdate(request, model, staffsDTO, "/");
	}

	public String saveOrUpdate(HttpServletRequest request, Model model, StaffsDTO staffsDTO, String page) {
		Staffs staff = new Staffs();
		staff.setId(staffsDTO.getId());
		staff.setBirthDay(staffsDTO.getBirthDay());
		staff.setDeparts(new Departs(staffsDTO.getDeparts().getId(), staffsDTO.getDeparts().getName()));
		staff.setEmail(staffsDTO.getEmail());
		staff.setGender(staffsDTO.isGender());
		staff.setName(staffsDTO.getName());
		staff.setNotes(staffsDTO.getNotes());
		staff.setPhone(staffsDTO.getPhone());
		staff.setSalary(staffsDTO.getSalary());
		
		MultipartFile file = staffsDTO.getFile();
		String name = file.getOriginalFilename();
		
		staff.setPhoto(name);
		staffsService.save(staff);

		return "redirect:/staff" + page;
	}
}
