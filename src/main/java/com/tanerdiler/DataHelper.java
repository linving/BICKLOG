package com.tanerdiler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tanerdiler.model.Article;
import com.tanerdiler.model.ArticleComment;
import com.tanerdiler.model.Category;
import com.tanerdiler.model.Person;
import com.tanerdiler.util.HumanFriendlyTime.TimeType;

public class DataHelper {
	public static List<Article> articles = new ArrayList<Article>();
	public static List<Category> categories = new ArrayList<Category>();
	
	
	public static Calendar getPastTime(int amount, TimeType type){
		Calendar pastTime = Calendar.getInstance(); 
		int calendarField = 0;
		switch(type){
		case SEC : 
			calendarField = Calendar.SECOND; break;
		case MIN :
			calendarField = Calendar.MINUTE; break;
		case HOUR :
			calendarField = Calendar.HOUR; break;
		case DAY :
			calendarField = Calendar.DATE; break;
		case WEEK :
			calendarField = Calendar.WEEK_OF_MONTH; break;
		case MONTH :
			calendarField = Calendar.MONTH; break;
		}
		pastTime.add(calendarField, -1 * amount);
		return pastTime;
	}
	
	static {
		Calendar fiveSecBefore = getPastTime(5, TimeType.SEC);
		Calendar twoMinBefore = getPastTime(2, TimeType.MIN);
		Calendar fifteenHourBefore = getPastTime(15, TimeType.HOUR);
		Calendar oneDayBefore = getPastTime(1, TimeType.DAY);
		Calendar fourDayBefore = getPastTime(4, TimeType.DAY);
		Calendar twoWeekBefore = getPastTime(2, TimeType.WEEK);
		Calendar threeMonthBefore = getPastTime(3, TimeType.MONTH);
		
		Article article_1 = createArticle();
		article_1.setId(22);
		
		categories.add(createCategory(1, "Blog", null));
		categories.add(createCategory(2, "Java", categories.get(0)));
		categories.add(createCategory(3, "Wicket", categories.get(0)));
		categories.add(createCategory(4, "Design Patterns", categories.get(0)));
		categories.add(createCategory(5, "Spring", categories.get(0)));
		
		article_1.setCategory(categories.get(2));
		
		article_1.setCreatedTime(fiveSecBefore);
		
		articles.add(article_1);
	}

	private static Article createArticle() {
		Article article_1 = new Article();
		article_1.setTitle("Lorem ipsum dolor sit amet");
		article_1.setFullText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id scelerisque leo. Aenean elementum placerat mauris, in vehicula libero placerat sit amet. Morbi at enim lorem. Aenean vel elementum velit. Proin nisl libero, fringilla non commodo id, tincidunt eget felis. Morbi congue sapien ullamcorper diam tincidunt auctor. Vestibulum eu velit diam, suscipit tempor metus. Nunc vitae urna non neque faucibus lobortis non non odio. Morbi fringilla sagittis mi, non tempor diam mattis eu. Fusce venenatis pharetra viverra. Mauris lobortis quam nec lectus pretium vel rutrum metus porttitor. Mauris vehicula, arcu sit amet imperdiet pellentesque, odio nisi iaculis nisl, sed gravida sem nibh nec massa. Ut ultricies diam non mauris ornare nec tempus tortor adipiscing. Proin non sem arcu, sed aliquet leo. Sed ipsum lacus, aliquet non fringilla eget, laoreet nec sem. Maecenas egestas lacus vitae leo scelerisque porttitor in id nisi. Vestibulum eget ligula et nulla pretium facilisis eu vel metus. Sed ultricies leo nisl. Fusce ornare erat ultricies velit dictum eleifend. Cras vitae turpis at metus ultrices volutpat. Proin convallis arcu at augue hendrerit eu porta enim cursus. Duis vestibulum enim ut magna placerat id euismod neque mollis. Integer mollis congue arcu, vitae congue nunc egestas vitae. Duis sit amet augue eros. Fusce lacus erat, iaculis at tempus vel, suscipit quis ante. Sed ac tortor arcu. Etiam gravida suscipit diam id dictum. Sed tempus lacinia vestibulum. Aenean dictum malesuada sem et auctor. In laoreet euismod nunc, in varius arcu tincidunt sed. Vivamus congue iaculis dui, vel lobortis risus feugiat vitae. Nunc adipiscing odio eu ipsum viverra venenatis. Praesent ornare, ipsum quis posuere rutrum, nulla est semper est, eget laoreet nisl dui nec risus. Etiam eu velit id massa dignissim auctor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras odio libero, sodales vitae porta non, egestas eget mi. Aliquam sagittis accumsan risus, vitae dictum augue ultrices at. Duis ac sapien nec enim convallis fermentum nec id sem. Aenean gravida metus at dolor laoreet rhoncus. Phasellus mollis iaculis justo, non lobortis est sagittis vitae. Aliquam erat volutpat. Cras ac vulputate sem.");
		article_1.setIntroText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id scelerisque leo. Aenean elementum placerat mauris, in vehicula libero placerat sit amet. Morbi at enim lorem. Aenean vel elementum velit.");
		article_1.setPerson(getPerson());
		return article_1;
	}

	private static ArticleComment createComment() {
		ArticleComment comment_1 = new ArticleComment();
		comment_1.setComment("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut id scelerisque leo. Aenean elementum placerat mauris, in vehicula libero placerat sit amet. Morbi at enim lorem. Aenean vel elementum velit. Proin nisl libero, fringilla non commodo id, tincidunt eget felis. Morbi congue sapien ullamcorper diam tincidunt auctor.");
		comment_1.setCreatedTime(Calendar.getInstance());
		comment_1.setAuthor(getPerson());
		return comment_1;
	}

	private static Category createCategory(int id, String name, Category parent) {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		if(parent != null){
			parent.addChild(category);
		}
		return category;
	}

	public static Person getPerson() {
		Person person = new Person();
		person.setFirstName("Taner");
		person.setLastName("Diler");
		person.setEmail("taner.diler@gmail.com");
		person.setNickname("tdiler");
		person.setId(1);
		return person;
	}
}
