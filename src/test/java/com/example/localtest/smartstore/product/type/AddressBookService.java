
package com.example.localtest.smartstore.product.type;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AddressBookService", targetNamespace = "http://shopn.platform.nhncorp.com/", wsdlLocation = "file:./src/main/resources/wsdl/product/ShopNAPI.wsdl")
public class AddressBookService
    extends Service
{

    private final static URL ADDRESSBOOKSERVICE_WSDL_LOCATION;
    private final static WebServiceException ADDRESSBOOKSERVICE_EXCEPTION;
    private final static QName ADDRESSBOOKSERVICE_QNAME = new QName("http://shopn.platform.nhncorp.com/", "AddressBookService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:./src/main/resources/wsdl/product/ShopNAPI.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ADDRESSBOOKSERVICE_WSDL_LOCATION = url;
        ADDRESSBOOKSERVICE_EXCEPTION = e;
    }

    public AddressBookService() {
        super(__getWsdlLocation(), ADDRESSBOOKSERVICE_QNAME);
    }

    public AddressBookService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ADDRESSBOOKSERVICE_QNAME, features);
    }

    public AddressBookService(URL wsdlLocation) {
        super(wsdlLocation, ADDRESSBOOKSERVICE_QNAME);
    }

    public AddressBookService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ADDRESSBOOKSERVICE_QNAME, features);
    }

    public AddressBookService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AddressBookService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AddressBookServicePortType
     */
    @WebEndpoint(name = "AddressBookServiceSOAP11port_http")
    public AddressBookServicePortType getAddressBookServiceSOAP11PortHttp() {
        return super.getPort(new QName("http://shopn.platform.nhncorp.com/", "AddressBookServiceSOAP11port_http"), AddressBookServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AddressBookServicePortType
     */
    @WebEndpoint(name = "AddressBookServiceSOAP11port_http")
    public AddressBookServicePortType getAddressBookServiceSOAP11PortHttp(WebServiceFeature... features) {
        return super.getPort(new QName("http://shopn.platform.nhncorp.com/", "AddressBookServiceSOAP11port_http"), AddressBookServicePortType.class, features);
    }

    /**
     * 
     * @return
     *     returns AddressBookServicePortType
     */
    @WebEndpoint(name = "AddressBookServiceSOAP12port_http")
    public AddressBookServicePortType getAddressBookServiceSOAP12PortHttp() {
        return super.getPort(new QName("http://shopn.platform.nhncorp.com/", "AddressBookServiceSOAP12port_http"), AddressBookServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AddressBookServicePortType
     */
    @WebEndpoint(name = "AddressBookServiceSOAP12port_http")
    public AddressBookServicePortType getAddressBookServiceSOAP12PortHttp(WebServiceFeature... features) {
        return super.getPort(new QName("http://shopn.platform.nhncorp.com/", "AddressBookServiceSOAP12port_http"), AddressBookServicePortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ADDRESSBOOKSERVICE_EXCEPTION!= null) {
            throw ADDRESSBOOKSERVICE_EXCEPTION;
        }
        return ADDRESSBOOKSERVICE_WSDL_LOCATION;
    }

}
