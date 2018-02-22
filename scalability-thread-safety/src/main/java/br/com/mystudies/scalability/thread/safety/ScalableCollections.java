package br.com.mystudies.scalability.thread.safety;

import java.util.Map;

public class ScalableCollections {

	
	
	
	
	
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
