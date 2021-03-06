package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;

import data.Company;

public class CompaniesReader {

	String fileString;

	public CompaniesReader(String name) {
		fileString = name;
	}

	public ArrayList<Company> getCompanies() throws Exception {

		ArrayList<Company> comps = new ArrayList<>();
		
		Calendar today = Calendar.getInstance();
		int day = today.get(Calendar.DAY_OF_MONTH) - 1;
		int month = today.get(Calendar.MONTH) + 1;
		int year = today.get(Calendar.YEAR);

		BufferedReader reader = new BufferedReader(new FileReader(new File(
				fileString)));
		String line = reader.readLine();
		while (line != null) {

			String abbrev = "";
			for (int i = 0; i < line.length(); i++) {
				char curr = line.charAt(i);
				if (curr == '|') {
					line = line.substring(i + 1);
					break;
				}
				abbrev += curr;
			}

			String company = "";
			for (int i = 0; i < line.length(); i++) {
				char curr = line.charAt(i);
				if (curr == '|')
					break;
				company += curr;
			}
			
			

			String urlStr = "http://ichart.finance.yahoo.com/table.csv?s="
					+ abbrev + "&d=" + (month-1) + "&e=" + day + "&f=" + year
					+ "&g=d&a=" + (month-1) + "&b=" + day + "&c=" + (year-2)
					+ "&ignore=.csv";
			
//			FOR GETTING OUTDATED DATA FOR TESTING THE UPDATER
//			String urlStr = "http://ichart.finance.yahoo.com/table.csv?s="
//					+ abbrev + "&d=" + (month-1) + "&e=" + 5 + "&f=" + year
//					+ "&g=d&a=" + (month-1) + "&b=" + 5 + "&c=" + (year-5)
//					+ "&ignore=.csv";
			comps.add(new Company(company, abbrev, urlStr));
			line = reader.readLine();
		}

		reader.close();
		return comps;
	}
}
