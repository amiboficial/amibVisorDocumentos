<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Documentos 0.1 - Todos los documentos</title>
</head>
<body>
	<a id="anchorForm"></a>
	<ul class="breadcrumb">
		<li><a href="#">Busqueda</a><span class="divider"></span></li>
		<li><a href="<g:createLink controller="busqueda" action="index" />">Busqueda avanzada</a></li>
		<li><a href="#">Resultados de búsqueda</a></li>
	</ul>
	<h2>
		<strong>Resultado de la busqueda</strong>
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

	<div class="pagination">
		<g:paginate total="${count ?: 0}" max="${max}" params="${[opcion:opcion, busqueda: busqueda]}"/>
	</div>
	<table class="table">
		<thead>
			<tr>
				<g:sortableColumn style="font-size:small;" property="id" params="[opcion:opcion, busqueda:busqueda]"
					title="${message(code: 'documento.id.label', default: 'Id')}" />
				<th style="font-size: small;"><span
					class="hidden-xs hidden-sm">Uuid</span></th>
				<g:sortableColumn style="font-size:small;" property="mimetype" params="[opcion:opcion, busqueda:busqueda]"
					title="${message(code: 'documento.mimetype.label', default: 'Tipo')}" />
				<g:sortableColumn style="font-size:small;" property="nombre" params="[opcion:opcion, busqueda:busqueda]"
					title="${message(code: 'documento.nombre.label', default: 'Nombre')}" />
				<g:sortableColumn style="font-size:small;"
					property="fechaModificacion" params="[opcion:opcion, busqueda:busqueda]"
					title="${message(code: 'documento.fechaModificacion.label', default: 'Fecha de Modificación')}" />
				<g:sortableColumn style="font-size:small;" property="fechaCreacion" params="[opcion:opcion, busqueda:busqueda]"
					title="${message(code: 'documento.fechaCreacion.label', default: 'Fecha de Creación')}" />
				<th style="font-size: x-small;">...</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${list}" status="i" var="element">
				<tr>
					<td style="font-size: 11px; width: 80px;">
						${element?.id}
					</td>
					<td style="font-size: 11px; width: 230px"><span
						class="hidden-xs hidden-sm">
							${element?.uuid}
					</span></td>
					<td style="font-size: 11px; width: 28px;"><asset:image
							src="mimetypes/${element?.mimetype.replace("/", "_")}.png"
							title="${element?.mimetype}" /></td>
					<td style="font-size: 11px;">
						${element?.nombre}
					</td>
					<td style="font-size: 11px; width: 196px;">
						${element?.fechaModificacion}
					</td>
					<td style="font-size: 11px; width: 196px;">
						${element?.fechaCreacion}
					</td>
					<td style="width: 196px;">
						<button id="btnVer" onclick="btnVer_click('${element?.uuid}')"
							type="button" class="btn btn-default btn-xs">
							<span class="glyphicon glyphicon-eye-open"></span><span
								class="hidden-xs hidden-sm" style="font-size: x-small;">
								Ver detalles</span>
						</button>
						<button id="btnDescargar"
							onclick="btnDescargar_click('${element?.uuid}')" type="button"
							class="btn btn-default btn-xs">
							<span class="glyphicon glyphicon-download-alt"></span><span
								class="hidden-xs hidden-sm" style="font-size: x-small;">
								Descargar</span>
						</button>
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${count ?: 0}" max="${max}" />
	</div>

	<script type="text/javascript">
		function btnDescargar_click(uuid) {
			window
					.open('<g:createLink controller="documento" action="download" />/'
							+ uuid);
		}

		function btnVer_click(uuid) {
			window
					.open('<g:createLink controller="documento" action="show" />/'
							+ uuid);
		}
	</script>

</body>
</html>