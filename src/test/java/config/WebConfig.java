package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:local.properties"
})

public interface WebConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://vimeo.com/")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browser.version")
    @DefaultValue("128.0")
    String getBrowserVersion();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("selenoid.login")
    @DefaultValue("${SELENOID_LOGIN}")
    String getSelenoidUser();

    @Key("selenoid.password")
    @DefaultValue("${SELENOID_PASSWORD}")
    String getSelenoidPassword();

    @Key("selenoid.url")
    @DefaultValue("${SELENOID_URL}")
    String getSelenoidUrl();
}
