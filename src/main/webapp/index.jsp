<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Menu de Opciones</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,400;0,700;1,400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            background-color: #DDD0C8;
        }

        a {
            color: #DDD0C8;
            text-decoration: none;
            font-family: 'Lato', sans-serif;
            padding: 5% 15%;
            font-weight: bold;
        }

        div {
            border-radius: 10px;
            width: 80%;
            text-align: center;
            position: relative;
            padding: 20px;
            margin: auto;
        }

        .c1 {
            width: 80%;
            border: solid 4px;
            border-color: #323232;
            padding-bottom: 30px;
        }

        h1 {
            color: #323232;
            font-family: 'Lato', sans-serif;
        }

        .boton {
            background-color: #323232;
            border-radius: 20px;
            width: 60%;
            padding: 15px;
            border: solid 4px;
            border-color: #323232;
        }
        .boton:hover {
            background-color: #DDD0C8;
        }
        .boton a:hover {
            color: #323232;
        }
    </style>
</head>
<body>
<div class="c1">
    <h1>MENU DE OPCIONES</h1>

    <c:if test="${not empty mensaje}">
        <p>${mensaje}</p>
    </c:if>

    <div>
        <div class="boton"><a href="empleados?opcion=listar"> LISTAR EMPLEADOS</a></div>
        <br>
        <div class="boton"><a href="empleados?opcion=buscarNom"> BUSCAR NOMINAS</a></div>
        <br>
        <div class="boton"><a href="empleados?opcion=buscar"> EDITAR EMPLEADOS</a></div>

    </div>
</div>

</body>
</html>