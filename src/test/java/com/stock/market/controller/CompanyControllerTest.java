package com.stock.market.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.stock.market.entity.CompanyDao;
import com.stock.market.service.CompanyService;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CompanyService companyService;

	@Test
	public void registerCompanyTest() throws Exception {

		when(companyService.registerCompany(anyString())).thenReturn(true);
		this.mockMvc
				.perform(post("/api/v1.0/market/company/register")
						.content("{\"companyCode\": \"abc\", \"companyName\": \"ABC Company\"}"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("true")))
				.andReturn();
	}

	@Test
	public void registerCompanyAlreadyExistTest() throws Exception {

		when(companyService.registerCompany(anyString())).thenReturn(false);
		this.mockMvc
				.perform(post("/api/v1.0/market/company/register")
						.content("{\"companyCode\": \"abc\", \"companyName\": \"ABC Company\"}"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("COMPANY_ALREADY_EXIST"))).andReturn();
	}

	@Test
	public void registerCompanyExceptionTest() throws Exception {

		when(companyService.registerCompany(anyString())).thenThrow(Exception.class);
		this.mockMvc
				.perform(post("/api/v1.0/market/company/register")
						.content("{\"companyCode\": \"abc\", \"companyName\": \"ABC Company\"}"))
				.andDo(print()).andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("INTERNAL_SERVER_ERROR"))).andReturn();
	}

	@Test
	public void getCompanyBycompanyCodeTest() throws Exception {

		CompanyDao company = new CompanyDao();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");

		when(companyService.getCompanyByCompanyCode("abc")).thenReturn(company);

		this.mockMvc.perform(get("/api/v1.0/market/company/info/abc")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("COMPANY_FOUND"))).andReturn();
	}

	@Test
	public void getCompanyBycompanyCodeNullDataTest() throws Exception {

		when(companyService.getCompanyByCompanyCode("abc")).thenReturn(null);

		this.mockMvc.perform(get("/api/v1.0/market/company/info/abc")).andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString("COMPANY_NOT_FOUND"))).andReturn();
	}

	@Test
	public void getCompanyBycompanyCodeExceptionTest() throws Exception {

		when(companyService.getCompanyByCompanyCode("abc")).thenThrow(Exception.class);

		this.mockMvc.perform(get("/api/v1.0/market/company/info/abc")).andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("INTERNAL_SERVER_ERROR"))).andReturn();
	}

	@Test
	public void getAllCompanyDetailsTest() throws Exception {

		List<CompanyDao> companies = new ArrayList<CompanyDao>();
		CompanyDao company = new CompanyDao();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");
		companies.add(company);

		when(companyService.getAllCompanyDetails()).thenReturn(companies);

		this.mockMvc.perform(get("/api/v1.0/market/company/getall")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("DATA_FETCH_SUCCESS"))).andReturn();
	}

	@Test
	public void getAllCompanyDetailsNullDataTest() throws Exception {

		List<CompanyDao> companies = new ArrayList<CompanyDao>();
		when(companyService.getAllCompanyDetails()).thenReturn(companies);

		this.mockMvc.perform(get("/api/v1.0/market/company/getall")).andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString("NO_COMPANY_FOUND"))).andReturn();
	}

	@Test
	public void getAllCompanyDetailsExceptionTest() throws Exception {
		when(companyService.getAllCompanyDetails()).thenThrow(Exception.class);

		this.mockMvc.perform(get("/api/v1.0/market/company/getall")).andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("INTERNAL_SERVER_ERROR"))).andReturn();
	}

	@Test
	public void deleteCompanyByCompanyCodeTest() throws Exception {

		when(companyService.deleteCompanyByCompanyCode(anyString())).thenReturn(true);
		
		this.mockMvc.perform(delete("/api/v1.0/market/company/delete/abc")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("COMPANY_DELETED"))).andReturn();
	}
	
	@Test
	public void deleteCompanyByCompanyCodeExceptionTest() throws Exception {

		when(companyService.deleteCompanyByCompanyCode(anyString())).thenThrow(Exception.class);
		
		this.mockMvc.perform(delete("/api/v1.0/market/company/delete/abc")).andDo(print()).andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("INTERNAL_SERVER_ERROR"))).andReturn();
	}

}
