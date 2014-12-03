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

import reggensc.poschtiapp.domain.Unit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class })
public class UnitDaoIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnitDaoIT.class);

    @Autowired
    private UnitDao unitDao;

    private Unit unit;

    @Before
    public void setUp() {
        LOGGER.debug("Creating test data");
        unit = new Unit();
        unit.setDesignator("TestUnit");
        LOGGER.debug("Test data created: {}", unit);
    }

    @Test
    @Transactional
    public void testCreateUpdateDelete() {
        LOGGER.debug("Loading initial units");
        List<Unit> units = unitDao.getAll();
        int origSize = units.size();
        LOGGER.debug("Successfully loaded initial units");

        LOGGER.debug("Saving test data unit");
        unit = unitDao.saveOrUpdate(unit);
        Assert.assertEquals(origSize + 1, unitDao.getAll().size());
        Long id = unit.getId();
        Assert.assertNotNull(id);
        LOGGER.debug("Successfully saved test data unit  ");

        LOGGER.debug("Loading test data unit for update");
        unit = unitDao.getById(id);
        unit.setDesignator("Changed TestUnit");
        LOGGER.debug("Successfully loaded test data unit for update");

        LOGGER.debug("Updating test data unit");
        unitDao.saveOrUpdate(unit);
        Assert.assertEquals(origSize + 1, unitDao.getAll().size());
        LOGGER.debug("Successfully updated test data unit");

        LOGGER.debug("Deleting test data unit");
        unitDao.delete(unit);
        Assert.assertEquals(origSize, unitDao.getAll().size());
        LOGGER.debug("Successfully deleted test data unit");
    }

}
