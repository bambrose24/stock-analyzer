package data;

import java.util.ArrayList;

public class Projection {

	private DataSet dataSet;
	private ArrayList<String> analys;
	
	public Projection(DataSet ds, ArrayList<String> a){
		this.dataSet = ds;
		this.analys = a;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public ArrayList<String> getAnalys() {
		return analys;
	}

	public void setAnalys(ArrayList<String> analys) {
		this.analys = analys;
	}	
}
