package microservices.book.multiplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MultiplicationServiceImplTest {

	@Mock
	private RandomGeneratorService randomGeneratorService;

	private MultiplicationServiceImpl multiplicationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		multiplicationService = new MultiplicationServiceImpl(randomGeneratorService);
	}

	@Test
	public void randomMultiplicationTest() {
		//given
		BDDMockito.given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

		//when
		Multiplication multiplication = multiplicationService.createRandomMultiplication();

		//assert
		assertThat(multiplication.getFactorA()).isEqualTo(50);
		assertThat(multiplication.getFactorB()).isEqualTo(30);
		assertThat(multiplication.multiply()).isEqualTo(1500);
	}

	@Test
	public void checkCorrectAttemptTestSuccess() {
		//given
		BDDMockito.given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

		User user = new User("Test User");
		Multiplication multiplication = new Multiplication(10, 60);
		MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user, multiplication, 600, false);

		//when //then
		assertThat(multiplicationService.checkAttempt(multiplicationResultAttempt)).isTrue();
	}

	@Test
	public void checkCorrectAttemptTestFailure() {
		//given
		BDDMockito.given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

		User user = new User("Test User");
		Multiplication multiplication = new Multiplication(10, 60);
		MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user, multiplication, 1800, false);

		//when //then
		assertThat(multiplicationService.checkAttempt(multiplicationResultAttempt)).isFalse();
	}
}
