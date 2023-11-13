package com.bartosztanski.visitreservation.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.model.MedicalField;
import com.bartosztanski.visitreservation.model.VisitType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitCreateRequest {
		
		private int duration;
		private VisitType type;
		private MedicalField field;
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		private Date startTime;
		private String employeeId;
		
}
