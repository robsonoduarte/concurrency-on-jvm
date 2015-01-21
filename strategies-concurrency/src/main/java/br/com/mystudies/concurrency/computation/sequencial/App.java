package br.com.mystudies.concurrency.computation.sequencial;

import static java.lang.System.out;

import java.io.IOException;
import java.util.List;

import br.com.mystudies.concurrency.Apps;
import br.com.mystudies.concurrency.computation.PrimeFinder;

public class App extends Apps {



	public static void main(String[] args) throws IOException {

		List<Integer> numbers = generateNumbers();

		start();

		PrimeFinder primeFinder = new SequencialPrimeFinder();

		out.println("The total of primes is..: " + primeFinder.countsPrimes(numbers));

		finish();

	}

}
