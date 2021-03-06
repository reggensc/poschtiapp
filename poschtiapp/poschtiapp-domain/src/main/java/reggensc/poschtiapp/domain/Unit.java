package reggensc.poschtiapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "ref_unit", indexes = { @Index(name = "uk_designator", columnList = "designator", unique = true) })
@Audited
public class Unit extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @NotNull
    private String designator;

    public String getDesignator() {
        return designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((designator == null) ? 0 : designator.hashCode());
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
        if (!(obj instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) obj;
        if (designator == null) {
            if (other.designator != null) {
                return false;
            }
        } else if (!designator.equals(other.designator)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Unit [unitString=");
        builder.append(designator);
        builder.append(", id=");
        builder.append(getId());
        builder.append("]");
        return builder.toString();
    }

}
