package utils;

import java.util.regex.Pattern;

import data.StockDate;

public class Splitter {

	// SAMPLE
	// Date,Open,High,Low,Close,Volume,Adj Close
	// 2014-12-16,51.01,51.15,47.68,47.96,22053700,47.96

	Pattern line;
	Pattern date;

	public Splitter() {
		line = Pattern.compile(",");
		date = Pattern.compile("/");
	}

	public StockDate getDate(String str) {
		String[] splitLine = line.split(str);
		String[] splitDate = date.split(splitLine[0]);
		return new StockDate(splitDate[0], splitDate[1], splitDate[2]);
	}

	public Double getClose(String str) {
		String[] splitLine = line.split(str);
		return new Double(splitLine[4]);
	}

}
