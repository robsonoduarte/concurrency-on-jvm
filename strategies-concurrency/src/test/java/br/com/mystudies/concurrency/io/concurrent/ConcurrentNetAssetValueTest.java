package br.com.mystudies.concurrency.io.concurrent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import br.com.mystudies.concurrency.io.BaseNetAssetValueTest;

public class ConcurrentNetAssetValueTest extends BaseNetAssetValueTest{

	
	private ConcurrentNetAssetValue concurrentNetAssetValue;

	
	@Before
	public void setUp() throws Exception {
		concurrentNetAssetValue = new ConcurrentNetAssetValue();
	}
	
	
	@Test
	public void test() throws IOException {
		assertThat(concurrentNetAssetValue.compute(getStocks()), equalTo(2.0));
	}

}
