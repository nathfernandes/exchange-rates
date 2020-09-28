package scenarios;

import configuration.TestBase;
import org.testng.annotations.Test;
import workflows.Workflow;

public class ExchangeRatesTests extends TestBase {
    public ExchangeRatesTests(String test) {
        super(test);
    }

    @Test
    public void validateLatestRates() throws Exception {
        Workflow.of()
                .validateLatestRates();
    }

    @Test
    public void validateHistoricalRates() throws Exception {
        Workflow.of()
                .validateHistoricalRates("2010-01-12");
    }
}
