package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ProjectConfig {
    public final WebConfig webConfig;

    public ProjectConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void webConfig() {
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowser();
        Configuration.timeout = 10000;

        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();

        if (System.getProperty("selenoid.login") != null) {
            Configuration.remote = getSelenoidConfiguration();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    public String getSelenoidConfiguration() {
        return "https://"
                + webConfig.getSelenoidLogin() + ":"
                + webConfig.getSelenoidPassword() + "@"
                + webConfig.getSelenoidUrl()
                + "/wd/hub";
    }
}
