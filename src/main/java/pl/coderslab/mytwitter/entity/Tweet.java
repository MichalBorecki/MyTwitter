package pl.coderslab.mytwitter.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tweets")
public class Tweet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@NotBlank
	@Size(min = 1, max = 160)
	@Column(columnDefinition = "TEXT")
	private String tweetText;
	@Column(updatable = false)
	@CreationTimestamp
	// private Date created;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat
	private Date created;
	@ManyToOne
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public String getCreated() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY HH:mm");
		String dateString = sdf.format(created);
		return dateString;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("Tweet [id=%s, tweetText=%s, created=%s, user=%s]", id, tweetText, created, user);
	}

}
