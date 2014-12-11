package reggensc.poschtiapp.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.validation.ConstraintViolationException;

import org.dbunit.DatabaseUnitException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import reggensc.poschtiapp.domain.ShoppingList;
import reggensc.poschtiapp.domain.State;
import reggensc.poschtiapp.domain.User;
import reggensc.poschtiapp.persistence.config.SpringApplicationConfig;
import reggensc.poschtiapp.testutils.SpringTestDbUnitFlatXmlDataSetLoader;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringApplicationConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = SpringTestDbUnitFlatXmlDataSetLoader.class)
public class ShoppingListDaoIT {

    private static final String DBUNIT_DATASET_LOCATION = "classpath:/dbunit/reference_db.dbunit.xml";
    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListDaoIT.class);

    @Autowired
    private ShoppingListDao shoppingListDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private StateDao stateDao;

    private static Random random;
    private Long testIdentifier;

    @BeforeClass
    public static void setUpBeforeClass() {
        random = new Random();
    }

    @Before
    public void setUp() {
        testIdentifier = random.nextLong();
    }

    // TODO test validation constraints

    @Test(expected = ConstraintViolationException.class)
    @DatabaseSetup(DBUNIT_DATASET_LOCATION)
    @DatabaseTearDown(DBUNIT_DATASET_LOCATION)
    public void testCreateWithoutState() {
        User user = userDao.getById(2L);
        Assert.assertNotNull(user);

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(testIdentifier + " Test ShoppingList");
        shoppingList.addOwner(user);

        shoppingListDao.create(shoppingList);
    }

    @Test(expected = ConstraintViolationException.class)
    @DatabaseSetup(DBUNIT_DATASET_LOCATION)
    @DatabaseTearDown(DBUNIT_DATASET_LOCATION)
    public void testCreateWithoutOwner() {
        State state = stateDao.getById(2L);
        Assert.assertNotNull(state);

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(testIdentifier + " Test ShoppingList");
        shoppingList.setState(state);

        shoppingListDao.create(shoppingList);
    }

    @Test
    @DatabaseSetup(DBUNIT_DATASET_LOCATION)
    @DatabaseTearDown(DBUNIT_DATASET_LOCATION)
    public void testCreate() throws DatabaseUnitException, SQLException, IOException {
        User user = userDao.getById(2L);
        Assert.assertNotNull(user);
        State state = stateDao.getById(2L);
        Assert.assertNotNull(state);

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(testIdentifier + " Test ShoppingList");
        shoppingList.setState(state);
        shoppingList.addOwner(user);

        // TODO test with categories

        shoppingListDao.create(shoppingList);
    }

    @Test
    @DatabaseSetup(DBUNIT_DATASET_LOCATION)
    @DatabaseTearDown(DBUNIT_DATASET_LOCATION)
    public void testDelete() {
        shoppingListDao.deleteById(1L);
    }

    @Test
    @DatabaseSetup(DBUNIT_DATASET_LOCATION)
    @DatabaseTearDown(DBUNIT_DATASET_LOCATION)
    public void testLoadByOwnerEmail() {
        User user = userDao.getById(2L);
        Assert.assertNotNull(user);

        List<ShoppingList> resultSet = shoppingListDao.getByOwnerEmail(user.getEmail());
        Assert.assertTrue("No shopping lists found for user", resultSet.size() >= 1);
        LOGGER.debug("Result of loadByOwnerEmail with user {}: {}", user.getEmail(), resultSet);
    }
}
