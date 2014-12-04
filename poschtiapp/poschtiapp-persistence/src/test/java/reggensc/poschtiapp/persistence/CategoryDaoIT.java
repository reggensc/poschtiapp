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
import reggensc.poschtiapp.persistence.config.SpringApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringApplicationConfig.class })
public class CategoryDaoIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateDaoIT.class);

    @Autowired
    private CategoryDao categoryDao;

    private Category category;

    @Before
    public void setUp() {
        LOGGER.debug("Creating test data");
        category = new Category();
        category.setName("TestCategory");
        category.setDescription("Description of TestCategory");
        LOGGER.debug("Test data created: {}", category);
    }

    @Test
    @Transactional
    public void testCreateUpdateDelete() {
        LOGGER.debug("Loading initial categories");
        List<Category> categories = categoryDao.getAll();
        int origSize = categories.size();
        LOGGER.debug("Successfully loaded initial categories");

        LOGGER.debug("Saving test data category");
        category = categoryDao.saveOrUpdate(category);
        Assert.assertEquals(origSize + 1, categoryDao.getAll().size());
        Long id = category.getId();
        Assert.assertNotNull(id);
        LOGGER.debug("Successfully saved test data category  ");

        LOGGER.debug("Loading test data category for update");
        category = categoryDao.getById(id);
        category.setName("Changed TestCategory");
        category.setDescription("Changed description of TestCategory");
        LOGGER.debug("Successfully loaded test data category for update");

        LOGGER.debug("Updating test data category");
        category = categoryDao.saveOrUpdate(category);
        categoryDao.saveOrUpdate(category);
        Assert.assertEquals(origSize + 1, categoryDao.getAll().size());
        LOGGER.debug("Successfully updated test data category");

        LOGGER.debug("Deleting test data category");
        categoryDao.delete(category);
        Assert.assertEquals(origSize, categoryDao.getAll().size());
        LOGGER.debug("Successfully deleted test data category");
    }

}
