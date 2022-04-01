package controllers;

import java.io.File;
import java.util.List;

import models.TCfgArticle;
import models.TCfgArticleCategory;
import play.mvc.Controller;

public class ArticleController extends BaseController {
	
	public static void index() {
		renderTemplate("themes"+File.separator+theme+File.separator+"articles"+File.separator+"index.html");
	}
	
	public static void list(Long id, Integer page) {
		if (page==null || page < 1) {
            page = 1;
        }
    	
    	int pageSize =  Integer.parseInt("10");

    	TCfgArticleCategory category = TCfgArticleCategory.findById(id);

        List<TCfgArticle> articles = TCfgArticle.find("category.id = ?1 order by create_at desc", category.id).fetch(page, pageSize);
        long total = TCfgArticle.count("category.id = ?1 order by create_at desc", category.id);
		renderTemplate("themes"+File.separator+theme+File.separator+"articles"+File.separator+"list.html",page, total, articles, category);
	}
	
	public static void article(Long id) {
		TCfgArticle article = TCfgArticle.findById(id);
		renderTemplate("themes"+File.separator+theme+File.separator+"articles"+File.separator+"article.html", article);
	}

}
