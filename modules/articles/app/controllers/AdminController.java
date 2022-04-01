package controllers;

import java.util.List;

import controllers.CRUD;
import models.TCfgArticleCategory;
import models.TCfgMenu;
import models.TCfgPage;

public class AdminController extends CRUD {

    public static void index() {
        render("admin/index.html");
    }
    
    public static void customMenu() {
		List<TCfgMenu> menus = TCfgMenu.find("menu_parent_id is null order by menu_order desc, id desc").fetch();
		renderJSON(menus);
	}
    
    public static void getByMenuType(String type) {
    	if(type.equalsIgnoreCase("list")) {
    		List<TCfgArticleCategory> _article_category = TCfgArticleCategory.findAll();
    		renderJSON(_article_category);
    	}
    	
    	if(type.equalsIgnoreCase("page")) {
    		List<TCfgPage> _pages = TCfgPage.findAll();
    		renderJSON(_pages);
    	}
    }

}