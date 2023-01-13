# Problems, which were given to solve in this project.

 1) Rewrite Project 1 using Hibernate and Spring Data JPA. There should not be any SQL query in your project. Entities (@Entity) Book and Person, repositories and services must be implemented. PersonDAO and BookDAO must be empty and must not be used, all DB work
through services. 

New features:

2) Add pagination for books. There can be many books and they may not fit on one page in the browser. To solve this problem, the controller method must be able to display not only all the books at once, but also paginate the output.
3) Add sorting books by year. The controller method must be able to serve books in sorted order.
4) Create a book search page. We enter in the field on the page the initial letters of the title of the book, we get the full title of the book and the name of the author.
5) Add an automatic check that the person has overdue the return of the book.


Old functionalities and version of project, which was written in JdbcTemplate, you can see in link below:
  https://github.com/RashadNovruzov/SpringMvc-project-with-JdbcTemplate-

# Проблемы, которые были решены в этом проекте.
  1) Переписать Проект 1 с использованием Hibernate и Spring Data JPA. В вашем проекте не должно быть ни одного SQL запроса. Должны быть реализованы сущности (@Entity) Книга и Человек, репозитории и сервисы. PersonDAO и BookDAO должны быть пустыми и не должны использоваться, вся работа с БД
через сервисы. 

Новый функционал:

 2) Добавить пагинацию для книг.Книг может быть много и они могут не помещаться на одной странице в браузере. Чтобы решить эту проблему, метод контроллера должен уметь выдавать не только все книги разом, но и разбивать выдачу на страницы.
 3) Добавить сортировку книг по году. Метод контроллера должен уметь выдавать книги в отсортированном порядке.
 4) Создать страницу поиска книг. Вводим в поле на странице начальные буквы названия книги, получаем полное название книги и имя автора. 
 5) Добавить автоматическую проверку на то, что человек просрочил возврат книги.
 
 Старый функционал и версию проекта, написанную на JdbcTemplate, вы можете увидеть по ссылке ниже:
 https://github.com/RashadNovruzov/SpringMvc-project-with-JdbcTemplate-
