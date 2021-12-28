package fr.vallfeur.wapi;

public enum WeatherInfos {

	Metric("metric"),
	Imperial("imperial"),
	Kelvin("kelvin"),
	Latitude("lat"),
	Longitude("lon");

	private String string;

	WeatherInfos(String str) {
		this.string = str;
	}

	public String getString() {
		return string;
	}
	
}
