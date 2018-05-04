package com.bizleap.training.assignment.monthversion;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.HashMap;

public class MonthMapGenerator2 {
	private Map<Integer, Object> monthMap = new HashMap<Integer, Object>();

	private class Month {
		private String name;
		private int numberOfDays, year;

		public Month(String name, int numberOfDays, int year) {
			this.name = name;
			this.numberOfDays = numberOfDays;
			this.year = year;
		}

		public String toString() {
			return this.year + "/" + this.name;
		}
	}

	private boolean isValid(int year) {
		return year >= 0;
	}

	private boolean isValid(int fromYear, int toYear) {
		return !(fromYear <= 0 || toYear <= 0 || fromYear > toYear);
	}

	public boolean isLeapYear(int year) {
		return year % 400 == 0;
	}

	private List<Month> createMonthsForYear(int fromYear) {
		List<Month> monthList = new ArrayList<Month>();

		monthList.add(new Month("January", 31, fromYear));

		if (isLeapYear(fromYear))
			monthList.add(new Month("February", 29, fromYear));
		else
			monthList.add(new Month("February", 28, fromYear));

		monthList.add(new Month("March", 31, fromYear));
		monthList.add(new Month("April", 30, fromYear));
		monthList.add(new Month("May", 31, fromYear));
		monthList.add(new Month("June", 30, fromYear));
		monthList.add(new Month("July", 31, fromYear));
		monthList.add(new Month("August", 31, fromYear));
		monthList.add(new Month("September", 30, fromYear));
		monthList.add(new Month("October", 31, fromYear));
		monthList.add(new Month("November", 30, fromYear));
		monthList.add(new Month("December", 31, fromYear));

		return monthList;
	}

	public Map<Integer, Object> createMonthMap(int fromYear, int toYear) {
		if (isValid(fromYear, toYear)) {
			for (int year = fromYear; year <= toYear; year++) {
				createMonthMap(year);
			}
		} else {
			monthMap.put(fromYear, "Range or years are invalid");
			monthMap.put(toYear, "Range or years are invalid");
		}
		return monthMap;
	}

	public Map<Integer, Object> createMonthMap(int year) {
		if (isValid(year)) {
			for (Month month : createMonthsForYear(year)) {
				addMonthToMap(month);
			}
		} else {
			monthMap.put(year, "Year is invalid");
		}
		return monthMap;
	}

	private List<Month> createMonthsForYearRange(int fromYear, int toYear) {
		List<Month> monthList = new ArrayList<Month>();
		for (int year = fromYear; year <= toYear; year++) {
			monthList.addAll(createMonthsForYear(year));
		}
		return monthList;
	}

	public Map<Integer, Object> createMonthMapRange(int fromYear, int toYear) {
		if (isValid(fromYear, toYear)) {
			for (Month month : createMonthsForYearRange(fromYear, toYear)) {
				addMonthToMap(month);
			}
		} else {
			monthMap.put(fromYear, "Range or years are invalid");
			monthMap.put(toYear, "Range or years are invalid");
		}
		return monthMap;
	}

	private void addMonthToMap(Month month) {
		List<Month> monthList = (List<Month>) monthMap.get(month.numberOfDays);
		if (monthList != null)
			monthList.add(month);
		else {
			monthList = new ArrayList<Month>();
			monthList.add(month);
			monthMap.put(month.numberOfDays, monthList);
		}
	}

	public String getFormattedText() {
		// print monthMap here in the prettiest form.

		StringBuilder builder = new StringBuilder();

		Iterator iterator = monthMap.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<Integer, Object> entry = (Entry<Integer, Object>) iterator.next();
			builder.append(entry.getKey());
			builder.append(" Day:");
			builder.append(entry.getValue());
			builder.append("\n");
		}
		return builder.toString();
	}

	public static void main(String[] args) {

		// System.out.println("MonthMap for Year 2000 to 2002");
		// System.out.println(new MonthMapGenerator2().createMonthMapRange(2002,2005));
		// System.out.println("MonthMap for Year 2000 to 2002"); System.out.println(new
		// MonthMapGenerator2().createMonthMap(2002,2005));
		// System.out.println("Test Error : "); //System.out.println(new
		// MonthGenerator2().createMonthMap(-2000,0000));

		MonthMapGenerator2 monthMapGenerator = new MonthMapGenerator2();
		monthMapGenerator.createMonthMapRange(2000, 2001);
		System.out.println(monthMapGenerator.getFormattedText());
	}

}
