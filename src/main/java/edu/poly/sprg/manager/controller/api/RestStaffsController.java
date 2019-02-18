package edu.poly.sprg.manager.controller.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.sprg.manager.entity.Staffs;
import edu.poly.sprg.manager.model.DepartsDTO;
import edu.poly.sprg.manager.model.StaffsDTO;
import edu.poly.sprg.manager.service.StaffsService;

@RestController
@RequestMapping("/api/v2")
public class RestStaffsController {

	@Autowired
	private StaffsService staffsService;

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/list-staff", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<StaffsDTO>> listStaffs(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {

		PageRequest pageRequest = new PageRequest(pageNumber, 4);
		Page<Staffs> staffs = staffsService.findAll(pageRequest);

		try {
			List<StaffsDTO> staffsDTOs = new ArrayList<>();
			ResponseEntity<List<StaffsDTO>> responseEntity = new ResponseEntity<>(staffsDTOs, HttpStatus.OK);

			for (Staffs staff : staffs.getContent()) {
				StaffsDTO dto = new StaffsDTO();
				dto.setId(staff.getId());
				dto.setBirthDay(staff.getBirthDay());
				dto.setDeparts(new DepartsDTO(staff.getDeparts().getId(), staff.getDeparts().getName()));
				dto.setEmail(staff.getEmail());
				dto.setGender(staff.isGender());
				dto.setName(staff.getName());
				dto.setNotes(staff.getNotes());
				dto.setPhone(staff.getPhone());
				dto.setPhoto(staff.getPhoto());
				dto.setSalary(staff.getSalary());

				staffsDTOs.add(dto);
			}
			model.addAttribute("data", staffs);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<List<StaffsDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(HttpServletRequest request,
			@RequestParam("file") MultipartFile uploadfile) {
		try {
			String uploadRootPath = request.getServletContext().getRealPath("/upload/");
			File uploadRootDir = new File(uploadRootPath);
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			String name = uploadfile.getOriginalFilename();
			if (name != null && name.length() > 0) {
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(uploadfile.getBytes());
				stream.close();
			}
			ResponseEntity<String> responseEntity = new ResponseEntity<>(name, HttpStatus.OK);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value = "/delete-staff", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<StaffsDTO>> deleteStaffsAndShowPaging(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "id", required = false, defaultValue = "0") int id) {

				staffsService.delete(staffsService.getOne(id));

		PageRequest pageRequest = new PageRequest(pageNumber, 4);
		Page<Staffs> staffs = staffsService.findAll(pageRequest);

		try {
			List<StaffsDTO> staffsDTOs = new ArrayList<>();
			ResponseEntity<List<StaffsDTO>> responseEntity = new ResponseEntity<>(staffsDTOs, HttpStatus.OK);

			for (Staffs staff : staffs.getContent()) {
				StaffsDTO dto = new StaffsDTO();
				dto.setId(staff.getId());
				dto.setBirthDay(staff.getBirthDay());
				dto.setDeparts(new DepartsDTO(staff.getDeparts().getId(), staff.getDeparts().getName()));
				dto.setEmail(staff.getEmail());
				dto.setGender(staff.isGender());
				dto.setName(staff.getName());
				dto.setNotes(staff.getNotes());
				dto.setPhone(staff.getPhone());
				dto.setPhoto(staff.getPhoto());
				dto.setSalary(staff.getSalary());

				staffsDTOs.add(dto);
			}
			model.addAttribute("data", staffs);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<List<StaffsDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-staff", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<StaffsDTO> getStaff(Model model, @RequestParam("id") int id) {
		try {
			Staffs staff = staffsService.getOne(id);

			StaffsDTO dto = new StaffsDTO();
			dto.setId(staff.getId());
			dto.setBirthDay(staff.getBirthDay());
			dto.setDeparts(new DepartsDTO(staff.getDeparts().getId(), staff.getDeparts().getName()));
			dto.setEmail(staff.getEmail());
			dto.setGender(staff.isGender());
			dto.setName(staff.getName());
			dto.setNotes(staff.getNotes());
			dto.setPhone(staff.getPhone());
			dto.setPhoto(staff.getPhoto());
			dto.setSalary(staff.getSalary());

			ResponseEntity<StaffsDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
			return responseEntity;

		} catch (Exception e) {
			return new ResponseEntity<StaffsDTO>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getLang", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<Integer> getLang(HttpServletRequest request) {
		try {
			// 1 is vietnamese
			int value = 1;

			Locale locale = (Locale) request.getSession().getAttribute("URL_LOCALE_ATTRIBUTE_NAME");
			if (locale == null || locale == Locale.ENGLISH) {
				locale = Locale.ENGLISH;
				value = 0;
				// 0 is english
			}
			ResponseEntity<Integer> responseEntity = new ResponseEntity<>(value, HttpStatus.OK);

			return responseEntity;
		} catch (Exception e) {
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
}
