<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Documentos 0.1 - Busqueda avanzada</title>
</head>
<body>
	<a id="anchorForm"></a>
	<ul class="breadcrumb">
		<li><a href="#">Busqueda</a><span class="divider"></span></li>
		<li><a href="#">Busqueda avanzada</a></li>
	</ul>
	<h2>
		<strong>Busqueda avanzada</strong>
	</h2>

	<g:if test="${flash.message}">
		<div class="alert alert-info">
			<span class="glyphicon glyphicon-info-sign"></span>
			${flash.message}
		</div>
	</g:if>
	<g:if test="${flash.errorMessage}">
		<div class="alert alert-danger">
			<span class="glyphicon glyphicon-info-sign"></span>
			${flash.errorMessage}
		</div>
	</g:if>

	<form  action="show" method="post"  id="frmBuscar">
		<g:textField name="busqueda" style="width: 30%" value="${busqueda}" />
		<button id="btnDescargar" type="button" onclick="$('#frmBuscar').submit()"
			class="btn btn-default btn-xs">
			<span class="glyphicon glyphicon-search"></span><span
				class="hidden-xs hidden-sm" style="font-size: x-small;">
				Buscar</span>
		</button>

		<br />
		<g:radio id="rdoUUID" name="opcion" value="1" checked="${opcion.equals(1)}" />
		<label for="rdoUUID">Por UUID</label>
		<br />
		<g:radio id="rdoMatricula" name="opcion" value="2" checked="${opcion.equals(2)}" />
		<label for="rdoMatricula">Por matrícula</label>
		<br />
		<g:radio id="rdoNombre" name="opcion" value="3" checked="${opcion.equals(3)}"/>
		<label for="rdoNombre">Por nombre de archivo</label>
		<br />
		<g:radio id="rdoDescripcion" name="opcion" value="4" checked="${opcion.equals(4)}"/>
		<label for="rdoDescripcion">Por alguna descripción</label>
	</form>
</body>
</html>