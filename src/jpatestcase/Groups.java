/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpatestcase;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author gustavoduarte
 */
@Entity
@Table(name = "groups")
@NamedQueries({@NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g")})
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @SequenceGenerator(name = "groups_id", sequenceName = "groups_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "groups_id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "groups")
    private List<UserGroup> userGroupCollection;

    public Groups() {
    }

    public Groups(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserGroup> getUserGroupCollection() {
        return userGroupCollection;
    }

    public void setUserGroupCollection(List<UserGroup> userGroupCollection) {
        this.userGroupCollection = userGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpatestcase.Groups[id=" + id + "]";
    }
}
