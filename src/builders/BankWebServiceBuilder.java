package builders;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.xws.cbs.banka.Banka;
import rs.ac.uns.ftn.xws.cbs.banka.BankaService;

public class BankWebServiceBuilder implements WebServiceBuilder {

	private static final QName SERVICE_NAME = BankaService.SERVICE;
	private static final String DEFAULT_WEBAPP_NAME = "xws-banka";
	private static String WSDL_LOCATION = "http://localhost:8080/"+DEFAULT_WEBAPP_NAME+"/BankaService?wsdl";
	private BankaService service;
	
	@Override
	public Service buildWebService(String webAppName) {
		
        try {
        	
        	if(webAppName!=null && !webAppName.isEmpty()) {
        		WSDL_LOCATION.replace(DEFAULT_WEBAPP_NAME, webAppName);
        	}
			URL wsdlURL = new URL(WSDL_LOCATION);
			service = new BankaService(wsdlURL, SERVICE_NAME);
			
		} catch (MalformedURLException e) {
			
			throw new RuntimeException(e.getCause());
		}
        
        return service;
	}
	
	@Override
	public Banka buildPort(String webAppName) {
		if(service==null) {
			buildWebService(webAppName);
		}
		return service.getBanka();
	}

}
