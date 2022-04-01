package controllers;

import java.lang.reflect.Constructor;

import controllers.CRUD.ObjectType;
import models.TCfgSystem;
import play.cache.Cache;
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;

@CRUD.For(TCfgSystem.class)
public class Settings extends CRUD {
	
	public static void save(String id) throws Exception {
		ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        TCfgSystem _system = TCfgSystem.findById(id);
        notFoundIfNull(_system);
        Binder.bindBean(params.getRootParamNode(), "object", _system);
        validation.valid(_system);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/show.html", type, _system);
            } catch (TemplateNotFoundException e) {
                render("CRUD/show.html", type, _system);
            }
        }
        _system._save();
        Cache.set("system_title", _system.system_title);
		Cache.set("system_subtitle", _system.system_subtitle);
		Cache.set("system_keyword", _system.system_keyword);
		Cache.set("system_description", _system.system_description);
		Cache.set("system_copyright", _system.system_copyright);
		Cache.set("system_logo", _system.system_logo);
		Cache.set("system_theme", _system.system_theme);
		Cache.set("system_favicon", _system.system_favicon);
        flash.success(play.i18n.Messages.get("crud.saved", type.modelName));
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        redirect(request.controller + ".show", _system._key());
    }

}
