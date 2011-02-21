package com.tanerdiler.bpo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.tanerdiler.dao.ArticleDAO;
import com.tanerdiler.dao.ArticleRatingLogDAO;
import com.tanerdiler.dao.ArticleViewLogDAO;
import com.tanerdiler.dao.CommentDAO;
import com.tanerdiler.model.Article;
import com.tanerdiler.model.ArticleRatingLog;
import com.tanerdiler.model.ArticleViewLog;
import com.tanerdiler.model.Comment;
import com.tanerdiler.model.Person;

@Service
@Scope("session")
public class UserBPO {
	
	@Autowired ArticleDAO articleDAO;
	@Autowired CommentDAO commentDAO;
	@Autowired ArticleViewLogDAO articleViewDAO;
	@Autowired ArticleRatingLogDAO articleRatingDAO;
	
	private Person user;
	private boolean signedIn;
	
	public Person getUser() {
		return user;
	}

	public UserBPO user(Person person){
		this.user = person;
		return this;
	}
	
	public boolean signedIn(){
		return signedIn;
	}
	
	public Integer writeArticle(Article article){
		article.setPerson(this.user);
		return articleDAO.saveOrUpdate(article);
	}
	
	public Integer writeComment(Comment comment){
		return commentDAO.saveOrUpdate(comment);
	}

	public void viewArticle(String ipAddress, String referer, Article article) {
		ArticleViewLog view = new ArticleViewLog(this.user, article);
		view.setReferer(referer);
		view.setIp(ipAddress);
		articleViewDAO.saveOrUpdate(view);
	}

	public void voteArticle(String ipAddress, Article article, Integer voteValue, String sessionId) {
		ArticleRatingLog rating = new ArticleRatingLog(this.user, article, voteValue);
		rating.setIp(ipAddress);
		rating.setSessionId(sessionId);
		articleRatingDAO.saveOrUpdate(rating);
	}

	public boolean signIn(String username, String password) {
		if("tdiler".equals(username) && "1q2w3e".equals(password)){
			Person user = new Person();
			user.setFirstName("Taner");
			user.setLastName("Diler");
			user.setId(1);
			this.user(user);
			signedIn = true;
			return true;
		} else {
			return false;
		}
	}
}
