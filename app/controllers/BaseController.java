package controllers;

import play.cache.Cache;
import play.mvc.Controller;

public class BaseController extends Controller {
	
	public static String theme = (Cache.get("system_theme", String.class)==null||"".equals(Cache.get("system_theme", String.class)))?"default":Cache.get("system_theme", String.class);

}
