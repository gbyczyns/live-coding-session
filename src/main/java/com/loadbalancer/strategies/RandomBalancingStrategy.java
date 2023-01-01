package com.loadbalancer.strategies;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandomBalancingStrategy implements BalancingStrategy {

	private final Supplier<Random> randomSupplier;

	public RandomBalancingStrategy() {
		this(() -> ThreadLocalRandom.current());
	}

	public RandomBalancingStrategy(Random random) {
		this(() -> random);
	}

	public RandomBalancingStrategy(Supplier<Random> randomSupplier) {
		this.randomSupplier = randomSupplier;
	}

	@Override
	public URL chooseWorker(List<URL> workers) {
		int index = randomSupplier.get().nextInt(workers.size());
		return workers.get(index);
	}
}