<a href="https://vimeo.com/ "> <img src="media/logo.png" width="200" height="200"> 
<h2 >Проект по автоматизации тестирования для компании <a href="https://vimeo.com/ "> Vimeo </a></h2> 

## Содержание:

- Технологии и инструменты
- Список проверок, реализованных в тестах
- Запуск тестов (сборка в Jenkins) и из терминала
- Allure-отчет
- Уведомление в Telegram о результатах прогона тестов
- Видео пример прохождения тестов

<a id="tools"></a>
## Технологии и инструменты:

|         Java                                                                                                      | IntelliJ  <br>  Idea                                                                                               | GitHub                                                                                                     | JUnit 5                                                                                                           | Gradle                                                                                                     | Selenide                                                                                                         | Selenoid                                                                                                                  | Allure <br> Report                                                                                                         |  Jenkins                                                                                                        |   Telegram
|:----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| <a href="https://www.java.com/"><img src="media/java-svgrepo-com.svg" width="50" height="50"  alt="Java"/></a>  | <a href="https://www.jetbrains.com/idea/"><img src="media/intellij-idea-svgrepo-com.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://github.com/"><img src="media/github-badge-svgrepo-com.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="media/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="media/gradle-svgrepo-com.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://selenide.org/"><img src="media/Selenide.svg" width="50" height="50"  alt="Selenide"/></a> | <a href="https://aerokube.com/selenoid/"><img src="media/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a> | <a href="https://github.com/allure-framework"><img src="media/Allure.svg" width="50" height="50"  alt="Allure"/></a> |<a href="https://www.jenkins.io/"><img src="media/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a> | <a href="https://web.telegram.org/"><img src="media/Telegram.svg" width="50" height="50" alt="Telegram"/></a> |<a href="https://qameta.io/"><img src="images\logo\Allure_TO.svg" width="50" height="50" alt="Allure_TO"/></a> |

<a id="cases"></a>
## Реализованные проверки:
1.UI-тесты видеоплеера:
- Воспроизведение видео
- Остановка воспроизведения видео
- Перемотка вперед клавиатурой
- Перемотка назад клавиатурой
- Перемотка вперед ползунком
- Перемотка назад ползунком

## <img alt="Jenkins" height="25" src="media/Jenkins.svg" width="25"/> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/vimeo_autotests/)


<p align="center">  
<img src="media/JenkinsBuild.jpg" alt="Jenkins" width="950"/></a>  
</p>


## Параметры сборки в Jenkins:

При запуске сборки в **Jenkins** можно использовать следующие параметры:

- `BROWSER` — браузер, **по умолчанию** `chrome`
- `BROWSER_VERSION` — версия браузера, **по умолчанию** `128.0`
- `BROWSER_SIZE` — размер окна браузера, **по умолчанию** `1920x1080`
- `SELENOID_LOGIN` и `SELENOID_PASSWORD` — логин и пароль для доступа к Selenoid
- `SELENOID_URL` — адрес сервера Selenoid
- `TASK` — выбор тестов для запуска, **по умолчанию** `test`


## Команда для запуска из терминала
Локальный запуск
```bash
gradle clean test
```
Удаленный запуск через Jenkins:
```bash  
clean
${TASK}
"-Dselenoid.url=${SELENOID_URL}"
-Dselenoid.login=${SELENOID_LOGIN}
-Dselenoid.password=${SELENOID_PASSWORD}
"-Dbrowser=${BROWSER}"
"-Dbrowser.version=${BROWSER_VERSION}"
"-Dbrowser.size=${BROWSER_SIZE}"
```

## <img alt="Allure" height="25" src="media/Allure.svg" width="25"/></a>  <a name="Allure"></a>Allure [Report](https://jenkins.autotests.cloud/job/vimeo_autotests/30/allure/)	</a>


## Основная страница отчёта

<p align="center">  
<img title="Allure Overview Dashboard" src="media/AllureReport.jpg" width="850">  
</p>  

____
## <img alt="Allure" height="25" src="media/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="media/tgAllert.jpg" width="550">  
</p>

____
## <img alt="Selenoid" height="25" src="media/Selenoid.svg" width="25"/></a> Примеры видео выполнения тестов на Selenoid
____
<p align="center">
<img title="Selenoid Video" src="media/test_video.gif" width="550" height="350"  alt="video">   
</p>
