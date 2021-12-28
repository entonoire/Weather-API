package fr.vallfeur.wapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Arrays;

public class Reader {
/*
*	/!\ ugly part of the api /!\
*/	
	private StringBuilder infos;
	private String data;
	
	/*
	 * internal method
	 */
	public Reader(HttpURLConnection connection){
		try{
			System.err.println("[Weather-Api] Connecting to reader ...");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			infos = new StringBuilder();
			String line;
			
			while ((line = in.readLine()) != null) {
				infos.append("\n" + line);
			}
			in.close();

			System.err.println("[Weather-Api] Connected to reader!");
			System.err.println("[Weather-Api] Ready to read infos!");
			
		}catch(IOException ex){
			System.err.println("[Weather-Api] Error: URL looks incorect! (city or appid can be incorrect)");
			System.err.println("[Weather-Api] Stopping the program ...");
			System.exit(0);
		}
	}
	
	
	/**
	 * @param type
	 * @return data precised in the param as a string
	 * 
	 * exemple : type : "\"temp\":" → it will return the temp (as a string)
	 */
	public String getData(String type){
		
		if(type == "main"){
			data = infos.toString().split(",")[3].split(":")[1].replace("\"", "");
		}else{
			Arrays.stream(infos.toString().split(String.format("\"%s\":", type))).skip(1).map(l -> l.split(",")[0]).forEach(l -> {
				data = l.replace("}", "");
			});
		}
		return data;
	}
	
}
