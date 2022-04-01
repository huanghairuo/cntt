package controllers;

import java.util.Hashtable;

import models.TCfgUser;

import org.apache.commons.lang.StringUtils;

import play.Logger;

public class Security extends Secure.Security {

    static boolean authentify(String username, String password) {
        TCfgUser user = TCfgUser.getByName(username);
        boolean result = false;
        if (user == null) {
            flash.error("用户不存在");
            flash.put("username", username);
        } else {
            if (user.authenticate_type == null || "LOCAL".equalsIgnoreCase(user.authenticate_type)
                    || "".equals(user.authenticate_type)) {
                if (TCfgUser.connect(username, password) != null) {
                    result = true;
                }
            }
            if (result) {
                Logger.info("login user:" + user.name);
                session.put("username", user.name);
            } else {
                Logger.info("用户名和密码不匹配");
                flash.error("用户名和密码不匹配");
            }
        }
        return result;
    }

    static void onDisconnected() {
        Application.index();
    }

    static void onAuthenticated() {
        Application.index();
    }

}
