<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Editar Empleado</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,400;0,700;1,400&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            background-color: #DDD0C8;
        }

        div {
            border: solid 3px;
            border-color: #323232;
            border-radius: 10px;
            width: 80%;
            text-align: center;
            position: relative;
            padding: 20px;
            margin: auto;
        }

        table {
            margin: auto;
            border: solid 2px;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        td {
            padding: 10px;
            color: #323232;
            font-family: 'Lato', sans-serif;
            font-weight: bold;
            border: solid 2px;
            border-color: #323232;
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

        input[type="submit"] {
            background-color: #323232;
            color: #DDD0C8;
            border: 2px solid #323232;
            font-family: 'Lato', sans-serif;
            font-weight: bold;
            padding: 10px 25px;
            border-radius: 20px;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-bottom: 8px;
        }

        input[type="submit"]:hover {
            background-color: #DDD0C8;
            color: #323232;
        }

        button[type="submit"] {
            background-color: #323232;
            color: #DDD0C8;
            border: 2px solid #323232;
            font-family: 'Lato', sans-serif;
            font-weight: bold;
            padding: 10px 25px;
            border-radius: 20px;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-bottom: 8px;
        }

        button[type="submit"]:hover {
            background-color: #DDD0C8;
            color: #323232;
        }

        input[type="text"] {
            background-color: #DDD0C8;
            color: #323232;
            font-family: 'Lato', sans-serif;
            font-weight: bold;
        }

        select {
            background-color: #DDD0C8;
            color: #323232;
            font-family: 'Lato', sans-serif;
            font-weight: bold;
            border: 2px solid #323232;
            border-radius: 5px;
            padding: 5px 12px;
            appearance: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            cursor: pointer;
        }

        option {
            background-color: #DDD0C8;
            color: #323232;

            font-family: 'Lato', sans-serif;
            font-weight: bold;
        }

        option:hover {
            background-color: #323232;
            color: #DDD0C8;
        }
    </style>
</head>
<body>
<div class="c1">
    <h1>EDITAR EMPLEADO</h1>
    <form action="empleados" method="post">
        <c:set var="empleado" value="${empleado}"></c:set>
        <input type="hidden" name="opcion" value="editar">
        <input type="hidden" name="dni" value="${empleado.dni}">
        <table border="1">
            <tr>
                <td>Nombre:</td>
                <td><input type="text" name="nombre" size="50" value="${empleado.nombre}"></td>
            </tr>
            <tr>
                <td>Sexo:</td>
                <td><input type="text" name="sexo" size="50" value="${empleado.sexo}"></td>
            </tr>
            <tr>
                <td>Categoria:</td>
                <td><input type="text" name="categoria" size="50" value="${empleado.categoria}"></td>
            </tr>
            <tr>
                <td>Anyos:</td>
                <td><input type="text" name="anyos" size="50" value="${empleado.anyos}"></td>
            </tr>
        </table>
        <input type="submit" value="Guardar">
    </form>

    <form action="empleados" method="post">
        <button type="submit" name="opcion" value="PaginaAnterior">Pagina Anterior</button>
    </form>

    <form action="empleados" method="post">
        <input type="submit" name="opcion" value="Volver">
    </form>
</div>
</body>
</html>