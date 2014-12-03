package reggensc.poschtiapp.persistence;

import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.State;

@Repository
public class StateDao extends AbstractDao<State> {

    public StateDao() {
        super(State.class);
    }

}
