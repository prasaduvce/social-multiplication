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
public class Multiplication {
	private int factorA;
	private int factorB;

	public int multiply() {
		return this.factorA * this.factorB;
	}
}
