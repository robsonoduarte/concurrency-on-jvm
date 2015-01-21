package br.com.mystudies.concurrency;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Stream;

public class Apps {

	private static long start;

	protected static void start() {
		out.println("Running the process...");
		start = currentTimeMillis();
	}

	protected static void finish() {
		out.print("The total time of execution was..: " +  ( currentTimeMillis() - start) / 1000  + " seconds");
	}



	protected static List<Integer> generateNumbers() {
		return Stream.iterate(1, n -> n += 1)
				.limit(10000000)
				.collect(toList());
	}


	protected static String formatCurrency(Double total) {
		return DecimalFormat.getCurrencyInstance().format(total);
	}
}