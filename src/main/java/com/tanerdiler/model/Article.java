package com.tanerdiler.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.tanerdiler.annotation.ToString;
import com.tanerdiler.util.MongoElement;

@Entity
@Table(name="ARTICLE")
@Embeddable
public class Article extends GenericModel<Integer> implements Commentable<ArticleComment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -69971097902474124L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@MongoElement
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="AUTHOR_ID")
	private Person person;
	
	@ToString(value=true, type="simple")
	@Column(name="TITLE")
	@NotEmpty
	@Length(max=255)
	@MongoElement
	private String title;
	
	
	@ToString(value=true, type="simple")
	@Column(name="INTRO_TEXT")
	@NotEmpty
	@Length(max=500)
	@MongoElement
	private String introText;
	
	@ToString(value=true, type="simple")
	@Column(name="FULL_TEXT")
	@NotEmpty
	@MongoElement
	private String fullText;
	
	@Column(name="CREATED_TIME")
	@MongoElement
	private Calendar createdTime;

	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	@NotNull
	private Category category;
	
	@Column(name="VIEW_COUNT")
	private Integer viewCount;
	
	@Column(name="RATING")
	private Double rating;

	@OneToMany(mappedBy="article", fetch=FetchType.EAGER)
	private List<ArticleComment> comments = new ArrayList<ArticleComment>();
	
	@ToString(value=true, type="collection")
	@OneToMany(mappedBy="article", orphanRemoval=true, cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private Set<ArticleTag> tags = new HashSet<ArticleTag>();
	
	@ToString(value=true, type="simple")
	@Transient
	private String tagString;
	
	public void setTagString(String tagString){
		this.tagString = tagString;
	}
	
	public String getTagString(){
		if(this.tagString == null && this.tags.size() > 0){
			StringBuilder strb = new StringBuilder();
			String separator = "";
			for(ArticleTag tag : this.tags){
				strb.append(separator + tag.getValue().getText());
				separator = ", ";
			}
			this.tagString = strb.toString();
		}
		return tagString;
	}
	
	public Double getRating() {
		return rating;
	}
	
	public Integer getViewCount() {
		if(viewCount == null){
			return 0;
		}
		return viewCount;
	}
	
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public void addComment(ArticleComment comment){
		this.comments.add(comment);
		comment.setArticle(this);
	}
	

	/**
	 * @return the tags
	 */
	public Set<ArticleTag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<ArticleTag> tags) {
		this.tags = tags;
	}
	
	

	/**
	 * @return the comment
	 */
	public List<ArticleComment> getComments() {
		return comments;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComments(List<ArticleComment> comments) {
		this.comments = comments;
	}
	
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the introText
	 */
	public String getIntroText() {
		return introText;
	}

	/**
	 * @param introText the introText to set
	 */
	public void setIntroText(String introText) {
		this.introText = introText;
	}

	/**
	 * @return the fullText
	 */
	public String getFullText() {
		return fullText;
	}

	/**
	 * @param fullText the fullText to set
	 */
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	/**
	 * @return the createdTime
	 */
	public Calendar getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Object getEqualityCriterian() {
		return this.getId();
	}
	
}
