package com.bartosztanski.visitreservation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bartosztanski.visitreservation.dto.VisitBookingRequest;
import com.bartosztanski.visitreservation.error.ClientDetailsNotMatchesException;
import com.bartosztanski.visitreservation.error.VisitNotAvailableException;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.model.VisitsTableCell;
import com.bartosztanski.visitreservation.model.VisitsTableRow;
import com.bartosztanski.visitreservation.service.DateService;
import com.bartosztanski.visitreservation.service.VisitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visits/")
public class VisitControllerThymeleaf {
	
	private final VisitService visitService;
	private final DateService dateService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Visit> getVisitById(@PathVariable("id") Long id) {
		
		Visit response = visitService.getById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping({"/all","/"})
	public ModelAndView getAll() {
		
		List<Visit> visits = visitService.getAllAvailable();
		ModelAndView mav = new ModelAndView("list-visits-booking");
		mav.addObject("visits", visits);
		return mav;  
	}
	
	@PostMapping("/book")
	public ResponseEntity<Visit> bookVisit(
			@RequestBody VisitBookingRequest request)
					throws NoSuchElementException, VisitNotAvailableException {
		
		Visit visit = visitService.book(request);
		return new ResponseEntity<>(visit, HttpStatus.OK);
	}
	@PostMapping("/unBook")
	public ResponseEntity<Visit> unBookVisit(
			@RequestBody VisitBookingRequest request)
					throws NoSuchElementException, VisitNotAvailableException {
		
		visitService.unBook(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteVisit(@RequestBody Client client, Long visitId)
			throws NoSuchElementException, ClientDetailsNotMatchesException {
		
		visitService.delete(client, visitId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping({"/week"})
	public ModelAndView getByWeek(@RequestParam Date week) {
		List<Visit> visits = visitService.getAllAvailableByWeek(week);
		
		Map<Date, Integer> map = new HashMap<>();
		for (Visit visit : visits) {
			map.merge(visit.getStartTime(), 1, Integer::sum);
		}
		List<VisitsTableRow> visitsTableRows = new ArrayList<>();
		for(Entry<Date, Integer> entry : map.entrySet()) {
			VisitsTableCell cell = new VisitsTableCell(entry.getKey(),entry.getValue());
		}
		Date prevWeek = dateService.getPrevWeekMonday(week);
		Date nextWeek = dateService.getNextWeekMonday(week);

		ModelAndView mav = new ModelAndView("list-visits-booking-week");
		mav.addObject("visitsTableRows", visitsTableRows);
		mav.addObject("prevWeek", prevWeek);
		mav.addObject("nextWeek", nextWeek);
		return mav;  
	}
	
}
