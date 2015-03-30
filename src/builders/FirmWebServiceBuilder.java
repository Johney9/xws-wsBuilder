package builders;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.cbs.firma.Firma;
import rs.ac.uns.ftn.xws.cbs.firma.FirmaService;

public class FirmWebServiceBuilder implements WebServiceBuilder {

	private static final QName SERVICE_NAME = FirmaService.SERVICE;
	private static final String DEFAULT_WEBAPP_NAME = "xws-firma";
	private static String WSDL_LOCATION = "http://localhost:8080/"+DEFAULT_WEBAPP_NAME+"/FirmaService?wsdl";
	private FirmaService service;
	
	@Override
	public Service buildWebService(String webAppName) {
		
        try {
        	
        	if(webAppName!=null && !webAppName.isEmpty()) {
        		WSDL_LOCATION.replace(DEFAULT_WEBAPP_NAME, webAppName);
        	}
			URL wsdlURL = new URL(WSDL_LOCATION);
			service = new FirmaService(wsdlURL, SERVICE_NAME);
			
		} catch (MalformedURLException e) {
			
			throw new RuntimeException(e.getCause());
		}
        
        return service;
	}
	
	@Override
	public Firma buildPort(String webAppName) {
		if(service==null) {
			buildWebService(webAppName);
		}
		return service.getFirma();
	}

}
