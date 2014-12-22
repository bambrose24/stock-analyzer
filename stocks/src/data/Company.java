package data;

import java.net.MalformedURLException;
import java.net.URL;

public class Company {

	private String name;
	private String abbrev;
	private URL url;
	
	public Company(String name, String abbrev, String url){
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("BAD URL ~~");
			e.printStackTrace();
		}
		this.name = name;
		this.abbrev = abbrev;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	
}
