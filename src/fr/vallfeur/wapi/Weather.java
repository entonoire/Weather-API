package fr.vallfeur.wapi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather {

		private String id, city;
		private WeatherInfos unit;
		private HttpURLConnection connection;
		private Reader reader;

		/**
		 * @param id your appid that you get on openweathermap (quite useful if you want to connect by the way)
		 * @param city from where you want the Weather
		 * @param unit Metric : speed is in km/h, temp is in Celcius etc ... ; Imperial : speed is in mph, temp in Fahrenheit ; Kelvin : speed is in km/h but the temp change
		 * @param lang (! optional ! default: enlish) language of the weather description (it only change in precise weather type) see language : https://openweathermap.org/current#multi
		 * @throws MalformedURLException if the URL is wrong you can catch it and do what ever you want
		 * @throws IOException here you can catch it and do what ever you want to
		 */
		public Weather(String id, String city, WeatherInfos unit) throws MalformedURLException, IOException{
			this.city = city;
			this.id = id;
			this.unit = unit;
			this.connection = (HttpURLConnection) new URL(
					String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=%s&appid=%s",
							city,
							unit.getString(),
							id)
					).openConnection();
			
			connection.addRequestProperty("User-Agent", "Mozilla/5.0");
	
			reader = new Reader(connection);
		}
		
		/**
		 * same method but language can be set
		 * @param lang (! optional ! default: enlish) language of the weather description (it only change in precise weather type) see language : https://openweathermap.org/current#multi
		 */
		public Weather(String id, String city, WeatherInfos unit, WeatherInfos lang) throws MalformedURLException, IOException{
			this.city = city;
			this.id = id;
			this.unit = unit;
			this.connection = (HttpURLConnection) new URL(
					String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=%s&lang=%s&appid=%s",
							city,
							unit.getString(),
							lang.getString(),
							id)
					).openConnection();
			
			connection.addRequestProperty("User-Agent", "Mozilla/5.0");
	
			reader = new Reader(connection);
		}
		
		/**
		 * @return name of the city as a String where you get data
		 */
		public String getCity() {
			return city;
		}
	
		/**
		 * @return the connection to the api as a HttpURLConnection
		 */
		public HttpURLConnection getConnection() {
			return connection;
		}
	
		/**
		 * @return your app id as a String
		 */
		public String getId() {
			return id;
		}
	
		/**
		 * @return the unit that you choose as an enum from WeatherInfos enumeration (you can do .getString() to have it as a String if you want)
		 */
		public WeatherInfos getUnit() {
			return unit;
		}
	
		/**
		 * It will stop the connection from the api (nice if you want to reload with an another url)
		 */
		public void close(){
			getConnection().disconnect();
		}
	
		/**
		 * @param coordType Latitude or longitude
		 * @return latitude or longitude of the city as a double
		 */
		public Double getCoord(WeatherInfos coordType){
			return Double.parseDouble(reader.getData(coordType.getString()).replace("}", ""));
		}
		
		/**
		 * @param precise true : it will return a "precise" description of the weather else it will just say "Rain" "Sun" etc ...
		 * @return the weather as a string
		 */
		public String getType(boolean precise){
			String data;
			
			if(precise){
				data = reader.getData("description");
			}else{
				data = reader.getData("main");
			}
			
			return data;
		}

		/**
		 * @return weather icon url as a String (all icon and weather code https://openweathermap.org/weather-conditions)
		 */
		public String getIconUrl(){
			return String.format("https://openweathermap.org/img/wn/%s@2x.png", reader.getData("icon").replace("}]", "").replace("\"", ""));
		}
		
		/**
		 * @return temperature as a double
		 */
		public double getTemp(){
			return Double.parseDouble(reader.getData("temp"));
		}
		
		/**
		 * @return how the temperature feel like when you"r outside as a double
		 */
		public double getFeelTemp(){
			return Double.parseDouble(reader.getData("feels_like"));
		}
		
		/**
		 * @return minimum temperature reached during the day
		 */
		public double getMinTemp(){
			return Double.parseDouble(reader.getData("temp_min"));
		}
		
		/**
		 * @return maximum temperature reached during the day
		 */
		public double getMaxTemp(){
			return Double.parseDouble(reader.getData("temp_max"));
		}
		
		/**
		 * @return atmospheric pressure as an integer (hPa)
		 */
		public int getPressure(){
			return Integer.parseInt(reader.getData("pressure"));
		}
		
		/**
		 * @return humidity as an integer in %
		 */
		public int getHumidity(){
			return Integer.parseInt(reader.getData("humidity").replace("}", ""));
		}
		
		/**
		 * @return visibility as an integer in meter (don"t looks like it change with imperial)
		 */
		public int getVisibility(){
			return Integer.parseInt(reader.getData("visibility"));
		}
		
		/**
		 * @return speed of the wind as a double in km/h for metric and mph for imperial
		 */
		public double getWindSpeed(){
			return Double.parseDouble(reader.getData("speed"));
		}
		
		/**
		 * @return wind direction as an integer in degrees
		 */
		public int getWindDirection(){
			return Integer.parseInt(reader.getData("deg").replace("}", ""));
		}
		
		/**
		 * good code thanks https://stackoverflow.com/questions/48750528/get-direction-from-degrees
		 * @return wind heading as a String
		 */
		public String getWindHeading(){
			int angle = getWindDirection();
		    String[] directions = {"North","North-East","East","South-East","South","South-West","West","North-West"};
		    
		    int index = Math.round(((angle %= 360) < 0 ? angle + 360 : angle) / 45) % 8;
		    return directions[index];
		}
}
