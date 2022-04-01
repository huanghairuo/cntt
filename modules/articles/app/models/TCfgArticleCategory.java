package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.data.validation.Required;

@Entity
@Table(name="t_cfg_article_category")
public class TCfgArticleCategory extends Models {

    @Required
    public String name;
    
    @MaxSize(1000)
    public String description;
    
    @ManyToOne
    public TCfgArticleCategory parent_category;
    
	public Date create_at = new Date();
	
	public static List<String> findAllSubCategoriesByName(String name) {
		List<TCfgArticleCategory> categories = find("byName", name).fetch();
		List<String> _str_categories = new ArrayList<String>();
		if(categories!=null) {
			for(TCfgArticleCategory category: categories) {
				findSubCategories(category, _str_categories, true);
			}
		}
		return _str_categories;
	}
	
	public static List<TCfgArticleCategory> findAllSubCategories(TCfgArticleCategory category) {
		List<TCfgArticleCategory> categories = new ArrayList<TCfgArticleCategory>();
		categories.add(category);
		return categories;
	}
	
	public static void findSubCategories(TCfgArticleCategory category, List<String> categories, Boolean is_ergodic) {
		categories.add(category.name);
		List<TCfgArticleCategory> _sub_categories = find("parent_category.id=?1", category.id).fetch();
		if(is_ergodic && _sub_categories!=null && _sub_categories.size()>0) {
			for(TCfgArticleCategory _sub_category: _sub_categories) {
				findSubCategories(_sub_category, categories, true);
			}
		}
	}
    
    @Override
    public String toString() {
    		return this.name;
    }
}
