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

import reggensc.poschtiapp.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class })
public class UserDaoIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoIT.class);

    @Autowired
    private UserDao userDao;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        LOGGER.debug("Creating test data");

        user1 = new User();
        user1.setUserId("Test userId 1");
        user1.setFirstName("Test firstName 1");
        user1.setLastName("Test lastName 1");

        user2 = new User();
        user2.setUserId("Test userId 2");
        user2.setFirstName("Test firstName 2");
        user2.setLastName("Test lastName 2");

        user3 = new User();
        user3.setUserId("Test userId 3");
        user3.setFirstName("Test firstName 3");
        user3.setLastName("Test lastName 3");

        LOGGER.debug("Test data created: {}", user1, user2, user3);
    }

    @Test
    @Transactional
    public void testCreateUpdateDeleteWithoutFriends() {
        LOGGER.debug("Loading initial users");
        List<User> users = userDao.getAll();
        int origSize = users.size();
        LOGGER.debug("Successfully loaded initial users");

        LOGGER.debug("Saving test data user");
        user1 = userDao.saveOrUpdate(user1);
        Assert.assertEquals(origSize + 1, userDao.getAll().size());
        Long id = user1.getId();
        Assert.assertNotNull(id);
        LOGGER.debug("Successfully saved test data user  ");

        LOGGER.debug("Loading test data user for update");
        user1 = userDao.getById(id);
        user1.setUserId("Changed Test userid");
        LOGGER.debug("Successfully loaded test data user for update");

        LOGGER.debug("Updating test data user");
        userDao.saveOrUpdate(user1);
        Assert.assertEquals(origSize + 1, userDao.getAll().size());
        LOGGER.debug("Successfully updated test data user");

        LOGGER.debug("Deleting test data user");
        userDao.delete(user1);
        Assert.assertEquals(origSize, userDao.getAll().size());
        LOGGER.debug("Successfully deleted test data user");
    }

    @Test
    @Transactional
    public void testCreateUpdateDeleteWithFriends() {
        LOGGER.debug("Loading initial users");
        List<User> users = userDao.getAll();
        int origSize = users.size();
        LOGGER.debug("Successfully loaded initial users");

        LOGGER.debug("Saving test data users");
        user1 = userDao.saveOrUpdate(user1);
        user2 = userDao.saveOrUpdate(user2);
        user3 = userDao.saveOrUpdate(user3);
        Assert.assertEquals(origSize + 3, userDao.getAll().size());
        Long id = user1.getId();
        Assert.assertNotNull(id);
        LOGGER.debug("Successfully saved test data users  ");

        LOGGER.debug("Loading test data user for update");
        user1 = userDao.getById(id);
        LOGGER.debug("Successfully loaded test data user for update");

        LOGGER.debug("Updating test data user");
        user1.addFriend(user2);
        user1.addFriend(user3);
        user1 = userDao.saveOrUpdate(user1);
        Assert.assertEquals(origSize + 3, userDao.getAll().size());
        LOGGER.debug("Successfully updated test data user");

        LOGGER.debug("Deleting test data user");
        userDao.delete(user1);
        userDao.delete(user2);
        userDao.delete(user3);
        Assert.assertEquals(origSize, userDao.getAll().size());
        LOGGER.debug("Successfully deleted test data user");
    }
}
