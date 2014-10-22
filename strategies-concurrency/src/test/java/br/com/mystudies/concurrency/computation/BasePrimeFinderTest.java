package br.com.mystudies.concurrency.computation;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import org.junit.BeforeClass;

import br.com.mystudies.concurrency.computation.PrimeFinder;

public class BasePrimeFinderTest {

	protected static List<Integer> numbers;
	protected PrimeFinder primeFinder;



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		numbers = Stream.iterate(1, n -> n += 1)
					.limit(10000000)
					.collect(toList());
	}


}