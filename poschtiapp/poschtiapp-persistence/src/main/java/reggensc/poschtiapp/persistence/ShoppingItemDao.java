package reggensc.poschtiapp.persistence;

import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.ShoppingItem;

@Repository
public class ShoppingItemDao extends AbstractDao<ShoppingItem> {

    public ShoppingItemDao() {
        super(ShoppingItem.class);
    }

}
