package reggensc.poschtiapp.testutils;

import java.net.URL;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

public class SpringTestDbUnitFlatXmlDataSetLoader extends AbstractDataSetLoader {

    @Override
    protected IDataSet createDataSet(Resource resource) throws Exception {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        URL url = resource.getURL();
        return builder.build(url);
    }

}
