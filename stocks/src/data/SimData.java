package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class SimData {

	DataSet ds;
	BufferedReader reader;
	int numStock;
	int day, month, year;

	public SimData(DataSet ds, BufferedReader br, int numStock, int day,
				   int month, int year) {
		this.ds = ds;
		this.numStock = numStock;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public void setup() throws IOException {
		File f = new File("data" + File.separator + "stock" + File.separator
				+ ds.getCompany().getAbbrev() + File.separator
				+ ds.getCompany().getAbbrev() + ".csv");
		reader = new BufferedReader(new FileReader(f));
		reader.readLine();
		String line = reader.readLine();
		this.parseLine(line);
	}

	private void parseLine(String line) {
		// TODO Auto-generated method stub
		final Pattern pattern = Pattern.compile(",");
		String[] nums = pattern.split(line);
		final Pattern datePattern = Pattern.compile("-");
		String[] date = datePattern.split(nums[0]);
		
		this.setYear(Integer.parseInt(date[0]));
		this.setMonth(Integer.parseInt(date[1]));
		this.setDay(Integer.parseInt(date[2]));
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

	public DataSet getDs() {
		return ds;
	}

	public void setDs(DataSet ds) {
		this.ds = ds;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public int getNumStock() {
		return numStock;
	}

	public void setNumStock(int numStock) {
		this.numStock = numStock;
	}

}
