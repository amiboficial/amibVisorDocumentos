package mx.amib.sistemas.util.service

import groovy.transform.AutoClone
import grails.transaction.Transactional

import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.apache.commons.io.FileUtils

import groovy.time.TimeCategory

import java.util.UUID

import liquibase.util.file.FilenameUtils;

/**
 * ArchivoTemporalService 
 * 
 * Este servicio permite gestionar archivos temporales en la aplicación
 * 
 * @author Gabriel
 * @version 1.1 - (Última actualización) 16/10/2014
 * 
 */
@Transactional
class ArchivoTemporalService {

	private static TreeMap<String,ArchivoTO> archivosTemporales = new TreeMap<String,ArchivoTO>()
	
	String directorioTemporal
	Integer minutosCaducidadPorArchivo
	
	/**
	 * Comprueba si un archivo dado su UUID se 
	 * encuentra almancenado en temporal
	 * 
	 * @param uuid
	 * @return
	 */
	boolean comprobarArchivoTemporal(String uuid){
		return this.archivosTemporales.containsKey(uuid)
	}
	
	void renuevaCaducidadArchivoTemporal(String uuid){
		Date ahorita = new Date()
		if(this.archivosTemporales.containsKey(uuid)){
			this.archivosTemporales.get(uuid).caducidad = ahorita + minutosCaducidadPorArchivo.minutes
		}
	}
	
	/**
	 * Obtiene los metadatos de un archivo temporal dado su UUID
	 * siempre cuando halla sido almacenado anteriormente.
	 * 
	 * @param uuid Identificador universal de archivo
	 * @return instancia de ArchivoTO con metadatos de archivo
	 */
    ArchivoTO obtenerArchivoTemporal(String uuid) {
		ArchivoTO a = null
		
		if(this.archivosTemporales.containsKey(uuid)){
			a = this.archivosTemporales.get(uuid)
		}
		
		return a
    }
	
	/**
	 * Guarda archivo en temporal; los metadatos son guardados en memoria.
	 * 
	 * @param sessionId		Identificador de sesión
	 * @param cmf			Objeto del tipo CommonsMultipartFile que almacena informacion de
	 * 						archivo recibido por medio de un "multipart"
	 * @return				Una instancia de ArchivoTO con los metadatos almacenados
	 * 
	 */
	ArchivoTO guardarArchivoTemporal(String sessionId, CommonsMultipartFile cmf){
		ArchivoTO anvo = new ArchivoTO()
		Date ahorita = new Date()
		
		anvo.uuid = UUID.randomUUID() as String
		anvo.sessionId = sessionId
		anvo.filename = cmf.getOriginalFilename()
		anvo.mimetype = cmf.getContentType()
		
		anvo.temploc = directorioTemporal + anvo.uuid
		use( TimeCategory ) {
			anvo.caducidad = ahorita + minutosCaducidadPorArchivo.minutes
		}
		
		FileOutputStream fos = new FileOutputStream(anvo.temploc)
		fos.write(cmf.getBytes())
		fos.close()
		
		archivosTemporales.put(anvo.uuid, anvo)
		
		return anvo
	}
	
	/**
	 * Descarga un archivo dada una URL y forma una instancia de ArchivoTO
	 * la cual estará guardada en temporal.
	 * 
	 * @param sessionId
	 * @param uuid
	 * @param filename
	 * @param mimetype
	 * @param source
	 * @return Una instancia de ArchivoTO con los metadatos almacenados
	 */
	ArchivoTO descargarArchivoTemporal(String sessionId, String uuid, String filename, String mimetype, URL source){
		ArchivoTO anvo = new ArchivoTO()
		Date ahorita = new Date()
		
		anvo.uuid = uuid
		anvo.sessionId = sessionId
		anvo.filename = filename
		anvo.mimetype = mimetype
		anvo.temploc = directorioTemporal + anvo.uuid
		use( TimeCategory ) {
			anvo.caducidad = ahorita + minutosCaducidadPorArchivo.minutes
		}
		
		try{
			FileUtils.copyURLToFile(source, new File(anvo.temploc) )
		}
		catch(IOException e){
			anvo = null
		}
		if(!this.archivosTemporales.containsKey(anvo.uuid)){
			this.archivosTemporales.put(anvo.uuid, anvo)
		}
		else{
			this.archivosTemporales.remove(anvo.uuid)
			this.archivosTemporales.put(anvo.uuid, anvo)
		}
		return anvo
	}
	
	/**
	 * Elimina archivo en temporal
	 * 
	 * @param uuid Identificador universal de archivo
	 */
	void eliminarArchivoTemporal(String uuid){
		if(this.archivosTemporales.containsKey(uuid)){
			this.archivosTemporales.remove(uuid)
			try{
			   File file = new File(directorioTemporal + uuid);
			   if(file.delete()){
				   System.out.println(file.getName() + " is deleted!");
			   }else{
				   System.out.println("Delete operation is failed.");
			   }
		
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
		
	/**
	 * Elimina todos los archivos que ya hayan caducado
	 */
	void eliminarArchivosTemporalesCaducos(){
		List<ArchivoTO> archivosBorrar = new ArrayList<ArchivoTO>()
		Date ahorita = new Date()
		
		this.archivosTemporales.values().each{ archivoTemporal ->
			if(ahorita > archivoTemporal.caducidad){
				archivosBorrar.add(archivoTemporal)
			}
		}
		
		archivosBorrar.each{ archivoBorrar ->
			this.eliminarArchivoTemporal(archivoBorrar.uuid)
		}
	}
	
	/**
	 * Elimina todos los archivos dado un identificador de sesión
	 * (util para limpiar archivos de sesiones expiradas)
	 * 
	 * @param sessionId Identificador de session
	 */
	void eliminarArchivosTemporalesPorSessionId(String sessionId){
		List<ArchivoTO> archivosBorrar = new ArrayList<ArchivoTO>()
		
		this.archivosTemporales.values().each{ archivoTemporal ->
			if(archivoTemporal.sessionId == sessionId){
				archivosBorrar.add(archivoTemporal)
			}
		}
		
		archivosBorrar.each{ archivoBorrar -> 
			this.eliminarArchivoTemporal(archivoBorrar.uuid)
		}
	}
	
	/**
	 * Elimina todos los archivos del directorio temporal.
	 * 
	 * NOTA: Solo se debe llamar al inicio o al final de la aplicación,
	 * de lo contrario causará comportamiento no esperado.
	 */
	void limpiarDirectorioTemporales(){
		FileUtils.cleanDirectory(directorioTemporal);
	}
}

@AutoClone
class ArchivoTO{
	String uuid
	String sessionId
	String filename
	String mimetype
	
	String temploc
	Date caducidad
}