package org.dbunit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Integration stress test for PrepAndExpectedTestCase.
 *
 * @author Jeff Jensen jeffjensen AT users.sourceforge.net
 */
@RunWith(Parameterized.class)
public class DefaultPrepAndExpectedTestCaseExceedLimitsIT
{
    private static final int ITERATION_COUNT = 1000;

    private static final String PREP_DATA_FILE_NAME =
            "/xml/flatXmlDataSetTest.xml";

    private final DataFileLoader dataFileLoader = new FlatXmlDataFileLoader();

    private DatabaseEnvironment dbEnv;
    private IDatabaseConnection connection;
    private IDatabaseTester databaseTester;

    private DefaultPrepAndExpectedTestCase tc;

    @Parameters(name = "test permutation {index}")
    public static Collection<Object[]> data()
    {
        final List<Object[]> list = new ArrayList<Object[]>(ITERATION_COUNT);

        for (int i = 0; i < ITERATION_COUNT; i++)
        {
            final Integer[] array = {i};
            list.add(array);
        }

        return list;
    }

    public DefaultPrepAndExpectedTestCaseExceedLimitsIT(final int testNumber)
    {
    }

    @Before
    public void setUp() throws Exception
    {
        dbEnv = DatabaseEnvironment.getInstance();
        connection = dbEnv.getConnection();
        databaseTester = new DefaultDatabaseTester(connection);

        tc = new DefaultPrepAndExpectedTestCase(dataFileLoader, databaseTester);
    }

    @Test
    public void testTooManyOpenCursors() throws Exception
    {
        final VerifyTableDefinition[] verifyTables = {};
        final String[] prepDataFiles = {PREP_DATA_FILE_NAME};
        final String[] expectedDataFiles = {};

        final PrepAndExpectedTestCaseSteps testSteps =
                new PrepAndExpectedTestCaseSteps()
                {
                    public Object run() throws Exception
                    {
                        return null;
                    }
                };

        tc.runTest(verifyTables, prepDataFiles, expectedDataFiles, testSteps);
    }
}
