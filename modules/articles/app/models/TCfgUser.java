package models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import models.TCfgGroup;
import models.TCfgRole;
import models.deadbolt.Role;
import models.deadbolt.RoleHolder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import play.Logger;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Blob;
import play.db.jpa.Model;
import utils.MyCrypt;
import controllers.CRUD.Exclude;
import controllers.CRUD.Hidden;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
@Table(name = "t_cfg_user")
public class TCfgUser extends Model implements RoleHolder {
    @Required
    @Unique
    public String name; // 对就LDAP的sAMAccountName

    public String display_name;

    public String description;

    public String authenticate_type = "LOCAL";

    public Blob photo;

    @Password
    @MinSize(6)
    public String password;

    @ManyToMany
    @JoinTable(name = "t_cfg_role_user",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    public List<TCfgRole> roles = new ArrayList<TCfgRole>();

    @ManyToMany(mappedBy = "users")
    public List<TCfgGroup> groups = new ArrayList<TCfgGroup>();

    public String url;

    public Integer age;
    public String sex;

    public String telephone;
    public String mobile;

    @Unique
    public String mail;

    /** 首次登录是否修改密码 */
    @Exclude
    public Boolean first_login = true;

    public TCfgUser() {

    }

    public TCfgUser(String name, String password) {
        this.password = password;
        this.name = name;
    }

    public static TCfgUser getByName(String name) {
        return find("name=?1 or display_name=?2 or mail=?3", name, name, name).first();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public List<? extends Role> getRoles() {
        return roles;
    }

    public static TCfgUser connect(String name, String password) {
        return find("(mail=?1 or name=?2 or display_name=?3) and password=?4", name, name, name, MyCrypt.getPassword(password)).first();
    }

}
