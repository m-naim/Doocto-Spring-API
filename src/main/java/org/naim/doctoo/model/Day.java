package org.naim.doctoo.model;

import lombok.Data;

@Data
public class Day {
	private Days name;
	private boolean available;
	private String morningStart;
	private String morningEnd;
	private String eveningStart;
	private String eveningEnd;
}
