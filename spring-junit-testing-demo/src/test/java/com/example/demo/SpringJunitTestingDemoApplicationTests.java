package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class SpringJunitTestingDemoApplicationTests {


	@Autowired
	private ObjectMapper objMap;

	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext ctx;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void addEmployeeTest() throws Exception {
		Employee emp = new Employee();
		emp.setEmpName("vikas");
		emp.setEmpDepartment("sales");

		String inputJson = objMap.writeValueAsString(emp);

		MvcResult mvcResult = mockMvc
				.perform(post("/employee/addEmployee").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String resultJson = mvcResult.getResponse().getContentAsString();

		Response response = objMap.readValue(resultJson, Response.class);

		assertThat(response.isStatus() == Boolean.TRUE);
	}

	@Test
	public void getEmployeeTest() throws Exception {

		MvcResult mvcResult = mockMvc
				.perform(get("/employee/getAllEmployee").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

		String resultJson = mvcResult.getResponse().getContentAsString();

		Response response = objMap.readValue(resultJson, Response.class);

		assertThat(response.isStatus() == Boolean.TRUE);
	}
}
