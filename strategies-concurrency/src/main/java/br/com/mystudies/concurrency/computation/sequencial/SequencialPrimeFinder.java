package br.com.mystudies.concurrency.computation.sequencial;

import static org.apache.commons.math3.primes.Primes.isPrime;
import br.com.mystudies.concurrency.computation.PrimeFinder;

public class SequencialPrimeFinder implements PrimeFinder {

	@Override
	public long countsPrimes(long finalNumber) {

		long count = 0;

		// TODO: change to stream..
		for (int i = 1; i <= finalNumber; i++) {
			if(isPrime(i)){
				count++;
			}
		}

		return count;
	}

}
