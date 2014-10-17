package br.com.mystudies.concurrency.computation.sequencial;

import static org.apache.commons.math3.primes.Primes.isPrime;

import java.util.stream.Stream;

import br.com.mystudies.concurrency.computation.PrimeFinder;

public class SequencialPrimeFinder implements PrimeFinder {

	@Override
	public long countsPrimes(long finalNumber) {

		return Stream.iterate(1, n -> n + 1)
			/*.parallel()*/
			.limit(finalNumber)
			.filter(n -> isPrime(n) )
			.count();
	}

}
