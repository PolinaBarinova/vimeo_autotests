package uitests.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.UUID;

public class TestBase {

    private static final String SELENOID_URL = System.getProperty("selenoid.url");
    private static final String SELENOID_LOGIN = System.getProperty("selenoid.login");
    private static final String SELENOID_PASSWORD = System.getProperty("selenoid.password");

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://vimeo.com/";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");
        Configuration.browserVersion = System.getProperty("browser.version");
        Configuration.timeout = 2000;
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "name", "Test: " + UUID.randomUUID()
        ));

        if (SELENOID_LOGIN != null && SELENOID_PASSWORD != null && SELENOID_URL != null) {
            Configuration.remote = "https://" + SELENOID_LOGIN + ":" + SELENOID_PASSWORD + "@" + SELENOID_URL + "/wd/hub";
        }

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
