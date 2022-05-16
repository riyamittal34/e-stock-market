package com.stock.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.stock.market.dto.CompanyDto;
import com.stock.market.dto.CompanyResponse;
import com.stock.market.dto.ResponseMessage;
import com.stock.market.entity.CompanyDao;
import com.stock.market.repository.CompanyRepository;
import com.stock.market.serviceImpl.CompanyServiceImpl;
import com.stock.market.util.MockSample;

/**
 * The Class CompanyServiceTest.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CompanyServiceTest {

	/** The company service. */
	@InjectMocks
	CompanyServiceImpl companyService;

	/** The company repository. */
	@Mock
	CompanyRepository companyRepository;

	/** The rest template. */
	@Mock
	RestTemplate restTemplate;

	/** The response entity. */
	@SuppressWarnings("rawtypes")
	@MockBean
	ResponseEntity<CompanyResponse> responseEntity;

	@MockBean
	EurekaClient eurekaClient;

	@Mock
	Application application;

	@Mock
	InstanceInfo instance;

	/**
	 * Sets the up.
	 */
	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Register company test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void registerCompanyTest() throws Exception {

		CompanyDto company = MockSample.getCompanyObject();

		when(companyRepository.findByCompanyCode("ghi")).thenReturn(null);
		when(companyRepository.save(any(CompanyDao.class))).thenReturn(new CompanyDao());
		Integer isSuccessful = companyService.registerCompany(company);
		assertEquals(0, isSuccessful);
	}

	/**
	 * Register company already exist test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void registerCompanyAlreadyExistTest() throws Exception {

		CompanyDto company = MockSample.getCompanyObject();
		when(companyRepository.findByCompanyCode("abc")).thenReturn(new CompanyDao());
		Integer isSuccessful = companyService.registerCompany(company);
		assertEquals(1, isSuccessful);
	}

	/**
	 * Register company less turnover test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void registerCompanyLessTurnoverTest() throws Exception {
		CompanyDto company = MockSample.getCompanyObject();
		company.setCompanyTurnover("3434");
		when(companyRepository.findByCompanyCode("ghi")).thenReturn(null);
		Integer isSuccessful = companyService.registerCompany(company);
		assertEquals(2, isSuccessful);
	}

	/**
	 * Register company field validation failed test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void registerCompanyFieldValidationFailedTest() throws Exception {
		CompanyDto company = MockSample.getCompanyObject();
		company.setCompanyCeo("");
		when(companyRepository.findByCompanyCode("ghi")).thenReturn(null);
		Integer isSuccessful = companyService.registerCompany(company);
		assertEquals(3, isSuccessful);
	}

	/**
	 * Register company malformed URL test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void registerCompanyMalformedURLTest() throws Exception {
		CompanyDto company = MockSample.getCompanyObject();
		company.setCompanyWebsite("url");
		when(companyRepository.findByCompanyCode("ghi")).thenReturn(null);
		Integer isSuccessful = companyService.registerCompany(company);
		assertEquals(3, isSuccessful);
	}

	/**
	 * Register company turnover field datatype mismatch test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void registerCompanyTurnoverFieldDatatypeMismatchTest() throws Exception {
		CompanyDto company = MockSample.getCompanyObject();
		company.setCompanyTurnover("30Cr");
		when(companyRepository.findByCompanyCode("ghi")).thenReturn(null);
		Integer isSuccessful = companyService.registerCompany(company);
		assertEquals(3, isSuccessful);
	}

	/**
	 * Register company save exception test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void registerCompanySaveExceptionTest() throws Exception {

		Assertions.assertThrows(Exception.class, () -> {
			when(companyRepository.findByCompanyCode(anyString())).thenReturn(null);
			when(companyRepository.save(any(CompanyDao.class))).thenThrow(Exception.class);
			companyService.registerCompany(MockSample.getCompanyObject());
		});
	}

	/**
	 * Gets the company by company code test.
	 *
	 * @return the company by company code test
	 * @throws Exception the exception
	 */
	@Test
	void getCompanyByCompanyCodeTest() throws Exception {

		CompanyDao company = MockSample.getCompanyDaoObject();
		List<InstanceInfo> instances = new ArrayList<>();
		instances.add(instance);

		when(companyRepository.findByCompanyCode("abc")).thenReturn(company);
		when(eurekaClient.getApplication(ArgumentMatchers.anyString())).thenReturn(application);
		when(application.getInstances()).thenReturn(instances);
		when(instance.getHostName()).thenReturn("");
		when(instance.getPort()).thenReturn(0);
		CompanyDto companyDto = companyService.getCompanyByCompanyCode("abc");

		assertEquals(company.getCompanyCode(), companyDto.getCompanyCode());
		assertEquals(company.getCompanyId(), companyDto.getCompanyId());
		assertEquals(company.getCompanyName(), companyDto.getCompanyName());
	}

	/**
	 * Gets the company by company code stock price test.
	 *
	 * @return the company by company code stock price test
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	void getCompanyByCompanyCodeStockPriceTest() throws Exception {
		CompanyDao company = MockSample.getCompanyDaoObject();

		CompanyResponse<Double> response = new CompanyResponse<>();
		response.withData(200.0);
		ResponseMessage message = new ResponseMessage();
		message.setCode("LATEST_STOCK_PRICE_FETCHED");
		response.withMessage(message);
		ResponseEntity<CompanyResponse> entity = new ResponseEntity<CompanyResponse>(response, HttpStatus.OK);

		List<InstanceInfo> instances = new ArrayList<>();
		instances.add(instance);

		when(companyRepository.findByCompanyCode("abc")).thenReturn(company);
		when(eurekaClient.getApplication(ArgumentMatchers.anyString())).thenReturn(application);
		when(application.getInstances()).thenReturn(instances);
		when(instance.getHostName()).thenReturn("localhost");
		when(instance.getPort()).thenReturn(8080);
		when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.any(Class.class))).thenReturn(entity);
		CompanyDto companyDto = companyService.getCompanyByCompanyCode("abc");

		assertEquals(company.getCompanyCode(), companyDto.getCompanyCode());
		assertEquals(company.getCompanyId(), companyDto.getCompanyId());
		assertEquals(company.getCompanyName(), companyDto.getCompanyName());
	}

	/**
	 * Gets the all company details test.
	 *
	 * @return the all company details test
	 * @throws Exception the exception
	 */
	@Test
	void getAllCompanyDetailsTest() throws Exception {

		List<CompanyDao> mockCompanies = new ArrayList<CompanyDao>();
		mockCompanies.add(MockSample.getCompanyDaoObject());

		List<InstanceInfo> instances = new ArrayList<>();
		instances.add(instance);

		when(companyRepository.findAll()).thenReturn(mockCompanies);
		when(eurekaClient.getApplication(ArgumentMatchers.anyString())).thenReturn(application);
		when(application.getInstances()).thenReturn(instances);
		when(instance.getHostName()).thenReturn("");
		when(instance.getPort()).thenReturn(0);
		List<CompanyDto> companies = companyService.getAllCompanyDetails();

		assertEquals(1, companies.size());
	}

	/**
	 * Delete company by company code test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void deleteCompanyByCompanyCodeTest() throws Exception {

		List<InstanceInfo> instances = new ArrayList<>();
		instances.add(instance);

		when(companyRepository.findByCompanyCode("abc")).thenReturn(MockSample.getCompanyDaoObject());
		when(eurekaClient.getApplication(ArgumentMatchers.anyString())).thenReturn(application);
		when(application.getInstances()).thenReturn(instances);
		when(instance.getHostName()).thenReturn("");
		when(instance.getPort()).thenReturn(0);
		Boolean isSuccess = companyService.deleteCompanyByCompanyCode("abc");

		assertTrue(isSuccess);
	}

}
