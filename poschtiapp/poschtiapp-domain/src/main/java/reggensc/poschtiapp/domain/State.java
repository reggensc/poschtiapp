package reggensc.poschtiapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "ref_state", indexes = { @Index(name = "uk_designator", columnList = "designator", unique = true) })
public class State extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
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
        if (!(obj instanceof State)) {
            return false;
        }
        State other = (State) obj;
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
        builder.append("State [state=");
        builder.append(designator);
        builder.append(", id=");
        builder.append(getId());
        builder.append("]");
        return builder.toString();
    }

}
