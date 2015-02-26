package big_brother;

import java.io.IOException;
import java.util.Calendar;

public interface Analyzer {
	final Calendar today = Calendar.getInstance();
	final int day = today.get(Calendar.DAY_OF_MONTH) - 1;
	final int month = today.get(Calendar.MONTH) + 1;
	final int year = today.get(Calendar.YEAR);
	
	public void getAnalyses() throws IOException;
}
