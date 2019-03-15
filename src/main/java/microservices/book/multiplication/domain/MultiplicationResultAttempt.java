package microservices.book.multiplication.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
public class MultiplicationResultAttempt {

	private User user;
	private Multiplication multiplication;
	private int resultAttempt;

	public boolean isResultMatching() {
		return this.multiplication.multiply() == resultAttempt;
	}
}
