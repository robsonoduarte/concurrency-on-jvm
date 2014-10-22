package br.com.mystudies.concurrency.computation.concurrent;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.mystudies.concurrency.computation.BasePrimeFinderTest;

public class ConcurrentPrimeFinderTest extends BasePrimeFinderTest{





	@Before
	public void setUp() throws Exception {
		primeFinder = new ConcurrentPrimeFinder();
	}




	@Test
	public void test() {
		long totalPrimes = primeFinder.countsPrimes(numbers);
		assertThat(totalPrimes, equalTo(664579L));
	}



}
