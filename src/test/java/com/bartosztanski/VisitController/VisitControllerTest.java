package com.bartosztanski.VisitController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Date;

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
import com.bartosztanski.visitreservation.model.Visit;
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
	
	@BeforeEach
    public void setup() {
		
		JacksonTester.initFields(this, new ObjectMapper());
	    
	    mockMvc = MockMvcBuilders.standaloneSetup(visitController)
	            .setControllerAdvice(new RestResponseEntityExceptionHandler())
	            .addFilters()
	            .build();
    }
	
	@Test
	@DisplayName("Should retrive by id when exists")
    public void canRetrieveByIdWhenExists() throws Exception {
        // given
		Date date = new Date();
        when(visitService.getById(Long.valueOf(123)))
                .thenReturn(new Visit(Long.valueOf(123),date,new Client(),15,new Employee(),true));

        // when
        MockHttpServletResponse response = this.mockMvc.perform(get("/api/v1/visit/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonVisit.write(new Visit(Long.valueOf(123),date,new Client(),15,new Employee(),true)).getJson()
        );
    }
}
