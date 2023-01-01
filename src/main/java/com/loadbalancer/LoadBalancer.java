package com.loadbalancer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.loadbalancer.strategies.BalancingStrategy;

public class LoadBalancer {
	private final Object lock = new Object();
	private final List<URL> workers = new ArrayList<>();
	private final List<URL> workersUnmodifiableWrapper = Collections.unmodifiableList(workers);

	private final BalancingStrategy balancingStrategy;
	private final int maximumNumberOfWorkers;

	public LoadBalancer(BalancingStrategy balancingStrategy, int maximumNumberOfWorkers) {
		this.balancingStrategy = balancingStrategy;
		this.maximumNumberOfWorkers = maximumNumberOfWorkers;
	}

	public void register(URL worker) {
		synchronized (lock) {
			if (workers.size() == maximumNumberOfWorkers) {
				throw new IllegalArgumentException("The maximum number of workers allowed has been exceeded.");
			}
			if (workers.contains(worker)) {
				throw new IllegalArgumentException(String.format("The worker %s already exists.", worker));
			}
			workers.add(worker);
		}
	}

	public URL get() {
		synchronized (lock) {
			if (workers.isEmpty()) {
				throw new IllegalStateException("No workers have been registered.");
			}
			return balancingStrategy.chooseWorker(workersUnmodifiableWrapper);
		}
	}

	public List<URL> getWorkers() {
		return workersUnmodifiableWrapper;
	}
}