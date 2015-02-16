package mx.amib.sistemas.documentos.visor.controller

class DocumentoController {

	def documentoRepositorioService
	
    def index() {
		Integer max = Math.min(Integer.parseInt(params.max?:'30'), 100)
		Integer offset = Integer.parseInt(params.offset?:'0')
		String sort = params.sort?:"id"
		String order = params.order?:"desc"
		
		def sr = documentoRepositorioService.obtenerTodos(max,offset,sort,order)
		
		println sr.list.size()
		
		respond sr.list, [model: [list: sr.list,max:max,offset:offset,sort:sort,order:order,count:sr.count]]
	}
}
