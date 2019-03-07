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

import edu.poly.sprg.manager.entity.Records;
import edu.poly.sprg.manager.entity.Staffs;
import edu.poly.sprg.manager.model.RecordsDTO;
import edu.poly.sprg.manager.model.StaffsDTO;
import edu.poly.sprg.manager.service.RecordsService;
import edu.poly.sprg.manager.service.StaffsService;

@Controller
@RequestMapping("/record")
public class RecordsController {
	
	@Autowired
	private RecordsService recordsService;

	@Autowired
	private StaffsService staffsService;

	@SuppressWarnings("deprecation")
	@GetMapping("/")
	public String listRecords(Model model, @RequestParam(value = "page", required = false) Optional<Integer> pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber.orElse(0), 4);
		model.addAttribute("pages", recordsService.findAll(pageRequest));
		model.addAttribute("staffs", staffsService.findAll());

		return "records/listRecords";
	}

	@GetMapping("/update-record/{id}")
	public String updateRecord(Model model, @PathVariable int id) {
		
		Records records = recordsService.getOne(id);
		RecordsDTO recordsDTO = new RecordsDTO();
		recordsDTO.setId(records.getId());
		recordsDTO.setDate(records.getDate());
		recordsDTO.setReason(records.getReason());
		recordsDTO.setStaffs(new StaffsDTO(records.getStaffs().getId()));
		recordsDTO.setType(records.isType());
		
		model.addAttribute("record", recordsDTO);
		model.addAttribute("staffs", staffsService.findAll());

		return "records/editRecords";
	}

	@PostMapping("/update-record")
	public String updateRecord(HttpServletRequest request, Model model, @ModelAttribute("record") RecordsDTO recordsDTO) {
		return this.saveOrUpdate(request, model, recordsDTO, 2);
	}

	public String saveOrUpdate(HttpServletRequest request, Model model, RecordsDTO recordsDTO, int page) {
		Records records = new Records();
		
		records.setId(recordsDTO.getId());
		records.setDate(recordsDTO.getDate());
		records.setReason(recordsDTO.getReason());
		records.setStaffs(new Staffs(recordsDTO.getStaffs().getId()));
		records.setType(recordsDTO.isType());

		recordsService.save(records);

		String result ="";
		if(page==1) {
			model.addAttribute("record", new RecordsDTO());
			model.addAttribute("staffs", staffsService.findAll());
			model.addAttribute("msg", "success");
			
			result = "/records/addRecords";
		}else {
			result = "redirect:/record/";
		}
		
		return result;
	}
}
