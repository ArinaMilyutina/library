# ******Система управления библиотекоой******

Система управления библиотекой предназначена для управления информациекй о книгах и их авторах. Система позволяет
добавлять, обновлять, удалять и просматривать информацию о книгах и авторах.

**Проект использует следующие зависимости:** Spring Boot Starter Data JPA,Spring Boot Starter Data REST,Spring Boot Starter
Validation,Spring Boot Starter Web,Spring Boot Devtools,PostgreSQL,Lombok,Spring Boot Starter Test.

**Порядок запуска приложения:**
1. Откройте приложение Intellij IDEA
2. Клонируйте проект: git clone <URL-репозитория>
3. Перейдите в корневую директорию проекта: cd <имя_проекта>
4. Перейдите на ветку master, так как проект находится там:git checkout master
5. Запустите проект.
6. Приложение запустится на стандартном порту 8080.

#    **Коллекция запросов для тестирования REST API (Postman)**

1. Создание автора (метод:POST, URL:http://localhost:8080/api/authors):
   json:
   {
   "name": "John",
   "surname": "Doe",
   "birthDate": "2004-12-12"
   }
2. Поиск всех авторов (меиод: GET, URL:http://localhost:8080/api/authors)
3. Поиск автора по id (метод: GET, URL:http://localhost:8080/api/authors/{id})
4. Обновление информации об авторе по id (метод: PUT, URL:http://localhost:8080/api/authors/{id})
   json:
   {
   "name": "Arina",
   "surname": "Milyutina",
   "birthDate": "2004-01-22",
   "bookList":[
   {
   "title":"Love",
   "genre":"Romantic",
   "publicationDate":"2024-12-12"
   }
   ]
   }
5. Удаление автора по id (метод: DELETE, URL:http://localhost:8080/api/authors/{id})
6. Создание книги по id автора (метод: POST, URL:http://localhost:8080/api/books)
   json:
   {
   "title":"Love",
   "genre":"Romantic",
   "publicationDate":"2024-12-12",
   "authorId":4
   }
7. Поиск всех книг (метод: GET, URL:http://localhost:8080/api/books)
8. Поиск книги по id (метод: GET, URL: http://localhost:8080/api/authors/{id})
9. Обновление информации о книге по id (метод: PUT, URL:http://localhost:8080/api/books/{id})
   json:
   {
   "title":"Love",
   "genre":"Romantic",
   "publicationDate":"2024-12-12",
   "authorDto":
   {
   "name":"Sasha",
   "surname":"Milyutin",
   "birthDate":"1996-03-27"
   }
   }
10. Удаление книги по id (метод: DELETE, URL:http://localhost:8080/api/books/{id})
11. Поиск книг по id автора (метод: GET, URL:URL:http://localhost:8080/api/books/author/{id})


