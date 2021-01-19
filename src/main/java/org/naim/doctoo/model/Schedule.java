package org.naim.doctoo.model;

import java.util.Set;

import lombok.Data;

@Data
public class Schedule {
	private Set<Day> days;
}
