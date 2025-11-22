<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${contacto != null ? 'Editar' : 'Nuevo'} Contacto</title>
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
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            max-width: 600px;
            width: 100%;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            padding: 40px;
        }
        h1 {
            color: #333;
            margin-bottom: 30px;
            text-align: center;
            font-size: 2em;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: 600;
        }
        input[type="text"],
        select,
        textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 16px;
            transition: border-color 0.3s;
        }
        input[type="text"]:focus,
        select:focus,
        textarea:focus {
            outline: none;
            border-color: #667eea;
        }
        textarea {
            resize: vertical;
            min-height: 80px;
        }
        .radio-group {
            display: flex;
            gap: 20px;
        }
        .radio-option {
            display: flex;
            align-items: center;
            gap: 8px;
        }
        input[type="radio"] {
            width: 20px;
            height: 20px;
            cursor: pointer;
        }
        .buttons {
            display: flex;
            gap: 15px;
            margin-top: 30px;
        }
        .btn {
            flex: 1;
            padding: 14px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 600;
            text-decoration: none;
            text-align: center;
            transition: all 0.3s;
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
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background: #5a6268;
        }
        .required {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${contacto != null ? '✏️ Editar' : '➕ Nuevo'} Contacto</h1>
        
        <form action="${pageContext.request.contextPath}/contactos" method="post">
            <input type="hidden" name="action" value="${contacto != null ? 'actualizar' : 'guardar'}">
            <c:if test="${contacto != null}">
                <input type="hidden" name="id" value="${contacto.id}">
            </c:if>

            <div class="form-group">
                <label>Nombre <span class="required">*</span></label>
                <input type="text" name="nombre" value="${contacto != null ? contacto.nombre : ''}" required>
            </div>

            <div class="form-group">
                <label>Apellido Paterno <span class="required">*</span></label>
                <input type="text" name="apellidoPaterno" value="${contacto != null ? contacto.apellidoPaterno : ''}" required>
            </div>

            <div class="form-group">
                <label>Apellido Materno</label>
                <input type="text" name="apellidoMaterno" value="${contacto != null ? contacto.apellidoMaterno : ''}">
            </div>

            <div class="form-group">
                <label>Sexo <span class="required">*</span></label>
                <div class="radio-group">
                    <div class="radio-option">
                        <input type="radio" id="sexoM" name="sexo" value="M" 
                               ${contacto == null || contacto.sexo == 'M' ? 'checked' : ''} required>
                        <label for="sexoM">Masculino</label>
                    </div>
                    <div class="radio-option">
                        <input type="radio" id="sexoF" name="sexo" value="F" 
                               ${contacto != null && contacto.sexo == 'F' ? 'checked' : ''}>
                        <label for="sexoF">Femenino</label>
                    </div>
                    <div class="radio-option">
                        <input type="radio" id="sexoO" name="sexo" value="O" 
                               ${contacto != null && contacto.sexo == 'O' ? 'checked' : ''}>
                        <label for="sexoO">Otro</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label>Teléfono <span class="required">*</span></label>
                <input type="text" name="telefono" value="${contacto != null ? contacto.telefono : ''}" 
                       placeholder="8112345678" required>
            </div>

            <div class="form-group">
                <label>Dirección</label>
                <textarea name="direccion">${contacto != null ? contacto.direccion : ''}</textarea>
            </div>

            <div class="form-group">
                <label>Tipo de Contacto <span class="required">*</span></label>
                <select name="tipoContacto" required>
                    <option value="CASA" ${contacto == null || contacto.tipoContacto == 'CASA' ? 'selected' : ''}>
                        🏠 Casa
                    </option>
                    <option value="TRABAJO" ${contacto != null && contacto.tipoContacto == 'TRABAJO' ? 'selected' : ''}>
                        💼 Trabajo
                    </option>
                </select>
            </div>

            <div class="buttons">
                <button type="submit" class="btn btn-primary">
                    💾 Guardar
                </button>
                <a href="${pageContext.request.contextPath}/contactos" class="btn btn-secondary">
                    ❌ Cancelar
                </a>
            </div>
        </form>
    </div>
</body>
</html>