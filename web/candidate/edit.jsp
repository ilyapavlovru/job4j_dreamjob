<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <title>Работа мечты</title>
</head>
<body>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<div class="row">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/posts.do">Вакансии</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/candidates.do">Кандидаты</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/post/edit.jsp">Добавить вакансию</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/candidate/edit.jsp">Добавить кандидата</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> |
                Выйти</a>
        </li>
    </ul>
</div>

<%
    String id = request.getParameter("id");
    Candidate can = new Candidate(0, "", 0);
    if (id != null) {
        can = PsqlStore.instOf().findCandidateById(Integer.valueOf(id));
    }
%>

<script>

    $(document).ready(function () {
        const el = document.getElementById('citySelector');
        let curCityId = <%=can.getCityId()%>;
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/dreamjob/cities',
            data: 'name=' + $('#exampleInputEmail1').val(),
            dataType: 'json'
        }).done(function (response) {
            response.forEach(function (arrayItem) {

                el.append(new Option(arrayItem.name, arrayItem.id));


                alert(curCityId)

            });
            el.value = curCityId;
        }).fail(function (err) {
            alert(err);
        });

    });

    function sendGreeting() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/dreamjob/cities',
            data: 'name=' + $('#exampleInputEmail1').val(),
            dataType: 'json'
        }).done(function (response) {
            response.forEach(function (arrayItem) {

                alert(arrayItem.name);
            });
        }).fail(function (err) {
            alert(err);
        });
    }
</script>

<div class="container">
    <h2 id="myHeader"></h2>
    <form>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                   placeholder="Enter email">
        </div>
        <button type="button" class="btn btn-primary" onclick="sendGreeting()">Submit</button>
    </form>
</div>

<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                Новый кандидат.
                <% } else { %>
                Редактирование кандидата.
                <% } %>
            </div>

            <div class="card-body">

                <form action="<%=request.getContextPath()%>/candidates.do?id=<%=can.getId()%>" method="post">

                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="form-control" name="name" value="<%=can.getName()%>">
                    </div>

                    <div class="form-group">
                        <label for="citySelector">Город:</label>
                        <select class="form-control" id="citySelector" name = "cityId">
                            <option value="0">Выберите город</option>
                        </select>
                    </div>

                    <input type="hidden" name="action" value="update"/>
                    <button type="submit" value="UPDATE" class="btn btn-primary">Сохранить</button>
                </form>
            </div>

        </div>
    </div>
</div>
</body>
</html>
