package com.loadbalancer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.net.MalformedURLException;
import java.net.URL;

import com.loadbalancer.strategies.BalancingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoadBalancerTest {

	@Mock
	private BalancingStrategy balancingStrategy;

	private static final int MAXIMUM_NUMBER_OF_WORKERS = 3;
	private LoadBalancer loadBalancer;

	@BeforeEach
	void beforeEach() {
		loadBalancer = new LoadBalancer(balancingStrategy, MAXIMUM_NUMBER_OF_WORKERS);
	}

	@Test
	void shouldProperlyRegisterWorkers() throws MalformedURLException {
		loadBalancer.register(new URL("https://worker1:1234"));
		loadBalancer.register(new URL("https://worker2:1234"));
		loadBalancer.register(new URL("https://worker3:1234"));
		assertEquals(3, loadBalancer.getWorkers().size());
	}

	@Test
	void shouldFailIfWorkerAlreadyAdded() throws MalformedURLException {
		loadBalancer.register(new URL("https://host"));
		assertThrows(IllegalArgumentException.class, () -> loadBalancer.register(new URL("https://host")));
	}

	@Test
	void shouldFailWhenTooManyWorkersAdded() throws MalformedURLException {
		for (int i = 0; i < MAXIMUM_NUMBER_OF_WORKERS; i++) {
			loadBalancer.register(new URL("https://host-" + i));
		}
		assertThrows(IllegalArgumentException.class, () -> loadBalancer.register(new URL("https://host")));
	}

	@Test
	void shouldFailWhenNoWorkersRegistered() {
		assertThrows(IllegalStateException.class, () -> loadBalancer.get());
		verify(balancingStrategy, never()).chooseWorker(any());
	}

	@Test
	void shouldUseBalancingStrategy() throws MalformedURLException {
		loadBalancer.register(new URL("https://worker1:1234"));
		loadBalancer.get();
		verify(balancingStrategy).chooseWorker(loadBalancer.getWorkers());
	}
}