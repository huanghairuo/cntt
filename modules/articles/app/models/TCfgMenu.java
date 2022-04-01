package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Model;
import controllers.CRUD.Exclude;

/**
 * Created by Humin on 9/17/14.
 */
@Entity
@Table(name = "t_cfg_menu")
@Access(AccessType.FIELD)
public class TCfgMenu extends Model implements Comparable<Object> {

    @Required
    public String menu_name;

    // For display
    public String menu_name_alias;

    public String menu_icon;

    /**
     * page: 单页面菜单 
     * list: 列表菜单
     */
    public String menu_type;
    
    public String menu_url;
    
    // Sort by asc.
    @Min(1)
    public Integer menu_order;

    public Boolean menu_status = true;

    public String menu_image;

    @ManyToOne
    public TCfgMenu menu_parent;

    /** public or private */
    @Exclude
    public Boolean menu_public = true;

    @Exclude
    @ManyToOne
    public TCfgUser user;
    
    @Exclude
    public Boolean menu_blank = false;
    
    
    @Override
    public String toString() {
    	return (this.menu_name_alias==null || "".equals(this.menu_name_alias))? this.menu_name: this.menu_name_alias;
    }
    

    public static List<TCfgMenu> getMenusByUserName(String userName) {
        List<TCfgMenu> _list_menu = new ArrayList<TCfgMenu>();
        if (StringUtils.isNotBlank(userName)) {
            if ("superadmin".equalsIgnoreCase(userName)) {
                _list_menu = TCfgMenu.find("order by menu_order asc").fetch();
            } else {
                TCfgUser user = TCfgUser.getByName(userName);
                if (user != null) {
                    List<TCfgGroup> groupList = TCfgGroup.find(
                            "select role from TCfgRole role, TCfgGroup g fetch join users u where g.id = rowl. u.id=?", user.id).fetch();
                    for (TCfgGroup group : groupList) {
                        List<TCfgRole> roleList = TCfgGroup.find("group_id", group.id).fetch();
                        for (TCfgRole role : roleList) {
                            List<TCfgMenu> menuList = TCfgRole.find("role_id", role.id).fetch();
                            for (TCfgMenu menu : menuList) {
                                _list_menu.add(menu);
                            }
                        }
                    }
                    // group = user.group;
                }
                // _list_menu =
                // TCfgMenu.find("select m from TCfgMenu m join fetch m.menu_group g where g.id=? order by menu_order asc",
                // group.id).fetch();
            }
        }
        return _list_menu;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        if (this == o) {
            return 0;
        } else if (o != null && o instanceof TCfgMenu) {
            TCfgMenu menu = (TCfgMenu) o;
            if (this.menu_order == null) {
                return -1;
            }
            if (menu.menu_order == null) {
                return 1;
            }
            if (this.menu_order.equals(menu.menu_order)) {
                return this.id.compareTo(menu.id);
            }
            return this.menu_order.compareTo(menu.menu_order);
        } else {
            return -1;
        }
    }

}
