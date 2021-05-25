
package com.example.localtest.smartstore.customer.type;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CustomerInquiryServicePortType", targetNamespace = "http://customerinquiry.shopn.platform.nhncorp.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CustomerInquiryServicePortType {


    /**
     * 
     * @param getCustomerInquiryListRequest
     * @return
     *     returns com.nhncorp.platform.shopn.customerinquiry.GetCustomerInquiryListResponseType
     */
    @WebMethod(operationName = "GetCustomerInquiryList", action = "CustomerInquiryService#GetCustomerInquiryList")
    @WebResult(name = "GetCustomerInquiryListResponse", targetNamespace = "http://customerinquiry.shopn.platform.nhncorp.com/", partName = "GetCustomerInquiryListResponse")
    public GetCustomerInquiryListResponseType getCustomerInquiryList(
        @WebParam(name = "GetCustomerInquiryListRequest", targetNamespace = "http://customerinquiry.shopn.platform.nhncorp.com/", partName = "GetCustomerInquiryListRequest")
        GetCustomerInquiryListRequestType getCustomerInquiryListRequest);

    /**
     * 
     * @param answerCustomerInquiryRequest
     * @return
     *     returns com.nhncorp.platform.shopn.customerinquiry.AnswerCustomerInquiryResponseType
     */
    @WebMethod(operationName = "AnswerCustomerInquiry", action = "CustomerInquiryService#AnswerCustomerInquiry")
    @WebResult(name = "AnswerCustomerInquiryResponse", targetNamespace = "http://customerinquiry.shopn.platform.nhncorp.com/", partName = "AnswerCustomerInquiryResponse")
    public AnswerCustomerInquiryResponseType answerCustomerInquiry(
        @WebParam(name = "AnswerCustomerInquiryRequest", targetNamespace = "http://customerinquiry.shopn.platform.nhncorp.com/", partName = "AnswerCustomerInquiryRequest")
        AnswerCustomerInquiryRequestType answerCustomerInquiryRequest);

}
