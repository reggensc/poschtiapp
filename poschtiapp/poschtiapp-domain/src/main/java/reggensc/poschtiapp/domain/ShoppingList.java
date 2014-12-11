package reggensc.poschtiapp.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "tbl_shoppinglist")
@Audited
@NamedQuery(name = "findByOwnerEmail", query = "SELECT sl from ShoppingList sl JOIN sl.owners o WHERE o.email = :email")
public class ShoppingList extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull
    private State state;

    @OneToMany(fetch = FetchType.EAGER)
    @OrderColumn(name = "list_index")
    @JoinColumn(name = "list_id")
    private List<ShoppingCategory> categories;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "map_owners", joinColumns = { @JoinColumn(name = "list_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
    @NotNull
    @Size(min = 1)
    private Set<User> owners;

    public ShoppingList() {
        categories = new ArrayList<>();
        owners = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<ShoppingCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ShoppingCategory> categories) {
        this.categories = categories;
    }

    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    public boolean addOwner(User owner) {
        return owners.add(owner);
    }

    public boolean removeOwner(User owner) {
        return owners.remove(owner);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((categories == null) ? 0 : categories.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((owners == null) ? 0 : owners.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof ShoppingList)) {
            return false;
        }
        ShoppingList other = (ShoppingList) obj;
        if (categories == null) {
            if (other.categories != null) {
                return false;
            }
        } else if (!categories.equals(other.categories)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (owners == null) {
            if (other.owners != null) {
                return false;
            }
        } else if (!owners.equals(other.owners)) {
            return false;
        }
        if (state == null) {
            if (other.state != null) {
                return false;
            }
        } else if (!state.equals(other.state)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShoppingList [name=");
        builder.append(name);
        builder.append(", state=");
        builder.append(state);
        builder.append(", categories=");
        builder.append(categories);
        builder.append(", owners=");
        builder.append(owners);
        builder.append(", getId()=");
        builder.append(getId());
        builder.append("]");
        return builder.toString();
    }

}
