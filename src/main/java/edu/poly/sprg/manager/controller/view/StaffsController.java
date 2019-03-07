package edu.poly.sprg.manager.controller.view;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import edu.poly.sprg.manager.validation.StaffsValidation;

@Controller
@RequestMapping("/staff")
public class StaffsController {

	@Autowired
	private DepartsService departsService;

	@Autowired
	private StaffsService staffsService;

	@Autowired
	private StaffsValidation staffsValidation;

	@GetMapping("/add-staff")
	public String addStaff(Model model) {
		model.addAttribute("staff", new StaffsDTO());
		model.addAttribute("listDepart", departsService.findAll());

		return "staffs/addStaffs";
	}

	@PostMapping("/add-staff")
	public String addStaff(HttpServletRequest request, Model model, @ModelAttribute("staff") StaffsDTO staffsDTO,
			@RequestParam(value = "photoHistory", required = false) String photoHistory, BindingResult bindingResult) {

		staffsValidation.validate(staffsDTO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("staff", staffsDTO);
			model.addAttribute("listDepart", departsService.findAll());

			return "staffs/addStaffs";
		}

		return this.saveOrUpdate(request, model, staffsDTO, 1, photoHistory);
	}

	@SuppressWarnings("deprecation")
	@GetMapping("/")
	public String listStaffs(Model model,
			@RequestParam(value = "page", required = false) Optional<Integer> pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber.orElse(0), 4);
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
		staffsDTO.setLevel(staff.getLevel());

		model.addAttribute("staff", staffsDTO);
		model.addAttribute("listDepart", departsService.findAll());

		return "staffs/editStaffs";
	}

	@PostMapping("/update-staff")
	public String updateStaff(HttpServletRequest request, Model model, @ModelAttribute("staff") StaffsDTO staffsDTO,
			@RequestParam(value = "photoHistory", required = false) String photoHistor, BindingResult bindingResult) {

		staffsValidation.validate(staffsDTO, bindingResult);

		if (bindingResult.hasErrors()) {

			staffsDTO.setPhoto(photoHistor);
			model.addAttribute("staff", staffsDTO);
			model.addAttribute("listDepart", departsService.findAll());

			return "staffs/editStaffs";
		}

		return this.saveOrUpdate(request, model, staffsDTO, 2, photoHistor);
	}

	public String saveOrUpdate(HttpServletRequest request, Model model, StaffsDTO staffsDTO, int page,
			String photoHistory) {
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
		staff.setLevel(staffsDTO.getLevel());

		String name;
		try {
			MultipartFile file = staffsDTO.getFile();
			name = file.getOriginalFilename();
			if (name.equals("") || name.isEmpty()) {
				name = photoHistory;
			}
		} catch (Exception e) {
			name = photoHistory;
		}

		staff.setPhoto(name);
		staffsService.save(staff);

		String result = "";
		if (page == 1) {
			model.addAttribute("staff", new StaffsDTO());
			model.addAttribute("listDepart", departsService.findAll());
			model.addAttribute("msg", "success");

			result = "/staffs/addStaffs";
		} else {
			result = "redirect:/staff/";
		}

		return result;
	}

//	@GetMapping("/view-staff/{id}")
//	public String viewStaff(Model model, @PathVariable int id) {
//		Staffs staff = staffsService.getOne(id);
//		StaffsDTO staffsDTO = new StaffsDTO();
//		staffsDTO.setId(staff.getId());
//		staffsDTO.setBirthDay(staff.getBirthDay());
//		staffsDTO.setDeparts(new DepartsDTO(staff.getDeparts().getId(), staff.getDeparts().getName()));
//		staffsDTO.setEmail(staff.getEmail());
//		staffsDTO.setGender(staff.isGender());
//		staffsDTO.setName(staff.getName());
//		staffsDTO.setNotes(staff.getNotes());
//		staffsDTO.setPhone(staff.getPhone());
//		staffsDTO.setSalary(staff.getSalary());
//		staffsDTO.setPhoto(staff.getPhoto());
//		;
//
//		model.addAttribute("staff", staffsDTO);
//
//		return "staffs/viewStaffs";
//	}
}
