package br.com.mystudies.concurrency.computation.sequencial;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.mystudies.concurrency.computation.BasePrimeFinderTest;

public class SequencialPrimeFinderTest extends BasePrimeFinderTest {



	@Before
	public void setUp() throws Exception {
		primeFinder = new SequencialPrimeFinder();
	}




	@Test
	public void test() {
		long totalPrimes = primeFinder.countsPrimes(numbers);
		assertThat(totalPrimes, equalTo(664579L));
	}

}
