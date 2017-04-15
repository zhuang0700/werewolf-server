package com.telan.weixincenter.plugins;

import java.io.Serializable;
import java.util.List;

public class WeatherResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String error;
	private String status;
	private String date;
	private List<Weather> results;   
	
	
	
	public List<Weather> getResults() {
		return results;
	}
	public void setResults(List<Weather> results) {
		this.results = results;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
