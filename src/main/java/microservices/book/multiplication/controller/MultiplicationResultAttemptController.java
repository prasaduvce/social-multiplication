package microservices.book.multiplication.controller;

import lombok.RequiredArgsConstructor;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.ResultResponse;
import microservices.book.multiplication.service.MultiplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/results")
public class MultiplicationResultAttemptController {

	private final MultiplicationService multiplicationService;

	@PostMapping
	ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt multiplicationResultAttempt) {
		boolean isCorrect = multiplicationService
				.checkAttempt(multiplicationResultAttempt);

		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(multiplicationResultAttempt.getUser(), multiplicationResultAttempt.getMultiplication(), multiplicationResultAttempt.getResultAttempt(), isCorrect);
		return ResponseEntity.ok(attempt);
	}

}
