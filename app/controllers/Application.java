package controllers;

import play.*;
import play.cache.Cache;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.*;

import java.io.File;
import java.io.InputStream;
import java.util.*;

import models.*;

import javax.swing.text.Style;

import controllers.CRUD.ObjectType;

public class Application extends BaseController {
	
	public static void index() {

        List<String> _news_categories = TCfgArticleCategory.findAllSubCategoriesByName("新闻中心");

        List<TCfgArticle> news = TCfgArticle.find("category.name in (?1) order by create_at desc", _news_categories).fetch(3);

        List<String> _notice_categories = TCfgArticleCategory.findAllSubCategoriesByName("通知公告");

        List<TCfgArticle> notices = TCfgArticle.find("category.name in (?1) order by create_at desc", _notice_categories).fetch(9);

        List<String> _server_categories = TCfgArticleCategory.findAllSubCategoriesByName("服务");
        List<TCfgArticle> servers = TCfgArticle.find("category.name in (?1) order by create_at desc", _server_categories).fetch();

        render("themes"+File.separator+theme+File.separator+"home"+File.separator+"index.html", news, notices,servers);
    }

    public static void article(Long id) {
        TCfgArticle article = TCfgArticle.findById(id);
        render(article);
    }

    //    率先
    public static void shuaixian(String msg) {
        List<String> _shuaixian_categories = TCfgArticleCategory.findAllSubCategoriesByName("中科率先");

        List<TCfgArticle> shuaixianList = TCfgArticle.find("category.name in (?1) order by create_at desc", _shuaixian_categories).fetch(10);
        render(shuaixianList, msg);
    }

    public static void shuaixianList() {
        List<String> _shuaixian_categories = TCfgArticleCategory.findAllSubCategoriesByName("中科率先");

        List<TCfgArticle> shuaixianList = TCfgArticle.find("category.name in (?1) order by create_at desc", _shuaixian_categories).fetch();
        render(shuaixianList);
    }

    //    工厂
    public static void genfire(String msg) {
        List<String> _genfire_categories = TCfgArticleCategory.findAllSubCategoriesByName("中科智汇工场");

        List<TCfgArticle> genfireList = TCfgArticle.find("category.name in (?1) order by create_at desc", _genfire_categories).fetch(10);
        render(genfireList, msg);
    }
    
//  工厂
  public static void services() {
	  	render();
  }
  

    public static void genfireList() {
        List<String> _genfire_categories = TCfgArticleCategory.findAllSubCategoriesByName("中科智汇工场");

        List<TCfgArticle> genfireList = TCfgArticle.find("category.name in (?1) order by create_at desc", _genfire_categories).fetch();
        render(genfireList);
    }

    //    服务案例
    public static void cases() {
        List<String> _government_categories = TCfgArticleCategory.findAllSubCategoriesByName("政府");
        List<TCfgArticle> governments = TCfgArticle.find("category.name in (?1) order by create_at desc", _government_categories).fetch();

        List<String> _institutes_categories = TCfgArticleCategory.findAllSubCategoriesByName("院所");
        List<TCfgArticle> institutes = TCfgArticle.find("category.name in (?1) order by create_at desc", _institutes_categories).fetch();

        List<String> _enterprise_categories = TCfgArticleCategory.findAllSubCategoriesByName("企业");
        List<TCfgArticle> enterprises = TCfgArticle.find("category.name in (?1) order by create_at desc", _enterprise_categories).fetch();
        render(governments, institutes, enterprises);
    }
    
    public static void thumnails(long id) {
    	final TCfgArticle article = TCfgArticle.findById(id);
    	response.setContentTypeIfNotSet(article.thumnails.type());
    	InputStream binaryData = article.thumnails.get();
    	renderBinary(binaryData);
    } 

    //    中心概述
    public static void summarize(String msg) {
        render("themes"+File.separator+theme+File.separator+"summarize"+File.separator+msg+".html", msg);
    }

    //    产业联盟
    public static void industry(String msg) {
        render("Application/industry.html", msg);
    }

    //    联系我们
    public static void contactus() {
        render("Application/contactus.html");
    }

    //    友情链接
    public static void blogroll() {
        render("Application/blogroll.html");
    }

    //    新闻更多
    public static void newsList(Integer page) {
    	if (page==null || page < 1) {
            page = 1;
        }
    	
    	int pageSize =  Integer.parseInt("10");

        List<String> _news_categories = TCfgArticleCategory.findAllSubCategoriesByName("新闻中心");

        List<TCfgArticle> articles = TCfgArticle.find("category.name in (?1) order by create_at desc", _news_categories).fetch(page, pageSize);
        long total = TCfgArticle.count("category.name in (?1) order by create_at desc", _news_categories);
        render(page, total, articles);
    }

    //    通告更多
    public static void annunciates() {

        List<String> _notice_categories = TCfgArticleCategory.findAllSubCategoriesByName("通知公告");

        List<TCfgArticle> notices = TCfgArticle.find("category.name in (?1) order by create_at desc", _notice_categories).fetch();
        render(notices);
    }


}

