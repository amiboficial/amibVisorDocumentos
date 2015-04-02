<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Documentos 0.1 - Ver detalle</title>
<script type="text/javascript">
	function btnDescargar_click(uuid) {
		window
				.open('<g:createLink controller="documento" action="download" />/'
						+ uuid);
	}
</script>
</head>
<body>

	<h2>
		<strong>Detalles de archivo</strong>
	</h2>
	<br />
	<table border="0" style="width: 70%">
		<tr>
			<th style="width: 40%">Uuid</th>
			<td style="width: 60%">
				${model?.uuid}
			</td>
		</tr>
		<tr>
			<th>Nombre</th>
			<td>
				${model?.nombre}
			</td>
		</tr>
		<tr>
			<th>Clave</th>
			<td>
				${model?.clave}
			</td>
		</tr>
		<tr>
			<th>Tipo</th>
			<td><asset:image
					src="mimetypes/${model?.mimetype.replace("/", "_")}.png"
					title="${model?.mimetype}" /> ${model?.mimetype}</td>
		</tr>
		<tr>
			<th>Fecha de creación</th>
			<td><g:formatDate date="${model?.fechaCreacion}"
					format="dd/MM/yyyy HH:mm:ss" /></td>
		</tr>
		<tr>
			<th>Fecha de actualización</th>
			<td><g:formatDate date="${model?.fechaModificacion}"
					format="dd/MM/yyyy HH:mm:ss" /></td>
		</tr>
		<g:if
			test="${model instanceof mx.amib.sistemas.external.documentos.service.DocumentoOficioCnbvRespositorioTO}">
			<tr>
				<th>Matrículas</th>
				<td>
					${model?.matriculas}
				</td>
			</tr>
			<tr>
				<th>Nombre</th>
				<td>
					${model?.nombres}
				</td>
			</tr>
			<tr>
				<th>Autorizaciones</th>
				<td>
					${model?.autorizaciones}
				</td>
			</tr>
		</g:if>
		<g:if
			test="${model instanceof mx.amib.sistemas.external.documentos.service.DocumentoPoderRepositorioTO}">
			<tr>
				<th>Tipo documento</th>
				<td>
					${model?.tipoDocumentoRespaldo}
				</td>
			</tr>
			<tr>
				<th>Representante legal</th>
				<td>
					${model?.representanteLegalNombreCompleto}
				</td>
			</tr>
			<tr>
				<th>Número escritura</th>
				<td>
					${model?.numeroEscritura}
				</td>
			</tr>
			<tr>
				<th>Fecha apoderamiento</th>
				<td><g:formatDate date="${model?.fechaApoderamiento}"
						format="dd/MM/yyyy HH:mm:ss" /></td>
			</tr>

			<tr>
				<th>Matrículas de apoderados</th>
				<td>
					${model?.matriculasApoderados}
				</td>
			</tr>
		</g:if>
		<g:if
			test="${model instanceof mx.amib.sistemas.external.documentos.service.DocumentoRevocacionRepositorioTO}">
			<tr>
				<th>Tipo documento de respaldo</th>
				<td>
					${model?.tipoDocumentoRespaldo}
				</td>
			</tr>
			<tr>
				<th>Representante legal</th>
				<td>
					${model?.representanteLegalNombreCompleto}
				</td>
			</tr>
			<tr>
				<th>Número escritura</th>
				<td>
					${model?.numeroEscritura}
				</td>
			</tr>
			<tr>
				<th>Fecha revocacion</th>
				<td><g:formatDate date="${model?.fechaRevocacion}"
						format="dd/MM/yyyy HH:mm:ss" /></td>
			</tr>
			<tr>
				<th>Matrículas revocados</th>
				<td>
					${model?.matriculasRevocados}
				</td>
			</tr>
			<tr>
				<th>Nombres revocados</th>
				<td>
					${model?.nombresRevocados }
				</td>
			</tr>
			<tr>
				<th>Notario</th>
				<td>
					${model?.notario}
				</td>
			</tr>
			<tr>
				<th>Grupo financiero</th>
				<td>
					${model?.grupoFinanciero}
				</td>
			</tr>
			<tr>
				<th>Institución</th>
				<td>
					${model?.institucion}
				</td>
			</tr>
		</g:if>
		<g:if
			test="${model instanceof mx.amib.sistemas.external.documentos.service.DocumentoFotoSustentanteRepositorioTO}">
			<tr>
				<th>Número de matrícula</th>
				<td>
					${model?.numeroMatricula}
				</td>
			</tr>
			<tr>
				<th>Nombre completo</th>
				<td>
					${model?.nombreCompleto}
				</td>
			</tr>
		</g:if>
		<g:if
			test="${model instanceof mx.amib.sistemas.external.documentos.service.DocumentoSustentanteRepositorioTO}">

			<tr>
				<th>Número de matrícula</th>
				<td>
					${model?.numeroMatricula}
				</td>
			</tr>
			<tr>
				<th>Tipo de documentos sustentante</th>
				<td>
					${model?.tipoDocumentoSustentante}
				</td>
			</tr>
			<tr>
				<th>Nombre completo</th>
				<td>
					${model?.nombreCompleto}
				</td>
			</tr>
		</g:if>
		<tr>
			<td></td>
			<td><br/><button id="btnDescargar"
					onclick="btnDescargar_click('${model?.uuid}')" type="button"
					class="btn btn-default btn-xs">
					<span class="glyphicon glyphicon-download-alt"></span><span
						class="hidden-xs hidden-sm" style="font-size: x-small;">
						Descargar</span>
				</button></td>
		</tr>
	</table>
</body>
</html>