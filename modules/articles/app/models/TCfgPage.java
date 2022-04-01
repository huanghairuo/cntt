package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import controllers.CRUD.Exclude;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="t_cfg_page")
public class TCfgPage extends Model {
	
	@Required
	public String title;
	
	@Lob
	@MaxSize(10000000)
	@Column(columnDefinition="longtext")
	public String content;
	
	public Date create_at = new Date();
	
	@Exclude
	public Date update_at = new Date();
	
	@Exclude
	public Date delete_at;

    @Override
    public String toString() {
    		return this.title;
    }
}
