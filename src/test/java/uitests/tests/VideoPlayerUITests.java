package uitests.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import uitests.pages.VideoPlayerPage;

import static io.qameta.allure.Allure.step;


@Owner("p.barinova")
@DisplayName("Проверки видеоплеера")
public class VideoPlayerUITests extends TestBase {

    VideoPlayerPage videoPlayerPage = new VideoPlayerPage();

    @Test
    @DisplayName("Воспроизведение видео")
    void videoPlayingTest() {
        videoPlayerPage.openVideo()
                .playVideo();
        double before = step("Получить текущий прогресс воспроизведения", () ->
                videoPlayerPage.getLeftPercent()
        );
        videoPlayerPage.waitForProgress(5000);
        double after = step("Получить прогресс воспроизведения после ожидания", () ->
                videoPlayerPage.getLeftPercent()
        );
        step("Проверить, что видео воспроизводится (прогресс увеличился)", () -> {
            assert after > before : "Видео не воспроизводится";
        });
    }

    @Test
    @DisplayName("Остановка воспроизведения видео")
    void videoPausedTest() {
        videoPlayerPage.openVideo()
                .playVideo()
                .waitForProgress(3000);
        videoPlayerPage.pauseVideo();
        double atPause = step("Получить текущий прогресс воспроизведения", () ->
                videoPlayerPage.getLeftPercent()
        );
        videoPlayerPage.waitForProgress(5000);
        double afterPause = step("Получить прогресс воспроизведения после ожидания", () ->
                videoPlayerPage.getLeftPercent()
        );
        step("Проверить, что видео остановлено (прогресс не увеличился)", () -> {
            assert afterPause <= atPause : "Видео не остановилось"; //"<=" вместо "==" стоит из-за возможной погрешности
        });
    }

    @Test
    @DisplayName("Перемотка вперед клавиатурой")
    void seekForwardKeyboardTest() {
        videoPlayerPage.openVideo()
                .playVideo()
                .waitForProgress(3000);
        double before = step("Получить прогресс просмотра до перемотки", () ->
                videoPlayerPage.getPlayedWidthPercent()
        );
        step("Перемотать видео вперед с клавиатуры", () ->
                videoPlayerPage.seekWithKeyboard(Keys.ARROW_RIGHT)
        );
        double after = step("Получить прогресс просмотра после перемотки", () ->
                videoPlayerPage.getPlayedWidthPercent()
        );
        step("Проверить, что перемотка сработала", () -> {
            assert after > before : "Перемотка вперед не сработала";
        });
    }

    @Test
    @DisplayName("Перемотка назад клавиатурой")
    void seekBackwardKeyboardTest() {
        videoPlayerPage.openVideo()
                .playVideo()
                .waitForProgress(3000);
        double before = step("Получить прогресс просмотра до перемотки", () ->
                videoPlayerPage.getPlayedWidthPercent()
        );
        step("Перемотать видео назад с клавиатуры", () ->
                videoPlayerPage.seekWithKeyboard(Keys.ARROW_LEFT)
        );
        double after = step("Получить прогресс просмотра после перемотки", () ->
                videoPlayerPage.getPlayedWidthPercent()
        );
        step("Проверить, что перемотка сработала", () -> {
            assert after < before : "Перемотка назад не сработала";
        });
    }

    @Test
    @DisplayName("Перемотка вперед ползунком")
    void seekForwardDragTest() {
        videoPlayerPage.openVideo()
                .playVideo()
                .waitForProgress(3000);
        double before = step("Получить прогресс просмотра до перемотки", () ->
                videoPlayerPage.getPlayedWidthPercent()
        );
        step("Перемотать видео вперед ползунком", () ->
                videoPlayerPage.seekByDragOffset(100)
        );
        double after = step("Получить прогресс просмотра после перемотки", () ->
                videoPlayerPage.getPlayedWidthPercent()
        );
        step("Проверить, что перемотка сработала", () -> {
            assert after > before : "Перемотка вперед не сработала";
        });
    }

    @Test
    @DisplayName("Перемотка назад ползунком")
    void seekBackwardDragTest() {
        videoPlayerPage.openVideo()
                .playVideo()
                .waitForProgress(3000);
        step("Перемотать видео вперед", () ->
                videoPlayerPage.seekByDragOffset(100)
        );
        double before = step("Получить прогресс просмотра до перемотки", () ->
                videoPlayerPage.getPlayedWidthPercent()
        );
        step("Перемотать видео назад ползунком", () ->
                videoPlayerPage.seekByDragOffset(-50)
        );
        double after = step("Получить прогресс просмотра после перемотки", () ->
                videoPlayerPage.getPlayedWidthPercent()
        );
        step("Проверить, что перемотка сработала", () -> {
            assert after < before : "Перемотка назад не сработала";
        });
    }

    @Test
    @DisplayName("Переключение в полноэкранный режим и выход из него")
    void toggleFullscreenTest() {
        videoPlayerPage.openVideo()
                .playVideo()
                .enterFullscreen();

        step("Проверить, что плеер в полноэкранном режиме", () -> {
            assert videoPlayerPage.isFullscreen() : "Плеер не перешел в полноэкранный режим";
        });
        videoPlayerPage.exitFullscreen();
        step("Проверить, что плеер вышел из полноэкранного режима", () -> {
            assert !videoPlayerPage.isFullscreen() : "Плеер не вышел из полноэкранного режима";
        });
    }
}

