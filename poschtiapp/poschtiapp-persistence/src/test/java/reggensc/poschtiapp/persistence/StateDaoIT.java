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

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StateDao stateDao;

    private State state;

    @Before
    public void setUp() {
        logger.debug("Creating test data");
        state = new State();
        state.setStateName("TestState");
        logger.debug("Test data created: {}", state);
    }

    @Test
    @Transactional
    public void testCreateUpdateDelete() {
        logger.debug("Loading initial states");
        List<State> states = stateDao.getAll();
        int origSize = states.size();
        logger.debug("Successfully loaded initial states");

        logger.debug("Saving test data state");
        state = stateDao.saveOrUpdate(state);
        Assert.assertEquals(origSize + 1, stateDao.getAll().size());
        Long id = state.getId();
        Assert.assertNotNull(id);
        logger.debug("Successfully saved test data state  ");

        logger.debug("Loading test data state for update");
        state = stateDao.getById(id);
        state.setStateName("Changed TestState");
        logger.debug("Successfully loaded test data state for update");

        logger.debug("Updating test data state");
        stateDao.saveOrUpdate(state);
        Assert.assertEquals(origSize + 1, stateDao.getAll().size());
        logger.debug("Successfully updated test data state");

        logger.debug("Deleting test data state");
        stateDao.delete(state);
        Assert.assertEquals(origSize, stateDao.getAll().size());
        logger.debug("Sucesffully deleted test data state");
    }

}
