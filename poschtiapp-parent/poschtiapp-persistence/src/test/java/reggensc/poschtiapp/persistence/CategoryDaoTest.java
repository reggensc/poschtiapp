package reggensc.poschtiapp.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reggensc.poschtiapp.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class })
public class CategoryDaoTest {

	@Autowired
	private CategoryDao categoryDao;

	private Category category;

	@Before
	public void setUp() {
		category = new Category();
		category.setName("TestCategory");
		category.setDescription("Description of TestCategory");
	}

	@Test
	public void testCreateUpdateDelete() {
		List<Category> categories = categoryDao.getAll();
		assertEquals(0, categories.size());

		categoryDao.saveOrUpdate(category);
		assertEquals(1, categoryDao.getAll().size());

		Long id = category.getId();
		assertNotNull(id);

		category = categoryDao.getById(id);
		category.setName("Changed TestCategory");
		categoryDao.saveOrUpdate(category);
		assertEquals(1, categoryDao.getAll().size());

		categoryDao.delete(category);
		assertEquals(0, categoryDao.getAll().size());
	}

}