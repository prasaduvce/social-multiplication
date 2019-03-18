package microservices.book.multiplication.repository;

import java.util.List;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiplicationResultAttemptRepository extends JpaRepository<MultiplicationResultAttempt, Long> {

	/**
	 * @return the latest 5 attempts for a given user, identified by their alias.
	 */
	List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
