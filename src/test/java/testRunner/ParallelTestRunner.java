package testRunner;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines({"cucumber"})
@SelectClasspathResource("features")
public class ParallelTestRunner {
}
