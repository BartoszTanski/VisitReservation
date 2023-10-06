package com.bartosztanski.visitreservation.service;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.model.VisitRequest;
import com.bartosztanski.visitreservation.model.VisitResponse;

@Service
public interface VisitService {

	VisitResponse getVisitById(Long id);

	VisitResponse bookNewVisit(VisitRequest visitRequest);

}
