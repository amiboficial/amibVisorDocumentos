package mx.amib.sistemas.documentos.visor.controller



class BusquedaController {

	def documentoRepositorioService
	int opcion
	def index() {
		String busqueda=params.busqueda
		try{
			opcion=Integer.parseInt(params.opcion)
		}catch(NumberFormatException e){
			opcion=0
		}
		respond opcion, [model: [busqueda : busqueda, opcion: opcion]]
	}

	def show(){
		Integer max = Math.min(Integer.parseInt(params.max?:'30'), 100)
		Integer offset = Integer.parseInt(params.offset?:'0')
		String sort = params.sort?:"id"
		String order = params.order?:"desc"

		Integer opcion= Integer.parseInt(params.opcion?:'0')
		String busqueda=params.busqueda

		Boolean error=false;

		switch(opcion){
			case 1:
				def doc= documentoRepositorioService.obtenerMetadatosDocumento(busqueda);
				if(doc==null){
					error=true;
				}else{
					redirect(action:"show", controller: "documento", params:[id: busqueda] )
				}
				break
			case 2:
				try{
					Integer matricula=Integer.parseInt(busqueda)
					def doc=documentoRepositorioService.obtenerTodosPorMatricula(matricula, max, offset, sort, order)
					if(doc.list.isEmpty()){
						error=true;
					}else{
						respond doc.list, [model: [list: doc.list,max:max,offset:offset,sort:sort,order:order,count:doc.count,opcion:opcion, busqueda:busqueda]]
					}
				}catch(NumberFormatException e){
					error=true;
				}
				break
			case 3:
				def doc=documentoRepositorioService.obtenerTodosPorNombreArchivoILike(busqueda.trim(), max, offset, sort, order)
				if(doc.list.isEmpty()){
					error=true;
				}else{
					respond doc.list, [model: [list: doc.list,max:max,offset:offset,sort:sort,order:order,count:doc.count,opcion:opcion, busqueda:busqueda]]
				}
				break
			case 4:
				def doc= documentoRepositorioService.obtenerTodosPorDescripcionILike(busqueda.trim(), max, offset, sort, order)
				if(doc.list.isEmpty()){
					error=true
				}else{
					respond doc.list, [model: [list: doc.list,max:max,offset:offset,sort:sort,order:order,count:doc.count,opcion:opcion, busqueda:busqueda]]
				}

				break
			default:
				error=true;
				break
		}

		if(error){
			flash.errorMessage = message(code: "default.not.found.file.message")
			redirect(action: "index", params:[opcion:opcion, busqueda:busqueda])
		}
	}
}
