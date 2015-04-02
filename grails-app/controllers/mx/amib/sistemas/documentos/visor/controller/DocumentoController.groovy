package mx.amib.sistemas.documentos.visor.controller

import org.apache.tools.ant.types.resources.selectors.InstanceOf;

import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach;

import mx.amib.sistemas.external.documentos.service.DocumentoOficioCnbvRespositorioTO;
import mx.amib.sistemas.external.documentos.service.DocumentoRepositorioTO;
import mx.amib.sistemas.external.documentos.service.DocumentoRevocacionRepositorioTO;

class DocumentoController {

	def documentoRepositorioService

	def index() {
		Integer max = Math.min(Integer.parseInt(params.max?:'30'), 100)
		Integer offset = Integer.parseInt(params.offset?:'0')
		String sort = params.sort?:"id"
		String order = params.order?:"desc"
		def sr = documentoRepositorioService.obtenerTodos(max,offset,sort,order)
		respond sr.list, [model: [list: sr.list,max:max,offset:offset,sort:sort,order:order,count:sr.count]]
	}

	def show(String id ){
		def doc= documentoRepositorioService.obtenerMetadatosDocumento(id);
		respond doc,[model:[model: doc]]
	}
}
