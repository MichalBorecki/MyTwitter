package pl.coderslab.mytwitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.coderslab.mytwitter.entity.Comment;
import pl.coderslab.mytwitter.entity.Tweet;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Comment findOneByTweetId(Long id);
	
	List<Comment> findAllByTweetOrderByCreatedDesc(Tweet tweet);
	
	List<Comment> findAllByTweetIdOrderByCreatedAsc(Long id);
	
	List<Comment>  findAllByTweetId(Long id);

	
	@Query(value= countByTweetId, nativeQuery = true)
	List<Object[]>  countByTweetId();
	final String countByTweetId= "SELECT c.tweet_id, COUNT(c.id) as counter FROM comments c GROUP BY c.tweet_id";
	
	//Query query = session.createSQLQuery("SELECT c.tweet_id, COUNT(c.id) as counter FROM comments c GROUP BY c.tweet_id").addScalar("tweet_id", LongType.INSTANCE);

}
