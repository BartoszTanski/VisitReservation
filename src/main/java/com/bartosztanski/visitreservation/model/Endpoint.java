package com.bartosztanski.visitreservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Endpoint {
	private String path;
	private String method;
	private String requires;
	private String returns;
}
