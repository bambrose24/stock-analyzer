package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import data.Company;
import data.DataSet;
import data.Projection;
import big_brother.ImmatureAnalyzer;

public class Main {

	public static void main(String[] args) {

		try {
			long start = System.currentTimeMillis();
			
			CompaniesReader cr = new CompaniesReader("nasdaqlisted.txt");
			ArrayList<Company> companiesList = cr.getCompanies();
			Downloader d = new Downloader(cr.getCompanies());
			ArrayList<File> files = d.download();
			ArrayList<DataSet> dataList = new ArrayList<>();

			for (int i = 0; i < files.size(); i++) {
				DataSet ds = new DataSet(files.get(i), companiesList.get(i));
				dataList.add(ds);
			}

			ImmatureAnalyzer an = new ImmatureAnalyzer(dataList);
			an.getAnalyses();
			
//			Simulator s = new Simulator(dataList, -5.0, 10.0);
//			s.simulate();

			long end = System.currentTimeMillis();

			System.out.println("\nTIME: " + (end - start));
		} catch (IOException e) {
			System.out.println("You messed up the file management system");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
