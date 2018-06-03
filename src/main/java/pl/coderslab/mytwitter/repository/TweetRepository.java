package pl.coderslab.mytwitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.mytwitter.entity.Tweet;
import pl.coderslab.mytwitter.entity.User;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

	Tweet findOneByUserId(Long id);

	List<Tweet> findAllByUserIdOrderByCreatedDesc(Long id);
	
	@Query("SELECT t FROM Tweet t ORDER BY t.created DESC")
	List<Tweet> findAllOrderByCreated();

	@Query("SELECT t FROM Tweet t WHERE t.tweetText LIKE %:term% ORDER BY t.created DESC")
	List<Tweet> findTweetLike(@Param("term") String term);

}
