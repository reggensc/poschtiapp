package reggensc.poschtiapp.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_shoppinglist")
public class ShoppingList extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date creationDate;

    @Column
    private Date purchaseDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private State state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "map_owners", joinColumns = { @JoinColumn(name = "shoppinglist_id") }, inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> owners;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "shoppinglist_id")
    private Set<ShoppingItem> items;

    public ShoppingList() {
        owners = new HashSet<>();
        items = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<User> getOwners() {
        return owners;
    }

    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    public Set<ShoppingItem> getItems() {
        return items;
    }

    public void setItems(Set<ShoppingItem> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((owners == null) ? 0 : owners.hashCode());
        result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
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
        if (creationDate == null) {
            if (other.creationDate != null) {
                return false;
            }
        } else if (!creationDate.equals(other.creationDate)) {
            return false;
        }
        if (items == null) {
            if (other.items != null) {
                return false;
            }
        } else if (!items.equals(other.items)) {
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
        if (purchaseDate == null) {
            if (other.purchaseDate != null) {
                return false;
            }
        } else if (!purchaseDate.equals(other.purchaseDate)) {
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
        builder.append(", creationDate=");
        builder.append(creationDate);
        builder.append(", purchaseDate=");
        builder.append(purchaseDate);
        builder.append(", state=");
        builder.append(state);
        builder.append(", owners=");
        builder.append(owners);
        builder.append(", items=");
        builder.append(items);
        builder.append(", id=");
        builder.append(getId());
        builder.append("]");
        return builder.toString();
    }

}
