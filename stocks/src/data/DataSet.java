package data;

import java.io.File;

public class DataSet {
	private Company company;
	private File dataFile;
	private File startFile;
	private File stopFile;
	private int stockNum;

	public DataSet(File f, Company c) {
		this.dataFile = f;
		this.company = c;
		this.startFile = new File("data" + File.separator + "stock"
				+ File.separator + c.getAbbrev() + File.separator + "start.txt");
		this.stopFile = new File("data" + File.separator + "stock"
				+ File.separator + c.getAbbrev() + File.separator + "stop.txt");
		this.stockNum = 0;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public File getFile() {
		return dataFile;
	}

	public void setFile(File file) {
		this.dataFile = file;
	}

	public File getStartFile() {
		return startFile;
	}

	public void setStartFile(File startFile) {
		this.startFile = startFile;
	}

	public File getStopFile() {
		return stopFile;
	}

	public void setStopFile(File stopFile) {
		this.stopFile = stopFile;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}
}
