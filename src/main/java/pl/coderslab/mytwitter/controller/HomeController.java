package pl.coderslab.mytwitter.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.coderslab.mytwitter.entity.Comment;
import pl.coderslab.mytwitter.entity.Tweet;
import pl.coderslab.mytwitter.entity.User;
import pl.coderslab.mytwitter.repository.CommentRepository;
import pl.coderslab.mytwitter.repository.TweetRepository;
import pl.coderslab.mytwitter.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TweetRepository tweetRepo;
	@Autowired
	private CommentRepository commentRepo;

	// get all data for home page
	@GetMapping("")
	public String home(Model model, RedirectAttributes redAttr) {
		model.addAttribute("tweet", new Tweet());
		model.addAttribute("comment", new Comment());
		/*
		 * add model with informations about quantity of comments for each tweet
		 */
		List<Object[]> commentsQuantity = commentRepo.countByTweetId();
		Map<Integer, Integer> map = null;
		if (commentsQuantity != null && !commentsQuantity.isEmpty()) {
			map = new HashMap<Integer, Integer>();
			for (Object[] object : commentsQuantity) {
				Integer objInt1 = ((BigInteger) object[0]).intValue();
				Integer objInt2 = ((BigInteger) object[1]).intValue();
				map.put(objInt1, objInt2);
			}
		}
		model.addAttribute("commentsQuantity", map);
		return "home";
	}

	@GetMapping("/find/")
	public String home(@RequestParam("search_param") String search_param, @RequestParam("term") String term)
		throws UnsupportedEncodingException {
		String encodedTerm = URLEncoder.encode(term, "UTF-8");
		System.out.println(search_param);
		if (search_param.equals("find/tweet/")) {
			return "redirect:/tweet/find/" + encodedTerm;
		}
		return "redirect:/user/find/" + encodedTerm;
	}

	// model
	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

	@ModelAttribute("tweets")
	public List<Tweet> findAllOrderByCreated() {
		return this.tweetRepo.findAllOrderByCreated();
	}
	
//	@ModelAttribute("tweets")
//	public List<Tweet> findAll() {
//		return this.tweetRepo.findAll();
//	}
}
