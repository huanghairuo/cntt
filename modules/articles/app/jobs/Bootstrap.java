package jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.TCfgGroup;
import models.TCfgMenu;
import models.TCfgRole;
import models.TCfgSystem;
import models.TCfgUser;

import org.apache.commons.lang.StringUtils;

import play.Logger;
import play.Play;
import play.cache.Cache;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import utils.Constant;
import utils.MyCrypt;

/**
 * Created by Humin on 9/17/14.
 */
@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
		Logger.info("start to init the system table...");
		if (TCfgSystem.getSystem() == null) {
			TCfgSystem _system = new TCfgSystem();
			_system.system_title = "乐享CMS系统";
			_system.system_theme = "default";
			_system.save();
		}

		String debug = Play.configuration.getProperty("debug");
		if ("true".equals(debug)) {
			Logger.info("in debug mode...");
			return;
		}

		this.initRole();

		this.initGroup();

		this.initData();
		
		this.loadSystem();

		Logger.info("LeXiang CMS initted successful...");
	}

	private void initData() {
		Logger.info("start to init the data...");

		TCfgUser user = TCfgUser.getByName("superadmin");
		if (user == null) {
			user = new TCfgUser();
			user.name = "superadmin";
			user.password = MyCrypt.getPassword("Passw0rd");
			user.description = "超级管理员";
			user.roles.add(TCfgRole.getByName("superadmin"));
			user.authenticate_type = "LOCAL";
			user.save();
		}
	}

	private void initRole() {
		Logger.info("start to init the role...");

		TCfgRole role = null;
		if (TCfgRole.getByName("superadmin") == null) {
			role = new TCfgRole();
			role.name = "superadmin";
			role.description = "超级管理权限";
			role.save();
		}

	}

	private void initGroup() {
		Logger.info("start to init the group...");

		TCfgGroup group = null;
		if (TCfgGroup.getByName("default") == null) {
			group = new TCfgGroup();
			group.name = "default";
			group.description = "默认用户组";
			group.save();
		}

		if (TCfgGroup.getByName("superadmin") == null) {
			group = new TCfgGroup();
			group.name = "superadmin";
			group.description = "超级管理员";
			group.save();
		}

	}
	
	private void loadSystem() {
		TCfgSystem _system = TCfgSystem.find("1=1").first();
		Cache.set("system_title", _system.system_title);
		Cache.set("system_subtitle", _system.system_subtitle);
		Cache.set("system_keyword", _system.system_keyword);
		Cache.set("system_description", _system.system_description);
		Cache.set("system_copyright", _system.system_copyright);
		Cache.set("system_logo", _system.system_logo);
		Cache.set("system_theme", _system.system_theme);
		Cache.set("system_favicon", _system.system_favicon);
	}

}
