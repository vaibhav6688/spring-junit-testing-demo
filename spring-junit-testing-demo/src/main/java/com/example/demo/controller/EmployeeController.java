package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Response;
import com.example.demo.repo.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;
	
	@PostMapping("/addEmployee")
	public Response addEmployee(@RequestBody Employee emp) {
		empRepo.save(emp);
	  return new Response(emp.getId() + "is inserted", Boolean.TRUE);
	}
	
	@GetMapping("/getAllEmployee")
	public Response getAllEmployees() {
	  List<Employee> emplList = empRepo.findAll();
	  return new Response("records count : "+emplList.size(), Boolean.TRUE);
	}
}
