package cucumber;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class StepDefinition {
    private HttpGet getRequest(String zone) {
        return new HttpGet("http://localhost:8080/SightAPI?zone=" + zone);
    }

    private CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    private String qidu() {
        return "\"sightName\":\"七堵鐵道紀念公園(舊七堵前站)\"";
    }

    private String zhongshan() {
        return "\"sightName\":\"仙洞巖\"";
    }

    private String zhongzheng() {
        return "\"sightName\":\"二沙灣砲台(海門天險)\"";
    }

    private String renai() {
        return "\"sightName\":\"海洋廣場\"";
    }

    private String anle() {
        return "\"sightName\":\"一太e衛浴觀光工廠\"";
    }

    private String sinyi() {
        return "\"sightName\":\"基中正公園\"";
    }

    private String nuannuan() {
        return "\"sightName\":\"暖東峽谷\"";
    }

    @When("users want to get information on the {string} sight")
    public void usersGetInformationOnASight(String zone) throws IOException {
        HttpResponse httpResponse = getHttpClient().execute(getRequest(zone));
        String responseString = convertResponseToString(httpResponse);
        if (Objects.equals(zone, "七堵")) {
            assertThat(responseString, containsString(qidu()));
        } else if (Objects.equals(zone, "中山")) {
            assertThat(responseString, containsString(zhongshan()));
        } else if (Objects.equals(zone, "中正")) {
            assertThat(responseString, containsString(zhongzheng()));
        } else if (Objects.equals(zone, "仁愛")) {
            assertThat(responseString, containsString(renai()));
        } else if (Objects.equals(zone, "安樂")) {
            assertThat(responseString, containsString(anle()));
        } else if (Objects.equals(zone, "信義")) {
            assertThat(responseString, containsString(sinyi()));
        } else if (Objects.equals(zone, "暖暖")) {
            //Assert.assertTrue(responseString.contains(nuannuan()));
            assertThat(responseString, containsString(nuannuan()));
        } else {
            System.out.println("Not Found");
        }
    }

    @Then("the \"(.+)\" response status should be \"(.*?)\"$")
    public void theRequestedDataIsReturned(String zone, int value) throws IOException {
        StatusLine httpResponse = getHttpClient().execute(getRequest(zone)).getStatusLine();
        int responseStatus = httpResponse.getStatusCode();
        assertEquals(value, responseStatus);
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, String.valueOf(StandardCharsets.UTF_8));
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }
}
