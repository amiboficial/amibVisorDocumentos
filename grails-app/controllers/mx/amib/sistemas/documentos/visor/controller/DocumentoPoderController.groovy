package mx.amib.sistemas.documentos.visor.controller

import mx.amib.sistemas.external.documentos.service.ClaseDocumento;

class DocumentoPoderController {

	def documentoRepositorioService
	
    def index() {
		Integer max = Math.min(Integer.parseInt(params.max?:'30'), 100)
		Integer offset = Integer.parseInt(params.offset?:'0')
		String sort = params.sort?:"id"
		String order = params.order?:"desc"
		def sr = documentoRepositorioService.obtenerTodos(ClaseDocumento.PODER, max,offset,sort,order)	
		respond sr.list, [model: [list: sr.list,max:max,offset:offset,sort:sort,order:order,count:sr.count]]
	}
}
