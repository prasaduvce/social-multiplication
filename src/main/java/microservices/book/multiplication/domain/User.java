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
public class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;

	private final String alias;

	public User() {
		this.alias = null;
	}
}
