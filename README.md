# EducationKafka
EducationKafka - это учебный проект на Java, созданный для демонстрации работы распределенной системы обмена сообщениями между серверными приложениями Apache Kafka. В основе лежит микросервисное приложение с использованием Spring Cloud.  

Сервис `Discovery`  автоматизирует механизм обнаружения друг другом микросервисов, также здесь подключен Config Server, обеспечивающий внешнюю конфигурацию.  

Сервис `Gateway` представляет собой маршрутизатор API-запросов, который получает на вход все запросы клиента, обрабатывает и рассылает разным сервисам, а после этого отсылает ответ обратно клиенту.  

Сервис `Metrics-producer`основан на REST-сервисе калькулятора ЕГЭ  для вуза, который позволяет получить список доступных направлений для поступления в вуз по введенным результатам ЕГЭ.  
![getSpecialities](https://github.com/OksanaBuivarenko/EducationKafka/assets/144807983/b9207421-75cd-42ba-84b9-37d2ca712c0f)

Также здесь реализован класс `MetricsService`, который получает заданный в конфигурационном файле `metrics-producer-local.yml` набор метрик из `Spring Boot Actuator` и  отправляет их в Kafka топик "metrics-topic". Отправка метрик в брокер сообщений происходит каждые 5 минут автоматически при помощи планировщика задач, либо при обращении по API к `POST/producer/metrics`.  

Сервис `Metrics-consumer`принимает метрики из Kafka топика "metrics-topic" и сохраняет их в базу данных для последующего анализа. При обращении по API можно получить список всех метрик, конкретную метрику по ее идентификатору, список метрик по тегу, дате, тегу и дате вместе.
![getMetrics](https://github.com/OksanaBuivarenko/EducationKafka/assets/144807983/9a7fca5d-aad9-46b4-8f11-3696f8f0686a)

`ExceptionHandlerAspect` при помощи АОП логирует ошибки в сервисах `Metrics-producer` и `Metrics-consumer`.  
Использование АОП в можно отключить, поменяв значение `@ConditionalOnProperty(name = "spring.aspect.enabled", havingValue = "true")` в конфигурационном файле `false`.  
Планировщик задач также можно отключить, поменяв значение `@ConditionalOnProperty(name = "scheduler.enable", matchIfMissing = true)` в конфигурационном файле `false`.

## Начало работы
1. Установите на свой компьютер [JDK](https://www.oracle.com/cis/java/technologies/downloads/) и среду разработки [IntelliJ IDEA](https://www.jetbrains.com/ru-ru/idea/download/?section=windows), если они ещё не установлены.
2. Загрузите проект-заготовку из Git-репозитория.
3. Запустите базу данных Postgres и Apache Kafka в [Docker](https://www.docker.com/products/docker-desktop/), выполнив в терминале команду `docker compose up`.
4. Запустите сервис внешних настроек и обнаружения сервисов - `Discovery`.
5. Запустите API шлюз - `Gateway`.
6. Запустите сервисы `Metrics-producer` и `Metrics-consumer`.

После запуска всех сервисов документацию по API можно увидеть в [Swagger](http://localhost:8765/webjars/swagger-ui/index.html).  
Для тестирования REST-API можно использовать [Postman](https://www.postman.com/downloads/).
