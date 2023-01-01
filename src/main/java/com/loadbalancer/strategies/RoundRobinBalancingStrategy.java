package com.loadbalancer.strategies;

import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinBalancingStrategy implements BalancingStrategy {

	private final AtomicInteger counter = new AtomicInteger();

	@Override
	public URL chooseWorker(List<URL> workers) {
		int index = counter.getAndUpdate(value -> (value + 1) % workers.size());
		return workers.get(index);
	}
}