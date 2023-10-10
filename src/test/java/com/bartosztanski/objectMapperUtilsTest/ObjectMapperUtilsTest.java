package com.bartosztanski.objectMapperUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bartosztanski.visitreservation.entity.ClientEntity;
import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.entity.VisitEntity;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.utils.ObjectMapperUtils;

@SpringBootTest(classes =ObjectMapperUtils.class)
public class ObjectMapperUtilsTest {
	
	static Employee employee;
	static EmployeeEntity employeeEntity;
	static Visit visit;
	static VisitEntity visitEntity;
	static Client client;
	static ClientEntity clientEntity;
	
	static Calendar calendar = Calendar.getInstance();
	static Date date = calendar.getTime();
	
	@BeforeAll
	static void init() {
		
		UUID clientId = UUID.fromString("1C");
		UUID employeeId = UUID.fromString("1C");
		
		visit = Visit.builder()
				.id(Long.valueOf(100001))
				.startTime(date)
				.available(false)
				.duration(15)
				.employee(Employee.builder().id(employeeId).firstName("John").lastName("Snow").build())
				.client(Client.builder().id(clientId).build())
				.build();
		
		visitEntity = VisitEntity.builder()
				.id(Long.valueOf(100001))
				.startTime(date)
				.available(false)
				.duration(15)
				.employee(EmployeeEntity.builder().id(employeeId).
						firstName("John").lastName("Snow").build())
				.client(ClientEntity.builder().id(clientId).build())
				.build();
		
		clientEntity = ClientEntity.builder()
				.id(employeeId)
				.firstName("Jan")
				.lastName("Kowalski")
				.phoneNumber(Long.valueOf(234567890))
				.emailAddress("jan.kowalski@gmail.com")
				.visits(List.of(visitEntity))
				.build();
		
		client = Client.builder()
				.id(clientId)
				.firstName("Jan")
				.lastName("Kowalski")
				.phoneNumber(Long.valueOf(234567890))
				.emailAddress("jan.kowalski@gmail.com")
				.visits(List.of(visit))
				.build();
				
		
		employee = Employee.builder()
				.id(employeeId)
				.firstName("John")
				.lastName("Snow")
				.phoneNumber(Long.valueOf(123456789))
				.emailAddress("john.snow@gmail.com")
				.visits(List.of(visit))
				.build();
		
		employeeEntity = EmployeeEntity.builder()
				.id(employeeId)
				.firstName("John")
				.lastName("Snow")
				.phoneNumber(Long.valueOf(123456789))
				.emailAddress("john.snow@gmail.com")
				.visits(List.of(visitEntity))
				.build();
	}
	
	@Test
	@DisplayName("Employee entity mapped correctly to Employee model")
	public void ShouldMapEmployeeEntityToEmployeeCorrectly() {
		
		Employee _employee = ObjectMapperUtils.map(employeeEntity, Employee.class);
		assertEquals(_employee.getId(), employee.getId());
		assertEquals(_employee.getFirstName(), employee.getFirstName());
		assertEquals(_employee.getLastName(), employee.getLastName());
		assertEquals(_employee.getPhoneNumber(), employee.getPhoneNumber());
		assertEquals(_employee.getEmailAddress(), employee.getEmailAddress());
		assertEquals(_employee.getVisits().get(0).getId(), employee.getVisits().get(0).getId());
		
	}
	
	@Test
	@DisplayName("Employee model mapped correctly to Employee entity")
	public void ShouldMapEmployeeToEmployeeEntityCorrectly() {
		
		EmployeeEntity _employeeEntity = ObjectMapperUtils.map(employee, EmployeeEntity.class);
		assertEquals(_employeeEntity.getId(), employeeEntity.getId());
		assertEquals(_employeeEntity.getFirstName(), employeeEntity.getFirstName());
		assertEquals(_employeeEntity.getLastName(), employeeEntity.getLastName());
		assertEquals(_employeeEntity.getPhoneNumber(), employeeEntity.getPhoneNumber());
		assertEquals(_employeeEntity.getEmailAddress(), employeeEntity.getEmailAddress());
		assertEquals(_employeeEntity.getVisits().get(0).getId(), employeeEntity.getVisits().get(0).getId());
		
	}
	
	@Test
	@DisplayName("Client entity mapped correctly to Client model")
	public void ShouldMapClientEntityToClientCorrectly() {
		
		Client _client = ObjectMapperUtils.map(clientEntity, Client.class);
		assertEquals(_client.getId(), client.getId());
		assertEquals(_client.getFirstName(), client.getFirstName());
		assertEquals(_client.getLastName(), client.getLastName());
		assertEquals(_client.getPhoneNumber(), client.getPhoneNumber());
		assertEquals(_client.getEmailAddress(), client.getEmailAddress());
		assertEquals(_client.getVisits().get(0).getId(), client.getVisits().get(0).getId());
		
	}
	
	@Test
	@DisplayName("Client model mapped correctly to Client entity")
	public void ShouldMapClientToClientEntityCorrectly() {
		
		ClientEntity _clientEntity = ObjectMapperUtils.map(client, ClientEntity.class);
		assertEquals(_clientEntity.getId(), clientEntity.getId());
		assertEquals(_clientEntity.getFirstName(), clientEntity.getFirstName());
		assertEquals(_clientEntity.getLastName(), clientEntity.getLastName());
		assertEquals(_clientEntity.getPhoneNumber(), clientEntity.getPhoneNumber());
		assertEquals(_clientEntity.getEmailAddress(), clientEntity.getEmailAddress());
		assertEquals(_clientEntity.getVisits().get(0).getId(), clientEntity.getVisits().get(0).getId());
		
	} 
	
	@Test
	@DisplayName("Visit entity mapped correctly to Visit model")
	public void ShouldMapVisitEntityToVisitCorrectly() {
		
		Visit _visit = ObjectMapperUtils.map(visitEntity, Visit.class);
		assertEquals(_visit.getId(), visit.getId());
		assertEquals(_visit.getStartTime(), visit.getStartTime());
		assertEquals(_visit.getDuration(), visit.getDuration());
		assertEquals(_visit.getEmployee().getFirstName(), visit.getEmployee().getFirstName());
		assertEquals(_visit.getEmployee().getLastName(), visit.getEmployee().getLastName());
		assertEquals(_visit.getClient().getId(), visit.getClient().getId());
		
	}
	
	@Test
	@DisplayName("Visit model mapped correctly to Visit entity")
	public void ShouldMapVisitToVisitEntityCorrectly() {
		
		VisitEntity _visitEntity = ObjectMapperUtils.map(visit, VisitEntity.class);
		assertEquals(_visitEntity.getId(), visitEntity.getId());
		assertEquals(_visitEntity.getStartTime(), visitEntity.getStartTime());
		assertEquals(_visitEntity.getDuration(), visitEntity.getDuration());
		assertEquals(_visitEntity.getEmployee().getId(), visitEntity.getEmployee().getId());
		assertEquals(_visitEntity.getClient().getId(), visitEntity.getClient().getId());
		
	}
	
	
	
}
