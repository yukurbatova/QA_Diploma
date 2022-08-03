# Шаги для запуска тестов
1. Запустить IntelliJ IDEA
2. Клонировать репозиторий 
   * `git clone https://github.com/yukurbatova/QA_Diploma`
3. Перейти в каталог
   * `cd QA_Diploma`
4. Запустить Docker 
   * `docker-compose up -d`
5. Запустить SUT
   * `java -jar aqa-shop.jar`
6. Запустить тесты
    * `./gradlew clean test`
7. Остановить контейнеры 
    * `docker-compose down`
