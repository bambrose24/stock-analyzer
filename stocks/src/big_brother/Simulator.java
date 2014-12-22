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

public class Simulator {

	private ArrayList<DataSet> dataSet;
	double downPercent;
	double upPercent;

	public Simulator(ArrayList<DataSet> ds, double downPercent, double upPercent) {
		this.dataSet = ds;
		this.downPercent = downPercent;
		this.upPercent = upPercent;
	}

	public void simulate() throws IOException {

		System.out.println("\nstarting simulator...");
		long startTime = System.currentTimeMillis();

		Calendar today = Calendar.getInstance();
		today.get(Calendar.DAY_OF_MONTH);
		today.get(Calendar.MONTH);
		today.get(Calendar.YEAR);
		initialize();

		for (DataSet ds : dataSet) {

			String compDir = "data" + File.separator + "stock" + File.separator
					+ ds.getCompany().getAbbrev() + File.separator;
			new File(compDir + "start.txt");
			new File(compDir + "stop.txt");

			BufferedReader br = new BufferedReader(new FileReader(ds.getFile()));
			String expr = ",";
			final Pattern pattern = Pattern.compile(expr);

			br.readLine();
			String line = br.readLine();
			String prev = line;
			while (line != null) {
				prev = line;
				line = br.readLine();
			}
			line = prev;
			String[] ls = pattern.split(line);
			Double current = new Double(ls[4]);

			while ((line = br.readLine()) != null) {
				String[] lineSplit = pattern.split(line);
				Double close = new Double(lineSplit[4]);
				Double change = getChange(close, current);
				if (change < downPercent) {
					if (current < 3) {
						current = 0.0;
					}
				}
				if (change > upPercent) {
				}
			}
			br.close();

		}

		long endTime = System.currentTimeMillis();
		System.out.println("done simulator (time " + (endTime - startTime)
				+ ")");
	}

	private double initialize() throws IOException {
		// TODO Auto-generated method stub
		double total = 0;
		int numStock = 10;
		for (DataSet ds : dataSet) {
			BufferedReader br = new BufferedReader(new FileReader(ds.getFile()));
			BufferedOutputStream bo = new BufferedOutputStream(
					new FileOutputStream(ds.getStopFile()));
			br.readLine();
			ds.setStockNum(numStock);

			String expr = ",";
			final Pattern pattern = Pattern.compile(expr);
			String[] lineSplit = pattern.split(br.readLine());
			Double firstClose = new Double(lineSplit[4]);
			double invstmnt = firstClose * numStock;
			String toWrite = "\nInvestment as of " + lineSplit[0] + ": "
					+ invstmnt;
			bo.write(toWrite.getBytes());
			total += invstmnt;
			br.close();
			bo.close();
		}
		return total;
	}

	private Double getChange(Double test, Double current) {
		// TODO Auto-generated method stub
		if (test == current)
			return 0.0;
		else
			return ((test - current) / current) * 100;

	}
}
