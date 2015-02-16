// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
		
		mx.amib.sistemas.catalogos.resthttpURL = 'http://bimalatrop.no-ip.biz:8080/amibCatalogos-0.1/'
		mx.amib.sistemas.expediente.resthttpURL = 'http://bimalatrop.no-ip.biz:8080/amibExpediente-0.1/'
		mx.amib.sistemas.documentos.resthttpURL = 'http://bimalatrop.no-ip.biz:8080/amibDocumentos-0.1/'
    }
	test {
		grails.logging.jul.usebridge = false
		// TODO: grails.serverURL = "http://www.changeme.com"
		mx.amib.sistemas.catalogos.resthttpURL = 'http://bimalatrop.no-ip.biz:8080/amibCatalogos-0.1/'
		mx.amib.sistemas.expediente.resthttpURL = 'http://bimalatrop.no-ip.biz:8080/amibExpediente-0.1/'
		mx.amib.sistemas.documentos.resthttpURL = 'http://bimalatrop.no-ip.biz:8080/amibDocumentos-0.1/'
	}
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
		mx.amib.sistemas.catalogos.resthttpURL = 'http://localhost:8081/amibCatalogos/'
		mx.amib.sistemas.expediente.resthttpURL = 'http://localhost:8082/amibExpediente/'
		mx.amib.sistemas.documentos.resthttpURL = 'http://localhost:8083/amibDocumentos/'
    }
}

// log4j configuration
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

grails.plugins.twitterbootstrap.fixtaglib = true
grails.plugins.twitterbootstrap.defaultBundle = 'bundle_bootstrap'

mx.amib.sistemas.registro.tempDir = 'H:\\Temp\\amibRegistro\\'

//INICIA: ESPECIFICACIONES DE RUTAS PARA CONSUMO DE SERVICIOS REST
//especificaciones de rutas para servicios REST de amibCatalogos
mx.amib.sistemas.catalogos.general.GrupoFinanciero.list = 'grupoFinancieroRestful/index?max=100'
mx.amib.sistemas.catalogos.general.GrupoFinanciero.getById = 'grupoFinancieroRestful/show/'
mx.amib.sistemas.catalogos.general.Institucion.getById = 'institucionRestful/show/'
mx.amib.sistemas.catalogos.sepomex.EntidadFederativa.list = 'entidadFederativaRestful/index?max=32'
mx.amib.sistemas.catalogos.sepomex.Sepomex.findByCodigoPostal = 'sepomexRestful/findByCodigoPostal?cp='

//especificaciones de rutas para servicios REST de amibExpediente
mx.amib.sistemas.expediente.Sustentante.obtenerSustentantePorMatricula = 'sustentanteRestful/obtenerSustentantePorMatricula/'

//especificaciones de rutas para servicios REST de amibDocumentos
mx.amib.sistemas.documentos.Documento.save = 'documentoRestful/save'
mx.amib.sistemas.documentos.Documento.getByUuid = 'documentoRestful/getByUuid?uuid='
mx.amib.sistemas.documentos.Documento.deleteByUuid = 'documentoRestful/deleteByUuid?uuid='

mx.amib.sistemas.documentos.Documento.findAllByMatricula = 'documentoRestful/findAllByMatricula'
mx.amib.sistemas.documentos.Documento.findAllLikeNombreArchivo = 'documentoRestful/findAllLikeNombreArchivo'
mx.amib.sistemas.documentos.Documento.findAllLikeDescripcion = 'documentoRestful/findAllLikeDescripcion'
mx.amib.sistemas.documentos.Documento.findAllGeneric = 'documentoRestful/findAllGeneric'
mx.amib.sistemas.documentos.Documento.findAllByType = 'documentoRestful/findAllByType'
mx.amib.sistemas.documentos.Documento.findAll = 'documentoRestful/findAll'

mx.amib.sistemas.documentos.Documento.list = 'documentoRestful/index'
mx.amib.sistemas.documentos.CnbvDgaOficio.list = 'cnbvDgaOficioRestful/index'
mx.amib.sistemas.documentos.DocumentoPoder.list = 'documentoPoderRestful/index'
mx.amib.sistemas.documentos.FotoSustentante.list = 'fotoSustenanteRestful/index'
mx.amib.sistemas.documentos.DocumentoSustentante.list = 'documentoSustentanteRestful/index'
mx.amib.sistemas.documentos.DocumentoRevocacion.list = 'documentoRevocacionRestful/index'

mx.amib.sistemas.documentos.DocumentoPoder.save = 'documentoPoderRestful/save'
mx.amib.sistemas.documentos.DocumentoPoder.update = 'documentoPoderRestful/updateByUuid'
mx.amib.sistemas.documentos.DocumentoRevocacion.save = 'documentoRevocacionRestful/save'
mx.amib.sistemas.documentos.DocumentoRevocacion.update = 'documentoRevocacionRestful/updateByUuid'
mx.amib.sistemas.documentos.archivo.subirArchivoDocumentoUuid = 'archivoDocumento/subirArchivoDocumentoUuid?uuid='
mx.amib.sistemas.documentos.archivo.descargarArchivoDocumentoUuid = 'archivoDocumento/descargarArchivoDocumentoUuid?uuid='