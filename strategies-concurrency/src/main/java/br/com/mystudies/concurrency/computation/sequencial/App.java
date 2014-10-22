package br.com.mystudies.concurrency.computation.sequencial;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import br.com.mystudies.concurrency.computation.PrimeFinder;

public class App {

	public static void main(String[] args) throws IOException {


		List<Integer> numbers = Stream.iterate(1, n -> n += 1)
				.limit(10000000)
				.collect(toList());

		System.out.println("init");

		long start = currentTimeMillis();

		PrimeFinder primeFinder = new SequencialPrimeFinder();
		System.out.println(primeFinder.countsPrimes(numbers));

		printTotalTime(start,currentTimeMillis());
	}


	private static void printTotalTime(long start, long finish) {
		out.print("Total Time..: " +  ( finish - start) / 1000  + " seconds");
	}

}
