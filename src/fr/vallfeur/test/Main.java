package fr.vallfeur.test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import fr.vallfeur.wapi.Weather;
import fr.vallfeur.wapi.WeatherInfos;

public class Main {

	public static void main(String[] str) throws IOException{
		Scanner scanner = new Scanner(new File("F:\\DEVELOPEMENT\\JAVA\\Eclipse\\DiscordRCP\\api.txt"));
		String[] data = scanner.nextLine().split(",");
		scanner.close();
		
		Weather api = new Weather(data[0], data[1], WeatherInfos.Metric, WeatherInfos.French);
		System.out.println(api.getSunset());
		
	}
	
}
