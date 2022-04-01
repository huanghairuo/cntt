package controllers;

import java.io.File;

import play.mvc.Controller;

public class PageController extends BaseController {
	
	public static void page(String id) {
		renderTemplate("themes"+File.separator+theme+File.separator+"pages"+File.separator+"page.html");
	}

}
