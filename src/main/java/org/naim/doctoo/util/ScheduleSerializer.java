package org.naim.doctoo.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.naim.doctoo.model.Day;
import org.naim.doctoo.model.Days;
import org.naim.doctoo.model.Schedule;

public class ScheduleSerializer {
	
//	public static String serialize(Schedule schedule) {
//		StringJoiner res= new StringJoiner(",");
//		String unavailableDay= "0,0,0,0";
//		
//		
//		List<Day> days =  schedule.getDays().stream().collect(Collectors.toList());
//		
//		Collections.sort(days, new Comparator<Day>() {
//            @Override
//            public int compare(Day o1, Day o2) {
//            	if(o1.getName().ordinal() > o2.getName().ordinal())
//                    return 1;
//                else if(o1.getName().ordinal() < o2.getName().ordinal())
//                    return -1;
//                else
//                    return 1;
//            }
//        });
//		
//		
//		for (Day day : days) {
//			if(!day.isAvailable()) res.add(unavailableDay);
//			else {
//				res.add(day.getMorningStart());
//				res.add(day.getMorningEnd());
//				res.add(day.getEveningStart());
//				res.add(day.getEveningEnd());
//			}
//		}
//		
//		return res.toString();
//	}
//	
//	public static Schedule deSerialize(String str) {
//		Schedule schedule = new Schedule();
//		HashSet<Day> days = new HashSet<Day>();
//		String[] split = str.split(",");
//		for (int i = 0; i < split.length; i+=4) {
//			Day newDay= new Day();
//			newDay.setName(Days.values()[i/4]);
//			if(split[i].equals("0")) newDay.setAvailable(false);
//			else newDay.setAvailable(true);
//			
//			newDay.setMorningStart(split[i]);
//			newDay.setMorningEnd(split[i+1]);
//			newDay.setEveningStart(split[i+2]);
//			newDay.setEveningEnd(split[i+3]);
//			days.add(newDay);
//		}
//		schedule.setDays(days);
//		return schedule;
//	}
}
