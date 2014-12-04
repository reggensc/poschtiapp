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
import reggensc.poschtiapp.domain.ShoppingItem;
import reggensc.poschtiapp.domain.Unit;
import reggensc.poschtiapp.persistence.config.SpringApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringApplicationConfig.class })
public class ShoppingItemDaoIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingItemDaoIT.class);

    @Autowired
    private ShoppingItemDao shoppingItemDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private UnitDao unitDao;

    private ShoppingItem shoppingItem;
    private Category category;
    private Unit unit;

    @Before
    public void setUp() {
        LOGGER.debug("Creating test data");
        shoppingItem = new ShoppingItem();
        shoppingItem.setName("TestShoppingItem Name");
        shoppingItem.setDescription("TestShoppingItem Description");
        shoppingItem.setQuantity(3);

        category = new Category();
        category.setName("TestCategory");

        unit = new Unit();
        unit.setDesignator("TestUnit");

        LOGGER.debug("Test data created: {}", shoppingItem);
    }

    @Test
    @Transactional
    public void testCreateUpdateDeleteWithoutReferences() {
        LOGGER.debug("Loading initial shoppingItems");
        List<ShoppingItem> shoppingItems = shoppingItemDao.getAll();
        int origSize = shoppingItems.size();
        LOGGER.debug("Successfully loaded initial shoppingItems");

        LOGGER.debug("Saving test data shoppingItem");
        category = categoryDao.saveOrUpdate(category);
        shoppingItem.setCategory(category);
        shoppingItem = shoppingItemDao.saveOrUpdate(shoppingItem);
        Assert.assertEquals(origSize + 1, shoppingItemDao.getAll().size());
        Long id = shoppingItem.getId();
        Assert.assertNotNull(id);
        LOGGER.debug("Successfully saved test data shoppingItem  ");

        LOGGER.debug("Loading test data shoppingItem for update");
        shoppingItem = shoppingItemDao.getById(id);
        shoppingItem.setName("Changed TestShoppingItem Name");
        LOGGER.debug("Successfully loaded test data shoppingItem for update");

        LOGGER.debug("Updating test data shoppingItem");
        shoppingItem = shoppingItemDao.saveOrUpdate(shoppingItem);
        shoppingItemDao.saveOrUpdate(shoppingItem);
        Assert.assertEquals(origSize + 1, shoppingItemDao.getAll().size());
        LOGGER.debug("Successfully updated test data shoppingItem");

        LOGGER.debug("Deleting test data shoppingItem");
        shoppingItemDao.delete(shoppingItem);
        categoryDao.delete(category);
        Assert.assertEquals(origSize, shoppingItemDao.getAll().size());
        LOGGER.debug("Successfully deleted test data shoppingItem");
    }

    @Test
    @Transactional
    public void testCreateUpdateDeleteWithReferences() {
        LOGGER.debug("Loading initial shoppingItems");
        List<ShoppingItem> shoppingItems = shoppingItemDao.getAll();
        int origSize = shoppingItems.size();
        LOGGER.debug("Successfully loaded initial shoppingItems");

        LOGGER.debug("Saving test data shoppingItem");
        category = categoryDao.saveOrUpdate(category);
        shoppingItem.setCategory(category);
        unit = unitDao.saveOrUpdate(unit);
        shoppingItem.setUnit(unit);
        shoppingItem = shoppingItemDao.saveOrUpdate(shoppingItem);
        Assert.assertEquals(origSize + 1, shoppingItemDao.getAll().size());
        Long id = shoppingItem.getId();
        Assert.assertNotNull(id);
        LOGGER.debug("Successfully saved test data shoppingItem  ");

        LOGGER.debug("Loading test data shoppingItem for update");
        shoppingItem = shoppingItemDao.getById(id);
        shoppingItem.setName("Changed TestShoppingItem Name");
        LOGGER.debug("Successfully loaded test data shoppingItem for update");

        LOGGER.debug("Updating test data shoppingItem");
        shoppingItem = shoppingItemDao.saveOrUpdate(shoppingItem);
        shoppingItemDao.saveOrUpdate(shoppingItem);
        Assert.assertEquals(origSize + 1, shoppingItemDao.getAll().size());
        LOGGER.debug("Successfully updated test data shoppingItem");

        LOGGER.debug("Deleting test data shoppingItem");
        shoppingItemDao.delete(shoppingItem);
        categoryDao.delete(category);
        unitDao.delete(unit);
        Assert.assertEquals(origSize, shoppingItemDao.getAll().size());
        LOGGER.debug("Successfully deleted test data shoppingItem");
    }

}
