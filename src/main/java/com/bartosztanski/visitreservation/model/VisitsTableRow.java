package com.bartosztanski.visitreservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitsTableRow {
	VisitsTableCell monday;
	VisitsTableCell thuesday;
	VisitsTableCell wednesday;
	VisitsTableCell thursday;
	VisitsTableCell friday;
	VisitsTableCell saturday;
}
