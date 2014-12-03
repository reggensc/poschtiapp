package reggensc.poschtiapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ref_states")
public class State extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String stateName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
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
        if (stateName == null) {
            if (other.stateName != null) {
                return false;
            }
        } else if (!stateName.equals(other.stateName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("State [state=");
        builder.append(stateName);
        builder.append(", id=");
        builder.append(getId());
        builder.append("]");
        return builder.toString();
    }

}
