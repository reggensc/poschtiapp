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

import reggensc.poschtiapp.domain.State;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class })
public class StateDaoIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateDaoIT.class);

    @Autowired
    private StateDao stateDao;

    private State state;

    @Before
    public void setUp() {
        LOGGER.debug("Creating test data");
        state = new State();
        state.setDesignator("TestState");
        LOGGER.debug("Test data created: {}", state);
    }

    @Test
    @Transactional
    public void testCreateUpdateDelete() {
        LOGGER.debug("Loading initial states");
        List<State> states = stateDao.getAll();
        int origSize = states.size();
        LOGGER.debug("Successfully loaded initial states");

        LOGGER.debug("Saving test data state");
        state = stateDao.saveOrUpdate(state);
        Assert.assertEquals(origSize + 1, stateDao.getAll().size());
        Long id = state.getId();
        Assert.assertNotNull(id);
        LOGGER.debug("Successfully saved test data state  ");

        LOGGER.debug("Loading test data state for update");
        state = stateDao.getById(id);
        state.setDesignator("Changed TestState");
        LOGGER.debug("Successfully loaded test data state for update");

        LOGGER.debug("Updating test data state");
        state = stateDao.saveOrUpdate(state);
        Assert.assertEquals(origSize + 1, stateDao.getAll().size());
        LOGGER.debug("Successfully updated test data state");

        LOGGER.debug("Deleting test data state");
        stateDao.delete(state);
        Assert.assertEquals(origSize, stateDao.getAll().size());
        LOGGER.debug("Successfully deleted test data state");
    }

}
