package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.mytwitter.entity.User;
import pl.coderslab.mytwitter.repository.UserRepository;

public class UserConverter implements Converter<String, User> {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User convert(String id) {
		return this.userRepo.findOne(Long.parseLong(id));
	}
}
