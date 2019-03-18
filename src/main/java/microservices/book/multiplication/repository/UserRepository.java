package microservices.book.multiplication.repository;

import java.util.Optional;
import microservices.book.multiplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByAlias(String alias);
}
