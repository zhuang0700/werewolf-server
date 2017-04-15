package com.telan.weixincenter.plugins;

import java.io.Serializable;
import java.util.List;

/**
 * @author :张超
 * @date :2015年7月16日 上午9:59:03
 * @Description: 具体数据
 */
public class Weather implements Serializable{   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String currentCity;
	private List<WeatherDate> weather_data;
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public List<WeatherDate> getWeather_data() {
		return weather_data;
	}
	public void setWeather_data(List<WeatherDate> weather_data) {
		this.weather_data = weather_data;
	}
}
