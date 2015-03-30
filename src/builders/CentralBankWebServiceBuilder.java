package builders;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.cbs.centralna_banka.CentralnaBanka;
import rs.ac.uns.ftn.xws.cbs.centralna_banka.CentralnaBankaService;

public class CentralBankWebServiceBuilder implements WebServiceBuilder {

	private static final QName SERVICE_NAME = CentralnaBankaService.SERVICE;
	private static final String DEFAULT_WEBAPP_NAME = "xws-centralna_banka";
	private static String WSDL_LOCATION = "http://localhost:8080/"+DEFAULT_WEBAPP_NAME+"/CentralnaBankaService?wsdl";
	private CentralnaBankaService service;
	
	@Override
	public Service buildWebService(String webAppName) {
		
        try {
        	
        	if(webAppName!=null && !webAppName.isEmpty()) {
        		WSDL_LOCATION.replace(DEFAULT_WEBAPP_NAME, webAppName);
        	}
			URL wsdlURL = new URL(WSDL_LOCATION);
			service = new CentralnaBankaService(wsdlURL, SERVICE_NAME);
			
		} catch (MalformedURLException e) {
			
			throw new RuntimeException(e.getCause());
		}
        
        return service;
	}
	
	@Override
	public CentralnaBanka buildPort(String webAppName) {
		if(service==null) {
			buildWebService(webAppName);
		}
		return service.getCentralnaBanka();
	}

}
