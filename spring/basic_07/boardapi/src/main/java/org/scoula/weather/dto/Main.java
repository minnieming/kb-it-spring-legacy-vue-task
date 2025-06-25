package org.scoula.weather.dto;

import lombok.Data;

@Data
public class Main{
	private Object temp;
	private Object tempMin;
	private int humidity;
	private int pressure;
	private Object feelsLike;
	private Object tempMax;
}