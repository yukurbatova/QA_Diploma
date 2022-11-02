# Предусловие
На локальной машине установлен Docker.
# Шаги для запуска тестов
1. Запустить IntelliJ IDEA.
2. Клонировать репозиторий: 
   * `git clone https://github.com/yukurbatova/QA_Diploma`
3. Перейти в каталог:
   * `cd QA_Diploma`
4. Запустить контейнеры: 
   * `docker-compose up -d`
5. Запустить SUT с поддержкой MySQL:
   * `java -jar aqa-shop.jar -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`
6. Запустить тесты с поддержкой MySQL:
    * `./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app`
7. Запустить SUT с поддержкой PostgreSQL:
   * `java  "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar aqa-shop.jar`
8. Запустить тесты с поддержкой PostgreSQLSQL:
    * `./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app`
9. Сгенерировать отчет с помощью фреймворка Allure:
    * `./gradlew allureReport`
    * `./gradlew allureServe`
10. Остановить и удалить контейнеры: 
    * `docker-compose down`
