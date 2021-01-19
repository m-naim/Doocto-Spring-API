package org.naim.doctoo.util;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;

import org.naim.doctoo.model.Day;
import org.naim.doctoo.model.Days;
import org.naim.doctoo.model.Schedule;

public class ScheduleSerializer {
	
	public static String serialize(Schedule schedule) {
		StringJoiner res= new StringJoiner(",");
		String unavailableDay= "0,0,0,0";
		
		Set<Day> days = schedule.getDays();
		for (Day day : days) {
			if(!day.isAvailable()) res.add(unavailableDay);
			else {
				res.add(day.getMorningStart());
				res.add(day.getMorningEnd());
				res.add(day.getEveningStart());
				res.add(day.getEveningEnd());
			}
		}
		System.out.println(res);
		
		return res.toString();
	}
	
	public static Schedule deSerialize(String str) {
		Schedule schedule = new Schedule();
		HashSet<Day> days = new HashSet<Day>();
		String[] split = str.split(",");
		for (int i = 0; i < split.length; i+=4) {
			Day newDay= new Day();
			newDay.setName(Days.values()[i/4]);
			if(split[i]=="0") newDay.setAvailable(false);
			else {
				newDay.setEveningStart(split[i]);
				newDay.setEveningEnd(split[i+1]);
				newDay.setMorningStart(split[i+2]);
				newDay.setMorningEnd(split[i+3]);
			}
		}
		
		return null;
	}
}
