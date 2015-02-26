package big_brother;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import data.DataSet;
import data.Projection;

public class ImmatureAnalyzer implements Analyzer {

	public ArrayList<DataSet> dataSets;

	public ImmatureAnalyzer(ArrayList<DataSet> dataSets) {
		this.dataSets = dataSets;
	}

	public void getAnalyses() throws IOException {

		System.out.println("\nstarting analysis...");
		long analysisStart = System.currentTimeMillis();
		for (DataSet ds : dataSets) {
			File outFile = new File("data" + File.separator + "stock"
					+ File.separator + ds.getCompany().getAbbrev()
					+ File.separator + ds.getCompany().getAbbrev()
					+ "analysis.txt");

			if (!outFile.exists()) {
				BufferedOutputStream bo = new BufferedOutputStream(
						new FileOutputStream(outFile));

				String intro = ds.getCompany().getAbbrev()
						+ "'s Mediocre Analysis (by Bob Ambrose)\n";
				bo.write(intro.getBytes());

				File inFile = ds.getFile();
				BufferedReader br = new BufferedReader(new FileReader(inFile));
				br.readLine();
				String line = br.readLine();

				String expr = ",";
				final Pattern linePat = Pattern.compile(expr);
				
				String[] lineSplit = linePat.split(line);
				
				
				
				String toWrite = "\nOpen on " + month + "/" + day + "/"
						+ (year - 5) + ": " + lineSplit[4];
				bo.write(toWrite.getBytes());
				
				bo.close();
				br.close();
			}
		}
		long analysisEnd = System.currentTimeMillis();
		System.out.println("done with analysis (time "
				+ (analysisEnd - analysisStart) + ")");
	}
}
