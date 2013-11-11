package br.com.mystudies.concurrency.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class StockFileReader implements StockReader{

	@Override
	public Map<String, Integer>reader(){
		
		List<String> lines = readerFileLines();
		
		Map<String, Integer> stocks = new HashMap<>(); 
		
		for (String stock : lines) {
			
			String[] values = stock.split(",");
			
			stocks.put(values[0],Integer.valueOf(values[1]));			
		}
		
		return stocks;
	}

	
	
	
	private List<String> readerFileLines() {
		try {
			return IOUtils.readLines(this.getClass().getResourceAsStream("/stocks.txt"));
		} catch (IOException exception) {
			return new ArrayList<>(); // <<<-- only to don't progragation the exception.
		}
	}

}
