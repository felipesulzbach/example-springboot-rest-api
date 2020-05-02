package restapi.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import restapi.models.enumeration.EnumAccessPermission;

/**
 * @autor: Felipe Sulzbach
 */
@Entity
@Table(schema = "FS_AUTO", name = "PROFILE")
@Proxy(lazy = false)
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_SEQ")
    @SequenceGenerator(schema = "FS_AUTO", name = "PROFILE_SEQ", sequenceName = "PROFILE_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACCESS_PERMISSION")
    private EnumAccessPermission accessPermission;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "FS_AUTO", name = "APP_USER_PROFILE",
               inverseForeignKey = @ForeignKey(name = "FK_APP_USER_PROFILE"),
               joinColumns = @JoinColumn(name = "PROFILE_ID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    @OrderBy("id ASC")
    private List<AppUser> appUserList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EnumAccessPermission getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(EnumAccessPermission accessPermission) {
        this.accessPermission = accessPermission;
    }

    public List<AppUser> getAppUserList() {
        return appUserList;
    }

    public void setAppUserList(List<AppUser> appUserList) {
        this.appUserList = appUserList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Profile other = (Profile) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append("ID: ");
        strb.append(getId());
        strb.append(", NAME: ");
        strb.append(getName());
        strb.append(", CODE: ");
        strb.append(getCode());
        strb.append(", ACCESS_PERMISSION: ");
        strb.append(getAccessPermission());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized Profile create() {
        return new Profile();
    }

    public Profile withId(final Long id) {
        this.id = id;
        return this;
    }

    public Profile withName(final String name) {
        this.name = name;
        return this;
    }

    public Profile withCode(final String code) {
        this.code = code;
        return this;
    }

    public Profile withAccessPermission(final EnumAccessPermission accessPermission) {
        this.accessPermission = accessPermission;
        return this;
    }
}
