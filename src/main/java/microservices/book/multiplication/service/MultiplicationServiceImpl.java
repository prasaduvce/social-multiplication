package microservices.book.multiplication.service;

import java.util.List;
import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

	private RandomGeneratorService randomGeneratorService;

	private MultiplicationResultAttemptRepository attemptRepository;

	private UserRepository userRepository;

	@Autowired
	public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService,
			MultiplicationResultAttemptRepository attemptRepository,
			UserRepository userRepository) {
		this.randomGeneratorService = randomGeneratorService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generateRandomFactor();
		int factorB = randomGeneratorService.generateRandomFactor();
		return new Multiplication(factorA, factorB);
	}

	@Override
	public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
		boolean resultMatching = resultAttempt.isResultMatching();

		Assert.isTrue(!resultAttempt.isCorrect(), "You can't send an attempt marked as correct");

		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(resultAttempt.getUser(), resultAttempt.getMultiplication(), resultAttempt.getResultAttempt(), resultMatching);

		attemptRepository.save(checkedAttempt);
		return checkedAttempt.isCorrect();
	}

	@Override
	public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
		return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
	}
}
