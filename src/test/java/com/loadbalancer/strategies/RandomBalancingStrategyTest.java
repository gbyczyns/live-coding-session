package com.loadbalancer.strategies;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;

class RandomBalancingStrategyTest {
	private final RandomBalancingStrategy randomBalancingStrategy = new RandomBalancingStrategy();

	@Test
	void shouldChooseRandomWorker() throws MalformedURLException {
		List<URL> urls = List.of(
				new URL("https://worker1:1234"),
				new URL("https://worker2:1234"),
				new URL("https://worker3:1234")
		);
		URL randomlySelectedUrl = randomBalancingStrategy.chooseWorker(urls);
		assertTrue(urls.contains(randomlySelectedUrl));
	}
}