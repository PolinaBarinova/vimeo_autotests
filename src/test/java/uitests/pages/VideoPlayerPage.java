package uitests.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class VideoPlayerPage {

    private final SelenideElement playerContainer = $("[data-testid='vh-player-container']");
    private final SelenideElement playButton = $("[class=PlayButton_module_playButtonWrapper__d1afd73a]");
    private final SelenideElement progressBar = $("div[data-progress-bar-timecode-container='true']");
    private final SelenideElement playedBar = $(".PlayedBar_module_played__a3ef1250.ChapterSegment_module_segmentBar__611ea95b");
    private final SelenideElement seekbar = $(".FocusTarget_module_focusTarget__00a969cc.shared_module_focusable__fd03e359");

    private static final String[] VIDEO_IDS = {
            "995775930",
            "835587844",
            "815583669",
            "790153007",
            "843191452"
    };

    private String getRandomVideoId() {
        int randomIndex = (int) (Math.random() * VIDEO_IDS.length);
        return VIDEO_IDS[randomIndex];
    }

    public String videoId = getRandomVideoId();

    public VideoPlayerPage openVideo() {
        open(videoId);
        playerContainer.shouldBe(visible);
        return this;
    }

    public VideoPlayerPage playVideo() {
        playButton.click();
        return this;
    }

    public VideoPlayerPage pauseVideo() {
        playButton.click();
        return this;
    }

    public void waitForProgress(int ms) {
        sleep(ms);
    }

    public double getLeftPercent() {
        progressBar.shouldBe(visible);
        String style = progressBar.getAttribute("style");
        String value = style.replaceAll(".*left:\\s*([0-9.]+)%.*", "$1");
        return Double.parseDouble(value);
    }

    public double getPlayedWidthPercent() {
        playedBar.shouldBe(visible);
        String style = playedBar.getAttribute("style");
        String percent = style.replaceAll("[^0-9.]", "");
        return Double.parseDouble(percent);
    }

    public void seekWithKeyboard(Keys key) {
        seekbar.shouldBe(visible).scrollIntoView("{block: 'center'}");
        executeJavaScript("arguments[0].click();", seekbar);
        seekbar.sendKeys(key);
    }

    public void seekByDragOffset(int offsetX) {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.moveToElement(seekbar)
                .clickAndHold()
                .moveByOffset(offsetX, 0)
                .release()
                .perform();
    }
}
