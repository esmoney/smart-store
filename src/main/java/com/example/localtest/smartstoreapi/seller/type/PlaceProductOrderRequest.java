
package com.example.localtest.smartstoreapi.seller.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>placeProductOrderRequest complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="placeProductOrderRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://seller.shopn.platform.nhncorp.com/}BaseCheckoutRequestType">
 *       &lt;sequence>
 *         &lt;element name="ProductOrderID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckReceiverAddressChanged" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "placeProductOrderRequest", propOrder = {
    "productOrderID",
    "checkReceiverAddressChanged"
})
public class PlaceProductOrderRequest
    extends BaseCheckoutRequestType
{

    @XmlElement(name = "ProductOrderID", required = true)
    protected String productOrderID;
    @XmlElement(name = "CheckReceiverAddressChanged")
    protected Boolean checkReceiverAddressChanged;

    /**
     * productOrderID 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductOrderID() {
        return productOrderID;
    }

    /**
     * productOrderID 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductOrderID(String value) {
        this.productOrderID = value;
    }

    /**
     * checkReceiverAddressChanged 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCheckReceiverAddressChanged() {
        return checkReceiverAddressChanged;
    }

    /**
     * checkReceiverAddressChanged 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCheckReceiverAddressChanged(Boolean value) {
        this.checkReceiverAddressChanged = value;
    }

}