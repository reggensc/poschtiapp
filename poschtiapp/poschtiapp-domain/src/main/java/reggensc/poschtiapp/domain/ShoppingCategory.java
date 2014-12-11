package reggensc.poschtiapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "tbl_shoppingcategory")
@Audited
@NamedQuery(name = "getReferenceCategoryNames", query = "SELECT DISTINCT(sc.name)from ShoppingCategory sc")
public class ShoppingCategory extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @OrderColumn(name = "category_index")
    @JoinColumn(name = "category_id")
    private List<ShoppingItem> items;

    public ShoppingCategory() {
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShoppingItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingItem> items) {
        this.items = items;
    }

    public boolean addItem(ShoppingItem item) {
        return items.add(item);
    }

    public boolean removeItem(ShoppingItem item) {
        return items.remove(item);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof ShoppingCategory)) {
            return false;
        }
        ShoppingCategory other = (ShoppingCategory) obj;
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
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Category [name=");
        builder.append(name);
        builder.append(", items=");
        builder.append(items);
        builder.append(", getId()=");
        builder.append(getId());
        builder.append("]");
        return builder.toString();
    }

}
