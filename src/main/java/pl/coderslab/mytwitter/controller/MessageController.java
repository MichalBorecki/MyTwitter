package pl.coderslab.mytwitter.controller;

import java.util.List;

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

import pl.coderslab.mytwitter.entity.Message;
import pl.coderslab.mytwitter.entity.User;
import pl.coderslab.mytwitter.repository.MessageRepository;
import pl.coderslab.mytwitter.repository.UserRepository;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired 
	private UserRepository userRepo;
	@Autowired 
	private MessageRepository messageRepo;
	
	// add
	@GetMapping("/add/{receiverId}")
	public String form(@PathVariable long receiverId, Model model, HttpServletRequest request) {
		Message message = new Message();
		
		//set receiver
		message.setReceiver(userRepo.findOne(receiverId));
		
		//set sender
		HttpSession sess = request.getSession();
		User sender = (User) sess.getAttribute("user");
		message.setSender(sender);
		
		model.addAttribute("message", message);
		return "message/addMessageForm";
	}
	
	@PostMapping("/add")
	public String formPost(@Valid Message message, BindingResult result) {
		if (result.hasErrors()) {
			return "message/addMessageForm";
		}
		messageRepo.save(message);
		return "redirect:/message/all-sent/" + message.getSender().getId();
	}

	
	
	// allReceivedByReceiverId
	@GetMapping("/all-received/{receiverId}")
	public String findAllByReceiverId(@PathVariable long receiverId, Model model) {
		model.addAttribute("messages",messageRepo.findAllByReceiverId(receiverId));
		return "message/listReceived";
	}
	
	// allSendBySenderId
	@GetMapping("/all-sent/{sernderId}")
	public String findAllBySenderId(@PathVariable long sernderId, Model model) {
		model.addAttribute("messages",messageRepo.findAllBySenderId(sernderId));
		return "message/listSent";
	}

	

	// model
	@ModelAttribute("messages")
	public List<Message> getMessages() {
		return this.messageRepo.findAll();
	}
	
	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}


	
}


