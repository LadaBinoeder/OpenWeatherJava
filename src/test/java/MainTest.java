import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainTest extends BaseTest {

    private final static String BASE_URL = "https://openweathermap.org/";


    @Ignore
    @Test
    public void testURLAndTitle() throws IOException {

        final String expectedResultURL = "https://openweathermap.org/";
        final String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

        getDriver().get(BASE_URL);
        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        WebElement anyElement = getDriver().findElement(By.xpath("//*"));
        boolean pageIsNotEmpty = false;
        if(anyElement != null) {
            pageIsNotEmpty = true;
        }

        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        boolean noErrors = true;
        if(responseCode >= 400 && responseCode < 600) {
            noErrors = false;
        }

        Assert.assertEquals(actualResultURL, expectedResultURL);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertTrue(pageIsNotEmpty);
        Assert.assertTrue(noErrors);
    }
}

