package workflows;

import configuration.RequestBase;
import constants.ProjectConstants;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class Workflow extends RequestBase {
    private Workflow(){ }

    public static Workflow of(){
        return new Workflow();
    }

    //region Flows
    public Workflow validateLatestRates() throws Exception {
        validateGeneralRates("latest");
        return this;
    }
    public Workflow validateHistoricalRates(String basePathDate) throws Exception {
        validateGeneralRates(basePathDate);
        return this;
    }
    //endregion

    //region Helpers
    private void validateGeneralRates(String basePath) throws Exception {
        Response res = defineRequestSpecification(ProjectConstants.URL, basePath).getRequestResponse();
        validateJSONSchema(res, "schemas/exchange-rates-schema.json");
        Map<String, Float> rates = getMapFromPath(res, "rates");

        List<String> currencyCodes = CommonMethods.readSingleLineCSV(ProjectConstants.CURRENT_PATH +
                "/src/test/java/data/currency-codes.csv");

        for(Map.Entry rate : rates.entrySet()){
            Assert.assertTrue(currencyCodes.contains(rate.getKey()));
        }

        Assert.assertEquals(getStringFromPath(res, "base"), "EUR");
    }
    //endregion
}
