<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agenda de Contactos</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            padding: 30px;
        }
        h1 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
            font-size: 2.5em;
        }
        .header-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            transition: all 0.3s;
            display: inline-block;
        }
        .btn-primary {
            background: #667eea;
            color: white;
        }
        .btn-primary:hover {
            background: #5568d3;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
        .btn-edit {
            background: #4CAF50;
            color: white;
            padding: 8px 16px;
            font-size: 14px;
        }
        .btn-edit:hover {
            background: #45a049;
        }
        .btn-delete {
            background: #f44336;
            color: white;
            padding: 8px 16px;
            font-size: 14px;
        }
        .btn-delete:hover {
            background: #da190b;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            font-weight: 600;
            text-transform: uppercase;
            font-size: 14px;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .badge {
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 12px;
            font-weight: bold;
        }
        .badge-masculino {
            background: #e3f2fd;
            color: #1976d2;
        }
        .badge-femenino {
            background: #fce4ec;
            color: #c2185b;
        }
        .badge-otro {
            background: #f3e5f5;
            color: #7b1fa2;
        }
        .badge-casa {
            background: #e8f5e9;
            color: #388e3c;
        }
        .badge-trabajo {
            background: #fff3e0;
            color: #f57c00;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        .no-data {
            text-align: center;
            padding: 40px;
            color: #999;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📇 Agenda de Contactos</h1>
        
        <div class="header-actions">
            <div>
                <strong>Total de contactos:</strong> ${contactos.size()}
            </div>
            <a href="${pageContext.request.contextPath}/contactos?action=nuevo" class="btn btn-primary">
                ➕ Nuevo Contacto
            </a>
        </div>

        <c:choose>
            <c:when test="${empty contactos}">
                <div class="no-data">
                    No hay contactos registrados. ¡Agrega el primero!
                </div>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre Completo</th>
                            <th>Sexo</th>
                            <th>Teléfono</th>
                            <th>Dirección</th>
                            <th>Tipo</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="contacto" items="${contactos}">
                            <tr>
                                <td>${contacto.id}</td>
                                <td><strong>${contacto.nombreCompleto}</strong></td>
                                <td>
                                    <span class="badge badge-${contacto.sexo == 'M' ? 'masculino' : contacto.sexo == 'F' ? 'femenino' : 'otro'}">
                                        ${contacto.sexo == 'M' ? 'Masculino' : contacto.sexo == 'F' ? 'Femenino' : 'Otro'}
                                    </span>
                                </td>
                                <td>${contacto.telefono}</td>
                                <td>${contacto.direccion}</td>
                                <td>
                                    <span class="badge badge-${contacto.tipoContacto == 'CASA' ? 'casa' : 'trabajo'}">
                                        ${contacto.tipoContacto}
                                    </span>
                                </td>
                                <td>
                                    <div class="actions">
                                        <a href="${pageContext.request.contextPath}/contactos?action=editar&id=${contacto.id}" 
                                           class="btn btn-edit">✏️ Editar</a>
                                        <a href="${pageContext.request.contextPath}/contactos?action=eliminar&id=${contacto.id}" 
                                           class="btn btn-delete"
                                           onclick="return confirm('¿Estás seguro de eliminar este contacto?')">
                                           🗑️ Eliminar
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>