package reggensc.poschtiapp.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reggensc.poschtiapp.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class })
public class CategoryDaoIT {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryDao categoryDao;

    private Category category;

    @Before
    public void setUp() {
        logger.debug("Creating test data");
        category = new Category();
        category.setName("TestCategory");
        category.setDescription("Description of TestCategory");
        logger.debug("Test data created: {}", category);
    }

    @Test
    @Transactional
    public void testCreateUpdateDelete() {
        logger.debug("Loading initial categories");
        List<Category> categories = categoryDao.getAll();
        int origSize = categories.size();
        logger.debug("Successfully loaded initial categories");

        logger.debug("Saving test data category");
        category = categoryDao.saveOrUpdate(category);
        Assert.assertEquals(origSize + 1, categoryDao.getAll().size());
        Long id = category.getId();
        Assert.assertNotNull(id);
        logger.debug("Successfully saved test data category  ");

        logger.debug("Loading test data category for update");
        category = categoryDao.getById(id);
        category.setName("Changed TestCategory");
        category.setDescription("Changed description of TestCategory");
        logger.debug("Successfully loaded test data category for update");

        logger.debug("Updating test data category");
        categoryDao.saveOrUpdate(category);
        Assert.assertEquals(origSize + 1, categoryDao.getAll().size());
        logger.debug("Successfully updated test data category");

        logger.debug("Deleting test data category");
        categoryDao.delete(category);
        Assert.assertEquals(origSize, categoryDao.getAll().size());
        logger.debug("Sucesffully deleted test data category");
    }

}
