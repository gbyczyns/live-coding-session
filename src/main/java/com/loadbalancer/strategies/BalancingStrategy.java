package com.loadbalancer.strategies;

import java.net.URL;
import java.util.List;

@FunctionalInterface
public interface BalancingStrategy {
	URL chooseWorker(List<URL> workers);
}