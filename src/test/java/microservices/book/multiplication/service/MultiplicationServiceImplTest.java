package microservices.book.multiplication.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MultiplicationServiceImplTest {

	@Mock
	private RandomGeneratorService randomGeneratorService;

	@Mock
	private MultiplicationResultAttemptRepository attemptRepository;

	@Mock
	private UserRepository userRepository;

	private MultiplicationServiceImpl multiplicationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		multiplicationService = new MultiplicationServiceImpl(randomGeneratorService, attemptRepository, userRepository);
	}

	@Test
	public void checkCorrectAttemptTest() {
		//given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);
		MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
		BDDMockito.given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
		//BDDMockito.given(randomGeneratorService.generateRandomFactor()).willReturn(50, 60);

		//when
		boolean attemptResult = multiplicationService.checkAttempt(attempt);

		//assert
		assertThat(attemptResult).isTrue();
		Mockito.verify(attemptRepository).save(verifiedAttempt);
	}

	@Test
	public void checkWrongAttemptTest() {
		//given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("Test User");
		MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		BDDMockito.given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());


		//when
		boolean attemptResult = multiplicationService.checkAttempt(multiplicationResultAttempt);

		//then
		assertThat(attemptResult).isFalse();
		Mockito.verify(attemptRepository).save(multiplicationResultAttempt);
	}

	@Test
	public void retrieveStatsTest() {
		//given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);

		List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);
		BDDMockito.given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
		BDDMockito.given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("john_doe")).willReturn(latestAttempts);

		//when
		List<MultiplicationResultAttempt> latestAttemptResults = multiplicationService.getStatsForUser("john_doe");

		//then
		assertThat(latestAttemptResults).isEqualTo(latestAttempts);

	}

	/*@Test
	public void checkCorrectAttemptTestFailure() {
		//given
		BDDMockito.given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

		User user = new User("Test User");
		Multiplication multiplication = new Multiplication(10, 60);
		MultiplicationResultAttempt multiplicationResultAttempt = new MultiplicationResultAttempt(user, multiplication, 1800, false);

		//when //then
		assertThat(multiplicationService.checkAttempt(multiplicationResultAttempt)).isFalse();
	}*/
}
