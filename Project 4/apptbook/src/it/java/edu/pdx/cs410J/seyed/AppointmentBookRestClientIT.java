package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.web.HttpRequestHelper.Response;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Integration test that tests the REST calls made by {@link AppointmentBookRestClient}
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppointmentBookRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private AppointmentBookRestClient newAppointmentBookRestClient() {
    int port = Integer.parseInt(PORT);
    return new AppointmentBookRestClient(HOSTNAME, port);
  }

  @Test
  public void test0RemoveAllMappings() throws IOException {
    AppointmentBookRestClient client = newAppointmentBookRestClient();
    Response response = client.removeAllMappings();
    assertThat(response.getContent(), response.getCode(), equalTo(200));
  }

  @Test
  public void test1EmptyServerContainsNoMappings() throws IOException {
    AppointmentBookRestClient client = newAppointmentBookRestClient();
    Response response = client.getAllKeysAndValues();
    String content = response.getContent();
    assertThat(content, response.getCode(), equalTo(200));
    assertThat(content, containsString(Messages.getMappingCount(0)));
  }

}
