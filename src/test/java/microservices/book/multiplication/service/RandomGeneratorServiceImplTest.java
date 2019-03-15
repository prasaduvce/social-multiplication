package microservices.book.multiplication.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class RandomGeneratorServiceImplTest {

	private RandomGeneratorServiceImpl randomGeneratorService;

	@Before
	public void setUp() {
		randomGeneratorService = new RandomGeneratorServiceImpl();
	}

	@Test
	public void generateRandomNumbers() {
		List<Integer> integers = IntStream.range(0, 1000)
				.map(i -> randomGeneratorService.generateRandomFactor())
				.boxed()
				.collect(Collectors.toList());

		Assertions.assertThat(integers).containsOnlyElementsOf(IntStream.range(11, 100).boxed().collect(Collectors.toList()));
	}
}
