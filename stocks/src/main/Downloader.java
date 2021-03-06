package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import data.Company;

public class Downloader {
	private List<Company> stocks;
	String dayStr, monthStr, yearStr;
	int day, month, year;
	Calendar today;
	Pattern datePattern;
	Pattern commaPattern;

	public Downloader(List<Company> stocks) {
		this.stocks = stocks;
		Calendar today = Calendar.getInstance();
		String dayStr = Integer.toString(today.get(Calendar.DAY_OF_MONTH) - 1);
		String monthStr = Integer.toString((today.get(Calendar.MONTH) + 1));
		String yearStr = Integer.toString(today.get(Calendar.YEAR));
		day = Integer.parseInt(dayStr);
		month = Integer.parseInt(monthStr);
		year = Integer.parseInt(yearStr);

		datePattern = Pattern.compile("-");
		commaPattern = Pattern.compile(",");
	}

	public ArrayList<File> download() throws IOException, InterruptedException {
		File dir = new File("data");
		dir.mkdir();
		ArrayList<File> files = new ArrayList<>();
		System.out.println("starting downloading...");
		long start = System.currentTimeMillis();
		for (Company c : stocks) {

			

			File file = new File("data" + File.separator + "stock"
					+ File.separator + c.getAbbrev() + File.separator
					+ c.getAbbrev() + ".csv");
			if (!file.isFile()) {
				
				File subDir1 = new File("data" + File.separator + "stock");
				subDir1.mkdir();
				File subDir2 = new File("data" + File.separator + "stock"
						+ File.separator + c.getAbbrev());
				subDir2.mkdir();
				
				System.out.println("new:      " + file.toString());
				ReadableByteChannel rbc = Channels.newChannel(c.getUrl()
						.openStream());
				FileOutputStream fos = new FileOutputStream(file);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				rbc.close();
			} else {
				// System.out.println("updating: " + file.toString());
				this.update(file, c);
			}

			files.add(file);
		}
		long end = System.currentTimeMillis();
		System.out.println("done downloading (time " + (end - start) + ")");
		return files;
	}

	private void update(File file, Company c) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file)));
		br.readLine();
		String line = br.readLine();
		br.close();

		String[] nums = commaPattern.split(line);
		String[] date = datePattern.split(nums[0]);
		if (!date[0].equals(year) || !date[1].equals(month)
				|| !date[2].equals(day)) {
			this.downloadRest(date, file, c);
		}
	}

	private void downloadRest(String[] date, File file, Company c) {
		// date[0] == year, date[1] == month, date[2] == day

		// NEEDS TO BE FIXED. IF STATEMENT AND GETTING INTERMEDIATE DATA SECURELY
		// java.time.*
		
		/*Calendar mostRecentDay = Calendar.getInstance();
		mostRecentDay.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
				Integer.parseInt(date[2]));

		if (mostRecentDay.getTime()
				.before(new Date(System.currentTimeMillis()))) {

			Integer urlMonth = Integer.parseInt(date[1]) - 1;

			String urlStr = "http://ichart.finance.yahoo.com/table.csv?s="
					+ c.getAbbrev() + "&d=" + (month - 1) + "&e=" + day + "&f="
					+ year + "&g=d&a=" + urlMonth + "&b=" + date[2] + "&c="
					+ date[0] + "&ignore=.csv";
			try {
				URL url = new URL(urlStr);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));
				File f = new File("data" + File.separator + "stock"
						+ File.separator + c.getAbbrev() + File.separator
						+ c.getAbbrev() + "_toUpdate");
				
				RandomAccessFile raf = new RandomAccessFile(f, "rw");
				raf.readLine();

				String line;
				while ((line = in.readLine()) != null) {
					raf.write(line.getBytes());
				}

				raf.close();
				in.close();
				

			} catch (MalformedURLException e) {
				System.out.println("URL did not have the correct format..");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}
