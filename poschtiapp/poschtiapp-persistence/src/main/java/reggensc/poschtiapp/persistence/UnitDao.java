package reggensc.poschtiapp.persistence;

import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.Unit;

@Repository
public class UnitDao extends AbstractDao<Unit> {

    public UnitDao() {
        super(Unit.class);
    }

}
