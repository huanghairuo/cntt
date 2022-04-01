package models;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;

/**
 * Created by Humin on 9/17/14.
 */
@Entity
@Table(name = "t_cfg_system")
@Access(AccessType.FIELD)
public class TCfgSystem extends Model {

    @Required
    public String system_title;

    public String system_subtitle;

    public String system_keyword;

    public String system_description;

    public String system_license;

    public String system_theme="default";

    public String system_copyright;
    
    @Lob
    public Blob system_logo;

    @Lob
    public Blob system_favicon;


    public String cloud_client_url;
    
    public static TCfgSystem getSystem(){
        TCfgSystem _system = TCfgSystem.find("1=1").first();
        return _system;
    }

    public String toString(){
        return this.system_title;
    }
}
