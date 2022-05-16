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
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.stock.market.constants.CompanyConstants;
import com.stock.market.dto.CompanyDto;
import com.stock.market.service.CompanyService;

/**
 * The Class CompanyControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The company service. */
	@MockBean
	CompanyService companyService;

	/**
	 * Register company test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void registerCompanyTest() throws Exception {
		when(companyService.registerCompany(ArgumentMatchers.any(CompanyDto.class))).thenReturn(0);
		this.mockMvc.perform(post("/register").content(
				"{\"companyCode\": \"ghi\", \"companyName\": \"GHI Company\", \"companyTurnover\": \"20000000000\", \"companyCeo\": \"Riya Mittal\", \"companyWebsite\": \"http://www.google.com\", \"stockExchange\": \"NSE\"}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("true"))).andReturn();
	}

	/**
	 * Register company already exist test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void registerCompanyAlreadyExistTest() throws Exception {

		when(companyService.registerCompany(ArgumentMatchers.any(CompanyDto.class))).thenReturn(1);
		this.mockMvc
				.perform(post("/register").content("{\"companyCode\": \"abc\", \"companyName\": \"ABC Company\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CompanyConstants.COMPANY_ALREADY_EXIST))).andReturn();
	}

	/**
	 * Register company less company turnover test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void registerCompanyLessCompanyTurnoverTest() throws Exception {

		when(companyService.registerCompany(ArgumentMatchers.any(CompanyDto.class))).thenReturn(2);
		this.mockMvc.perform(post("/register").content(
				"{\"companyCode\": \"ghi\", \"companyName\": \"GHI Company\", \"companyTurnover\": \"20000\", \"companyCeo\": \"Riya Mittal\", \"companyWebsite\": \"http://www.google.com\", \"stockExchange\": \"NSE\"}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CompanyConstants.COMPANY_TURNOVER_IS_LESS))).andReturn();
	}

	/**
	 * Register company less field validation test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void registerCompanyLessFieldValidationTest() throws Exception {

		when(companyService.registerCompany(ArgumentMatchers.any(CompanyDto.class))).thenReturn(3);
		this.mockMvc.perform(post("/register").content(
				"{\"companyCode\": \"ghi\", \"companyName\": \"\", \"companyTurnover\": \"20000\", \"companyCeo\": \"Riya Mittal\", \"companyWebsite\": \"http://www.google.com\", \"stockExchange\": \"NSE\"}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CompanyConstants.FIELD_VALIDATION_FAILD))).andReturn();
	}

	/**
	 * Register company exception test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void registerCompanyExceptionTest() throws Exception {

		when(companyService.registerCompany(ArgumentMatchers.any(CompanyDto.class))).thenThrow(Exception.class);
		this.mockMvc
				.perform(post("/register").content("{\"companyCode\": \"abc\", \"companyName\": \"ABC Company\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString(CompanyConstants.INTERNAL_SERVER_ERROR))).andReturn();
	}

	/**
	 * Gets the company bycompany code test.
	 *
	 * @return the company bycompany code test
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void getCompanyBycompanyCodeTest() throws Exception {

		CompanyDto company = new CompanyDto();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");

		when(companyService.getCompanyByCompanyCode("abc")).thenReturn(company);

		this.mockMvc.perform(get("/info/abc")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CompanyConstants.COMPANY_FOUND))).andReturn();
	}

	/**
	 * Gets the company bycompany code null data test.
	 *
	 * @return the company bycompany code null data test
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void getCompanyBycompanyCodeNullDataTest() throws Exception {

		when(companyService.getCompanyByCompanyCode("abc")).thenReturn(null);

		this.mockMvc.perform(get("/info/abc")).andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString(CompanyConstants.COMPANY_NOT_FOUND))).andReturn();
	}

	/**
	 * Gets the company bycompany code exception test.
	 *
	 * @return the company bycompany code exception test
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void getCompanyBycompanyCodeExceptionTest() throws Exception {

		when(companyService.getCompanyByCompanyCode("abc")).thenThrow(Exception.class);

		this.mockMvc.perform(get("/info/abc")).andDo(print()).andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString(CompanyConstants.INTERNAL_SERVER_ERROR))).andReturn();
	}

	/**
	 * Gets the all company details test.
	 *
	 * @return the all company details test
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void getAllCompanyDetailsTest() throws Exception {

		List<CompanyDto> companies = new ArrayList<CompanyDto>();
		CompanyDto company = new CompanyDto();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");
		companies.add(company);

		when(companyService.getAllCompanyDetails()).thenReturn(companies);

		this.mockMvc.perform(get("/getall")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CompanyConstants.DATA_FETCH_SUCCESS))).andReturn();
	}

	/**
	 * Gets the all company details null data test.
	 *
	 * @return the all company details null data test
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void getAllCompanyDetailsNullDataTest() throws Exception {

		List<CompanyDto> companies = new ArrayList<CompanyDto>();
		when(companyService.getAllCompanyDetails()).thenReturn(companies);

		this.mockMvc.perform(get("/getall")).andDo(print()).andExpect(status().isNotFound())
				.andExpect(content().string(containsString(CompanyConstants.NO_COMPANY_FOUND))).andReturn();
	}

	/**
	 * Gets the all company details exception test.
	 *
	 * @return the all company details exception test
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void getAllCompanyDetailsExceptionTest() throws Exception {
		when(companyService.getAllCompanyDetails()).thenThrow(Exception.class);

		this.mockMvc.perform(get("/getall")).andDo(print()).andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString(CompanyConstants.INTERNAL_SERVER_ERROR))).andReturn();
	}

	/**
	 * Delete company by company code test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void deleteCompanyByCompanyCodeTest() throws Exception {

		when(companyService.deleteCompanyByCompanyCode(anyString())).thenReturn(true);

		this.mockMvc.perform(delete("/delete/abc")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(CompanyConstants.COMPANY_DELETED))).andReturn();
	}

	/**
	 * Delete company by company code exception test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	void deleteCompanyByCompanyCodeExceptionTest() throws Exception {

		when(companyService.deleteCompanyByCompanyCode(anyString())).thenThrow(Exception.class);

		this.mockMvc.perform(delete("/delete/abc")).andDo(print()).andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString(CompanyConstants.INTERNAL_SERVER_ERROR))).andReturn();
	}

}
