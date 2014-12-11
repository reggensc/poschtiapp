package reggensc.poschtiapp.persistence;

import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.ShoppingCategory;

@Repository
public class ShoppingCategoryDao extends AbstractDao<ShoppingCategory> {

    public ShoppingCategoryDao() {
        super(ShoppingCategory.class);
    }

}
