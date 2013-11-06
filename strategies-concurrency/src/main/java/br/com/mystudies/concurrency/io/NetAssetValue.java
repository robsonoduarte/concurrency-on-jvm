package br.com.mystudies.concurrency.io;

import java.io.IOException;
import java.util.Map;

public interface NetAssetValue {

	 Double compute(Map<String, Integer> stocks) throws IOException;

}