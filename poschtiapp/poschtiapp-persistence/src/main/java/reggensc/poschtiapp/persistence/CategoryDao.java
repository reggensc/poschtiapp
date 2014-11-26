package reggensc.poschtiapp.persistence;

import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.Category;

@Repository
public class CategoryDao extends AbstractDao<Category> {

	public CategoryDao() {
		super(Category.class);
	}

}
