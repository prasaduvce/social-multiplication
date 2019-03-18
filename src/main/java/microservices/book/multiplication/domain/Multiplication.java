package microservices.book.multiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@RequiredArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Multiplication {

	@Id
	@GeneratedValue
	@Column(name = "MULTIPLICATION_ID")
	private Long id;
	private final int factorA;
	private final int factorB;

	public int multiply() {
		return this.factorA * this.factorB;
	}

	public Multiplication() {
		this.factorA = -1;
		this.factorB = -1;
	}
}
