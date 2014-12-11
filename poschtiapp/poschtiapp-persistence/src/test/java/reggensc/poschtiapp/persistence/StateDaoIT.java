package reggensc.poschtiapp.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import reggensc.poschtiapp.domain.State;
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
public class StateDaoIT {

    private static final String DBUNIT_DATASET_LOCATION = "classpath:/dbunit/reference_db.dbunit.xml";

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

    // FIXME test validation constraints

    @Test
    @DatabaseSetup(DBUNIT_DATASET_LOCATION)
    @DatabaseTearDown(DBUNIT_DATASET_LOCATION)
    public void testCreate() throws DatabaseUnitException, SQLException, IOException {
        State state = new State();
        state.setDesignator(testIdentifier + " Test State");
        stateDao.create(state);
    }

    @Test
    @DatabaseSetup(DBUNIT_DATASET_LOCATION)
    @DatabaseTearDown(DBUNIT_DATASET_LOCATION)
    public void testDelete() {
        stateDao.deleteById(1L);
    }

}
