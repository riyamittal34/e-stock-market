package com.stock.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.market.entity.CompanyDao;
import com.stock.market.repository.CompanyRepository;
import com.stock.market.serviceImpl.CompanyServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyServiceTest {

	@InjectMocks
	CompanyServiceImpl companyService;

	@Mock
	CompanyRepository companyRepository;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerCompanyTest() throws Exception {

		CompanyDao company = getCompanyObject();

		when(companyRepository.findByCompanyCode("abc")).thenReturn(null);
		when(companyRepository.save(any(CompanyDao.class))).thenReturn(company);
		Boolean isSuccessful = companyService
				.registerCompany("{\"companyCode\": \"abc\", \"companyName\": \"ABC Company\"}");
		assertTrue(isSuccessful);
	}

	@Test
	public void registerCompanyMappingExceptionTest() throws Exception {
		Assertions.assertThrows(Exception.class, () -> {
			companyService.registerCompany("TestData");
		});
	}

	@Test
	public void registerCompanyAlreadyExistTest() throws Exception {

		CompanyDao company = getCompanyObject();

		when(companyRepository.findByCompanyCode("abc")).thenReturn(company);
		Boolean isSuccessful = companyService
				.registerCompany("{\"companyCode\": \"abc\", \"companyName\": \"ABC Company\"}");
		assertFalse(isSuccessful);
	}

	@Test
	public void registerCompanySaveExceptionTest() throws Exception {

		Assertions.assertThrows(Exception.class, () -> {
			when(companyRepository.findByCompanyCode(anyString())).thenReturn(null);
			when(companyRepository.save(any(CompanyDao.class))).thenThrow(Exception.class);
			companyService.registerCompany("{\"companyCode\": \"abc\", \"companyName\": \"ABC Company\"}");
		});
	}

	@Test
	public void getCompanyByCompanyCodeTest() throws Exception {

		CompanyDao company = getCompanyObject();

		when(companyRepository.findByCompanyCode("abc")).thenReturn(company);
		CompanyDao companyDao = companyService.getCompanyByCompanyCode("abc");

		assertEquals(company.getCompanyCode(), companyDao.getCompanyCode());
		assertEquals(company.getCompanyId(), companyDao.getCompanyId());
		assertEquals(company.getCompanyName(), companyDao.getCompanyName());
	}

	@Test
	public void getAllCompanyDetailsTest() throws Exception {

		List<CompanyDao> mockCompanies = new ArrayList<CompanyDao>();
		mockCompanies.add(getCompanyObject());

		when(companyRepository.findAll()).thenReturn(mockCompanies);
		List<CompanyDao> companies = companyService.getAllCompanyDetails();

		assertEquals(1, companies.size());
	}

	@Test
	public void deleteCompanyByCompanyCodeTest() throws Exception {

		when(companyRepository.findByCompanyCode("abc")).thenReturn(getCompanyObject());
		Boolean isSuccess = companyService.deleteCompanyByCompanyCode("abc");

		assertTrue(isSuccess);
	}

	private CompanyDao getCompanyObject() {
		CompanyDao company = new CompanyDao();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");
		return company;
	}
}
