package pl.coderslab.mytwitter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.mytwitter.entity.Comment;
import pl.coderslab.mytwitter.entity.Tweet;
import pl.coderslab.mytwitter.repository.CommentRepository;
import pl.coderslab.mytwitter.repository.TweetRepository;
import pl.coderslab.mytwitter.repository.UserRepository;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private TweetRepository tweetRepo;
	@Autowired
	private CommentRepository commentRepo;
	@Autowired 
	private UserRepository userRepo;
	
	
	// add for tweet of id: tweetId, by userId
	@PostMapping("/add/{tweetId}/{userId}")
	@ResponseBody
	public List<Comment> saveComment(@PathVariable long tweetId, @PathVariable long userId, @RequestBody Comment comment,  HttpServletRequest request) {
		comment.setUser(userRepo.getOne(userId));
		comment.setTweet(tweetRepo.getOne(tweetId));
		commentRepo.save(comment);
		return commentRepo.findAllByTweetIdOrderByCreatedAsc(tweetId);
	}


	// get list of all by tweetId
	@GetMapping("/all/{tweetId}")
	@ResponseBody
	public List<Comment> showAllByTweetId(@PathVariable long tweetId, Model model) {
		model.addAttribute("tweet", new Tweet());
		return commentRepo.findAllByTweetIdOrderByCreatedAsc(tweetId);
	}

}


