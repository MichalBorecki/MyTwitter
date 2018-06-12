package pl.coderslab.mytwitter.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.coderslab.mytwitter.entity.Comment;
import pl.coderslab.mytwitter.entity.Tweet;
import pl.coderslab.mytwitter.entity.User;
import pl.coderslab.mytwitter.repository.CommentRepository;
import pl.coderslab.mytwitter.repository.TweetRepository;
import pl.coderslab.mytwitter.repository.UserRepository;

@Controller
@RequestMapping("/tweet")
public class TweetController {

	@Autowired
	private TweetRepository tweetRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CommentRepository commentRepo;

	// add
	@GetMapping("/add")
	public String form(Model model) {
		model.addAttribute("tweet", new Tweet());
		return "tweet/addTweetForm";
	}

	@PostMapping("/add")
	public String formPost(@Valid Tweet tweet, BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "tweet/addTweetForm";
		}
		/*
		 * get user and add to author of tweet
		 */
		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");
		tweet.setUser(user);

		// if updated
		tweet.setId(tweet.getId());

		tweetRepo.save(tweet);
		return "redirect:/tweet/all/" + user.getId();
	}

	// update
	@GetMapping("/update/{id}")
	public String updateJsp(@PathVariable long id, Model model, HttpServletRequest request) {
		Tweet tweet = tweetRepo.findOne(id);
		model.addAttribute(tweet);
		request.setAttribute("message", "Edit tweet:");
		return "tweet/addTweetForm";
	}

	// delete
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id, Model model, HttpServletRequest request) {
		List<Comment> list = commentRepo.findAllByTweetId(id);
		if (list != null) {
			commentRepo.delete(list);
		}
		tweetRepo.delete(id);

		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");
		model.addAttribute("tweets", tweetRepo.findAllByUserIdOrderByCreatedDesc(user.getId()));
		return "tweet/listTweet";
	}

	// all
	@GetMapping("/all")
	public String showAll(Model model) {
		return "tweet/listTweet";
	}

	// allById
	@GetMapping("/all/{id}")
	public String showAllByUserId(@PathVariable long id, Model model, RedirectAttributes redAttr) {
		model.addAttribute("tweets", tweetRepo.findAllByUserIdOrderByCreatedDesc(id));

		commentsQty(model);
		
		return "tweet/listTweet";
	}

	// extracted method for find comments quantity
	public void commentsQty(Model model) {
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
	}
	
	// find by term
	@GetMapping("/find/{term}")
	public String findByTerm(@PathVariable String term, Model model, HttpServletRequest request) {
		String termCorrected = term.replace("+", " ");
		
		List<Tweet> tweetsFound = tweetRepo.findTweetLike(termCorrected);
		model.addAttribute("tweets", tweetsFound);
		
		commentsQty(model);
		
		request.setAttribute("message", "Found tweets with \"" + termCorrected + "\":");
		return "tweet/listTweet";
	}

	// model
	@ModelAttribute("tweets")
	public List<Tweet> getTweets() {
		return this.tweetRepo.findAll();
	}

	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

}
