package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.db.jpa.Model;
import utils.Constant;

@Entity
@Table(name="t_cfg_log")
public class TCfgLog extends Model {

	public String username;
	
	public String severity;
	
	public String type;
	
	public String source;
	
	public String summary;
	
	public String message;
	
	public Date create_at;
	
	public String toString(){
		return this.summary;
	}
	
	public static void info(String username, String type, String summary, String message){
		TCfgLog log = new TCfgLog();
		log.create_at = new Date();
		log.message = message;
		log.summary = summary;
		log.username = username;
		log.type = type;
		log.severity = Constant.LOG_INFO;
		log.save();
	}
	
	public static void warn(String username, String type, String summary, String message){
		TCfgLog log = new TCfgLog();
		log.create_at = new Date();
		log.message = message;
		log.summary = summary;
		log.username = username;
		log.type = type;
		log.severity = Constant.LOG_WARNING;
		log.save();
	}
	
	public static void error(String username, String type, String summary, String message){
		TCfgLog log = new TCfgLog();
		log.create_at = new Date();
		log.message = message;
		log.summary = summary;
		log.username = username;
		log.type = type;
		log.severity = Constant.LOG_ERROR;
		log.save();
	}
}
