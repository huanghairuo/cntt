package models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_cfg_page_category")
public class TCfgPageCategory extends Model {
	
	@Required
	public String name;
	
	@Lob
	@MaxSize(1000)
	public String description;
	
    @Override
    public String toString() {
    		return this.name;
    }

}
