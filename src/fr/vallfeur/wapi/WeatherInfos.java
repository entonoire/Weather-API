package fr.vallfeur.wapi;

public enum WeatherInfos {

	/*
	 * unit
	 */
	Metric("metric"),
	Imperial("imperial"),
	Kelvin("kelvin"),
	/*
	 * parameter for coord
	 */
	Latitude("lat"),
	Longitude("lon"),
	/*
	 * languages
	 */
	Afrikaans("af"),
	Albanian("al"),
	Arabic("ar"),
	Azerbaijani("az"),
	Bulgarian("bg"),
	Catalan("ca"),
	Czech("cz"),
	Danish("da"),
	German("de"),
	Greek("el"),
	English("en"),
	Basque("eu"),
	Persian("fa"),
	Finnish("fi"),
	French("fr"),
	Galician("gl"),
	Hebrew("he"),
	Hindi("hi"),
	Croatian("hr"),
	Hungarian("hu"),
	Indonesian("id"),
	Italian("it"),
	Japanese("ja"),
	Korean("kr"),
	Latvian("la"),
	Lithuanian("lt"),
	Macedonian("mk"),
	Norwegian("no"),
	Dutch("nl"),
	Polish("pl"),
	Portuguese("pt"),
	Portugues_Brasil("pt_br"),
	Romanian("ro"),
	Russian("ru"),
	Swedish("se"),
	Slovak("sk"),
	Slovenian("sl"),
	Spanish("sp"),
	Serbian("sr"),
	Thai("th"),
	Turkish("tr"),
	Ukrainian("ua"),
	Vietnamese("vi"),
	Chinese_Simplified("zh_cn"),
	Chinese_Traditional("zh_tw"),
	Zulu("zu");

	private String string;

	WeatherInfos(String str) {
		this.string = str;
	}

	public String getString() {
		return string;
	}
	
}
