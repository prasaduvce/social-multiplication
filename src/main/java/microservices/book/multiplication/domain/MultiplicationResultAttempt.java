package microservices.book.multiplication.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@RequiredArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class MultiplicationResultAttempt {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private final User user;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MULTIPLICATION_ID")
	private final Multiplication multiplication;
	private final int resultAttempt;

	private final boolean correct;

	public boolean isResultMatching() {
		return this.multiplication.multiply() == resultAttempt;
	}

	public MultiplicationResultAttempt() {
		this.user = null;
		this.multiplication = null;
		this.resultAttempt = -1;
		this.correct = false;
	}
}
