package controllers;

import controllers.deadbolt.DeadboltHandler;
import controllers.deadbolt.ExternalizedRestrictionsAccessor;
import controllers.deadbolt.RestrictedResourcesHandler;
import models.deadbolt.ExternalizedRestrictions;
import models.deadbolt.RoleHolder;
import models.TCfgUser;
import play.mvc.Controller;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class MyDeadboltHandler extends Controller implements DeadboltHandler
{
    public static void authenticate(String username, String password) {
    	TCfgUser user = TCfgUser.getByName(username);
        if (user == null) {
            flash.error("该用户不存在");
            flash.put("username", username);

        } else if (TCfgUser.connect(username, password) == null) {
            flash.error("错误的用户名和密码");
            flash.put("username", username);

        }
        flash.success("欢迎回来， %s !", user.name);
    }


    public void beforeRoleCheck()
    {
        // Note that if you provide your own implementation of Secure's Security class you would refer to that instead
        if (!Security.isConnected())
        {
            try
            {
                if (!session.contains("username"))
                {
                    flash.put("url", "GET".equals(request.method) ? request.url : "/");
                    Secure.login();
                }
            }
            catch (Throwable t)
            {
                // handle this in an app-specific way
            }
        }
    }

    public RoleHolder getRoleHolder()
    {
        String userName = Secure.Security.connected();
        return TCfgUser.getByName(userName);
    }

    public void onAccessFailure(String controllerClassName)
    {
       forbidden();
    }

    public ExternalizedRestrictionsAccessor getExternalizedRestrictionsAccessor()
    {
        return new ExternalizedRestrictionsAccessor()
        {
            public ExternalizedRestrictions getExternalizedRestrictions(String name)
            {
                return null;
            }
        };
    }

    public RestrictedResourcesHandler getRestrictedResourcesHandler()
    {
        return null;
    }
}
