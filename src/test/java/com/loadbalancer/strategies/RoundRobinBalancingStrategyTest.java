package com.loadbalancer.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;

class RoundRobinBalancingStrategyTest {
	private final RoundRobinBalancingStrategy roundRobinBalancingStrategy = new RoundRobinBalancingStrategy();

	@Test
	void shouldChooseWorkersInRoundRobinFashion() throws MalformedURLException {
		List<URL> urls = List.of(
				new URL("https://worker1:1234"),
				new URL("https://worker2:1234"),
				new URL("https://worker3:1234")
		);
		assertEquals(urls.get(0), roundRobinBalancingStrategy.chooseWorker(urls));
		assertEquals(urls.get(1), roundRobinBalancingStrategy.chooseWorker(urls));
		assertEquals(urls.get(2), roundRobinBalancingStrategy.chooseWorker(urls));
		assertEquals(urls.get(0), roundRobinBalancingStrategy.chooseWorker(urls));
		assertEquals(urls.get(1), roundRobinBalancingStrategy.chooseWorker(urls));
		assertEquals(urls.get(2), roundRobinBalancingStrategy.chooseWorker(urls));
	}
}