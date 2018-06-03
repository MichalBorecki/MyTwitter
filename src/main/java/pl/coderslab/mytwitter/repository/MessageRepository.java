package pl.coderslab.mytwitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.mytwitter.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findAllBySenderId(Long id);

	List<Message> findAllByReceiverId(Long id);

}
