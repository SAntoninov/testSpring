# testSpring
Test spring boot project

Пока что основное из того что написано в классе-контроллере. В остальном обычный sprigboot проект сгенерированный через spring initializr.

mvn package - собирает jar
java -jar {path to jar}\demo1-0.1.jar - старт приложения на 8080 порту
Открываем в браузере localhost:8080, запускается маппинг контроллера. Входные данные достаются из resources и помещаются в массив.
Далее через restTemplate запрашиваются актуальные данные, пересчитываются и формируется ответ на страницу.
