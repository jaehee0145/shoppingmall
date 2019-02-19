package my.examples.JBCmart.repository;

import my.examples.JBCmart.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT a FROM User a WHERE a.email = :email")
    public User getUserByEmail(@Param("email") String email);
}
