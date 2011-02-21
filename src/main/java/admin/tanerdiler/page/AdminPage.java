package admin.tanerdiler.page;

import com.tanerdiler.web.article.page.ArticleFormPage;
import com.tanerdiler.web.category.view.CategoryPage;
import com.tanerdiler.web.component.menupanel.MenuItem;
import com.tanerdiler.web.component.menupanel.MenuPanel;
import com.tanerdiler.web.page.MainPage;

public class AdminPage extends MainPage {

	public MenuPanel createMenu() {
		MenuPanel panel = new MenuPanel("menu-container");
		panel.addHomePage(new MenuItem(this.getApplication().getHomePage(), "menu.home", true));
		panel.addItem(new MenuItem(CategoryPage.class, "menu.category"));
		panel.addItem(new MenuItem(ArticleFormPage.class, "menu.article"));
		return panel;
	}
	
}
