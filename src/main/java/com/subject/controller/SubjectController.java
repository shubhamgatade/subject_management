package com.subject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subject.entity.Subject;
import com.subject.entity.Teacher;
import com.subject.network.service.NetworkService;
import com.subject.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private NetworkService networkService;

	@PostMapping("/createTeacher")
	public Teacher createTeacher(@RequestBody Teacher teacher) throws Exception {
//		teacher = networkService.createTeacher(teacher);
//		teacher = networkService.createTeacherUsingRT(teacher);
		teacher = networkService.createTeacherUsingHTTPClient(teacher);
		return teacher;
	}

	@PostMapping("/create")
	public Subject create(@RequestBody Subject subject) {
		subject = subjectService.create(subject);
		return subject;
	}

	@GetMapping("/get")
	public Subject getById(@RequestParam("id") Integer id) {
		Subject subject = subjectService.getById(id);
		return subject;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
		String response = subjectService.deleteById(id);
		return response;
	}

	@PutMapping("/update/{id}")
	public Subject updateById(@PathVariable("id") Integer id, @RequestBody Subject subject) {
		Subject response = subjectService.updateById(id, subject);
		return response;

	}
}
