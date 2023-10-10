package com.bartosztanski.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.bartosztanski.visitreservation.controller.VisitController;
import com.bartosztanski.visitreservation.error.RestResponseEntityExceptionHandler;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.model.ErrorMessage;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.service.VisitService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private VisitService visitService;
	
	@InjectMocks
	private VisitController visitController;
	
	private JacksonTester<Visit> jsonVisit;
	private JacksonTester<ErrorMessage> jsonErrorMessage;
	private JacksonTester<VisitBookingRequest> jsonBookRequest;
	
	@BeforeEach
    public void setup() {
		
		JacksonTester.initFields(this, new ObjectMapper());
	    
	    mockMvc = MockMvcBuilders.standaloneSetup(visitController)
	            .setControllerAdvice(new RestResponseEntityExceptionHandler())
	            .addFilters()
	            .build();
    }
	
	@Test
	@DisplayName("Can retrive by id when exists")
    public void canRetrieveByIdWhenExists() throws Exception {
        // given
		Date date = new Date();
        when(visitService.getById(Long.valueOf(123)))
                .thenReturn(new Visit(Long.valueOf(123),date,new Client(),15,new Employee(),true));

        // when
        MockHttpServletResponse response = this.mockMvc
        		.perform(get("/api/v1/visit/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response
        			.getContentAsString())
        			.isEqualTo(
        					jsonVisit.write(
        						new Visit(
        								Long.valueOf(123),
        								date,
        								new Client(),
        								15,
        								new Employee(),
        								true))
        					.getJson());
        
    }
	
	@Test
	@DisplayName("Can retrive by id when NOT exists")
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
		
        // given
        when(visitService.getById(Long.valueOf(124)))
                .thenThrow(new NoSuchElementException());

        // when
        MockHttpServletResponse response = mockMvc
        		.perform(get("/api/v1/visit/124")
	                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response
        		.getContentAsString())
        		.isEqualTo(jsonErrorMessage
        				.write(new ErrorMessage(
        						HttpStatus.NOT_FOUND,
        						"Element with given id does not exist"))
        				.getJson());
    }
	
	@Test
	@DisplayName("Can book a visit by id when exists")
    public void canBookAVisitByIdWhenExists() throws Exception {
		
		//given
		Client client = Client
				.builder()
				.id(UUID.fromString("77f4ea4a-e602-4cb7-a64d-2aed23014be5"))
				.firstName("Jan")
				.lastName("Kowalski")
				.phoneNumber(Long.valueOf(234567890))
				.emailAddress("jan.kowalski@gmail.com")
				.visits(null)
				.build();
		
		Long visitId = Long.valueOf(123);
		Visit visit = new Visit(visitId,new Date(),client,15,new Employee(),false);
		
		VisitBookingRequest bookRequest = new VisitBookingRequest(visitId, client);
		when(visitService.book(any(VisitBookingRequest.class))).thenReturn(visit);
		
		// when
        MockHttpServletResponse response = mockMvc
        		.perform(post("/api/v1/visit/book/")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(jsonBookRequest.write(bookRequest).getJson()))
        		.andReturn()
        		.getResponse();


        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
        		.isEqualTo(jsonVisit.write(visit).getJson());
    }
	
	@Test
	@DisplayName("Can NOT book a visit by id when NOT exists")
    public void canNotBookAVisitByIdWhenNotExists() throws Exception {
		
		//given
		Client client = Client
				.builder()
				.id(UUID.fromString("77f4ea4a-e602-4cb7-a64d-2aed23014be5"))
				.firstName("Jan")
				.lastName("Kowalski")
				.phoneNumber(Long.valueOf(234567890))
				.emailAddress("jan.kowalski@gmail.com")
				.visits(null)
				.build();
		
		Long visitId = Long.valueOf(124);
		Visit visit = new Visit(visitId,new Date(),client,15,new Employee(),false);
		
		VisitBookingRequest bookRequest = new VisitBookingRequest(visitId, client);
		when(visitService.book(any(VisitBookingRequest.class))).thenThrow(new NoSuchElementException());
		
		
        // when
        MockHttpServletResponse response = mockMvc
        		.perform(post("/api/v1/visit/book/")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(jsonBookRequest.write(bookRequest).getJson()))
        		.andReturn()
        		.getResponse();

        // then
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response
        		.getContentAsString())
        		.isEqualTo(jsonErrorMessage
        				.write(new ErrorMessage(
        						HttpStatus.NOT_FOUND,
        						"Element with given id does not exist"))
        				.getJson());
    }
}
