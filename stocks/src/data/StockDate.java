package data;

public class StockDate {

	private int day, month, year;
	private String dayStr, monthStr, yearStr;
	
	public StockDate(int month, int day, int year){
		this.day = day;
		this.month = month;
		this.year = year;
		this.dayStr = Integer.toString(day);
		this.monthStr = Integer.toString(month);
		this.yearStr = Integer.toString(year);
	}
	
	public StockDate(String m, String d, String y){
		this.dayStr = d;
		this.monthStr = m;
		this.yearStr = y;
		this.day = Integer.parseInt(d);
		this.month = Integer.parseInt(m);
		this.year = Integer.parseInt(y);
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDayStr() {
		return dayStr;
	}

	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}

	public String getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}

	public String getYearStr() {
		return yearStr;
	}

	public void setYearStr(String yearStr) {
		this.yearStr = yearStr;
	}
}
