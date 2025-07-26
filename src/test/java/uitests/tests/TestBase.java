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

public class TestBase {

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://vimeo.com/";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");
        Configuration.browserVersion = System.getProperty("browser.version");
        Configuration.timeout = 10000;

        String selenoidLogin = System.getProperty("selenoid.login");
        String selenoidPassword = System.getProperty("selenoid.password");
        String selenoidUrl = System.getProperty("selenoid.url");

        if (selenoidLogin != null && selenoidPassword != null && selenoidUrl != null) {
            Configuration.remote = String.format(
                    "https://%s:%s@%s/wd/hub",
                    selenoidLogin,
                    selenoidPassword,
                    selenoidUrl
            );
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
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
