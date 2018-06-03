package pl.coderslab.mytwitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.mytwitter.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.firstName LIKE %:term% OR u.lastName LIKE %:term% OR u.userName LIKE %:term%")
	List<User> findUserLike(@Param("term") String term);
	
}
