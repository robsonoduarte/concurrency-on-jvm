package br.com.mystudies.concurrency.computation.concurrent;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.mystudies.concurrency.computation.PrimeFinder;

public class ConcurrentPrimeFinderTest {


	private PrimeFinder primeFinder;







	@Before
	public void setUp() throws Exception {
		primeFinder = new ConcurrentPrimeFinder();
	}





	@Test
	public void test() {
		long totalPrimes = primeFinder.countsPrimes(10000000L);
		assertThat(totalPrimes, equalTo(664579L));
	}


}
