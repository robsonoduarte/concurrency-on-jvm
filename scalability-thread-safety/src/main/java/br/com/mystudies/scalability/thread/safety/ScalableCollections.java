package br.com.mystudies.scalability.thread.safety;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ScalableCollections {

	
	public static void main(String[] args) {
		System.out.println("Using Plain vanilla Hashmap");
		AcessingMap.useMap(new HashMap<>());
		
		System.out.println("Using Synchronized HasMap");
		AcessingMap.useMap(Collections.synchronizedMap(new HashMap<>()));
		
		
		System.out.println("Using Concurrent HasMap");
		AcessingMap.useMap(new ConcurrentHashMap<>());
			
	}
	
	private static class AcessingMap{
		
		static void useMap(final Map<String, Integer> scores) {
			scores.put("Fred", 10);
			scores.put("Sara", 12);
			
			try {
				for(final String key: scores.keySet()) {
					System.out.println(key + "score " + scores.get(key));
					scores.put("Joe", 10);
				}
			} catch (Exception ex) {
				System.out.println("Falied: " + ex);
			}
			
			System.out.println("Number of elements in the map: " + scores.keySet().size());
		}				
	}
}
