# Task - тестовое задание

# Задача
Форма регистрации с отправкой eмейла после одобрения из внешней системы.
Дана форма регистрации в нашем приложении, в которой необходимо заполнить:
- логин,
- пароль,
- адрес электронной почты,
- ФИО. 
  После отправки формы, мы регистрируем данные из нее в нашей БД, а также отправляем ее для одобрения во внешней системе. Обмен с этой внешней системой осуществляется через messaging решение. После одобрения или отклонения заявки, приложение отправляет сообщение на электронную почту нашему пользователю с результатом проверки.

## Для разработчика
### Конфигурация
В корне проекта находится файл `application.properties` в котором указан пример конфигурации для запуска.
В файле указаны параметры подключения к БД, RabbitMQ исходя из docker-compose. 

### Полезные инструменты
В проекте есть support/docker-compose.yaml который поднимет необходимые зависимости (RabbitMQ)

### Обязательная конфигурация
Любой конфиг может быть задан не только в файле для конфигурации, но и в переменных окружениях.
```yaml
server.port = 8181

spring.profiles.active=dev

# Настройка подключения к БД
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=

spring.h2.console.enabled=true

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto = update

# Настройка подключения к RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

logging.level.ru.max.task=DEBUG
```
