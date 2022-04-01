package models;

import java.util.ArrayList;
import java.util.List;

import models.deadbolt.Role;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
@Table(name="t_cfg_role")
public class TCfgRole extends Model implements Role
{
    @Required
    public String name;
    
    public String description;
    
    @ManyToMany
    @JoinTable(name = "t_cfg_role_menu",
    joinColumns=@JoinColumn(name="role_id"),
    inverseJoinColumns=@JoinColumn(name="menu_id"))
    public List<TCfgMenu> menuList = new ArrayList<TCfgMenu>();
    
    @ManyToMany
    @JoinTable(name = "t_cfg_role_user", 
    joinColumns=@JoinColumn(name="role_id"),
    inverseJoinColumns=@JoinColumn(name="user_id"))
    public List<TCfgUser> userList = new ArrayList<TCfgUser>();
    
    public TCfgRole(){
    	
    }

    public TCfgRole(String name)
    {
        this.name = name;
    }

    public String getRoleName()
    {
        return name;
    }

    public static TCfgRole getByName(String name)
    {
        return TCfgRole.find("byName", name).first();
    }

    @Override
    public String toString()
    {
        return this.description;
    }
}
