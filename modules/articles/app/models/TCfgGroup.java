package models;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import controllers.CRUD.Exclude;

@Entity
@Table(name = "t_cfg_group")
public class TCfgGroup extends Model {
    @Required
    @Unique
    public String name;

    public String description;

    @ManyToMany
    @JoinTable(name = "t_cfg_group_user", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName="id"))
    public List<TCfgUser> users = new ArrayList<TCfgUser>();

    @ManyToMany
    @JoinTable(name = "t_cfg_group_role", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<TCfgRole> roleList = new ArrayList<TCfgRole>();

    @Exclude
    public String dn;

    @Exclude
    public String dn_ad;

    @Override
    public String toString() {
        return this.name;
    }

    public static TCfgGroup getByName(String name) {
        return TCfgGroup.find("byName", name).first();
    }

    public static Long getMaxId() {
        Long result = (Long) TCfgGroup.em().createQuery("select max(id) from TCfgGroup").getSingleResult();
        result++;
        return result;
    }

}
