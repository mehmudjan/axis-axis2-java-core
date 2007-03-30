//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.03.21 at 10:56:51 AM CDT 
//


package org.apache.axis2.jaxws.description.xml.handler;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * The handler-chain element defines the handlerchain. Handlerchain can be defined such that the
 * handlers in the handlerchain operate all ports of a service, on a specific port, or on a list of
 * protocol-bindings. The choice of elements service-name-pattern, port-name-pattern, and
 * protocol-bindings are used to specify whether the handlers in the handler-chain are for a
 * service, port or protocol binding. If none of these choices are specified with the handler-chain
 * element, then the handlers specified in the handler-chain will be applied on everything.
 * <p/>
 * <p/>
 * <p/>
 * <p>Java class for handler-chainType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="handler-chainType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="service-name-pattern" type="{http://java.sun.com/xml/ns/javaee}qname-pattern"/>
 *           &lt;element name="port-name-pattern" type="{http://java.sun.com/xml/ns/javaee}qname-pattern"/>
 *           &lt;element name="protocol-bindings" type="{http://java.sun.com/xml/ns/javaee}protocol-bindingListType"/>
 *         &lt;/choice>
 *         &lt;element name="handler" type="{http://java.sun.com/xml/ns/javaee}handlerType"
 * maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "handler-chainType", propOrder = {
        "serviceNamePattern",
        "portNamePattern",
        "protocolBindings",
        "handler"
        })
public class HandlerChainType {

    @XmlElement(name = "service-name-pattern", namespace = "http://java.sun.com/xml/ns/javaee")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected java.lang.String serviceNamePattern;
    @XmlElement(name = "port-name-pattern", namespace = "http://java.sun.com/xml/ns/javaee")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected java.lang.String portNamePattern;
    @XmlList
    @XmlElement(name = "protocol-bindings", namespace = "http://java.sun.com/xml/ns/javaee")
    protected List<java.lang.String> protocolBindings;
    @XmlElement(namespace = "http://java.sun.com/xml/ns/javaee", required = true)
    protected List<HandlerType> handler;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the serviceNamePattern property.
     *
     * @return possible object is {@link java.lang.String }
     */
    public java.lang.String getServiceNamePattern() {
        return serviceNamePattern;
    }

    /**
     * Sets the value of the serviceNamePattern property.
     *
     * @param value allowed object is {@link java.lang.String }
     */
    public void setServiceNamePattern(java.lang.String value) {
        this.serviceNamePattern = value;
    }

    /**
     * Gets the value of the portNamePattern property.
     *
     * @return possible object is {@link java.lang.String }
     */
    public java.lang.String getPortNamePattern() {
        return portNamePattern;
    }

    /**
     * Sets the value of the portNamePattern property.
     *
     * @param value allowed object is {@link java.lang.String }
     */
    public void setPortNamePattern(java.lang.String value) {
        this.portNamePattern = value;
    }

    /**
     * Gets the value of the protocolBindings property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the protocolBindings property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProtocolBindings().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link java.lang.String }
     */
    public List<java.lang.String> getProtocolBindings() {
        if (protocolBindings == null) {
            protocolBindings = new ArrayList<java.lang.String>();
        }
        return this.protocolBindings;
    }

    /**
     * Gets the value of the handler property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the handler property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHandler().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link HandlerType }
     */
    public List<HandlerType> getHandler() {
        if (handler == null) {
            handler = new ArrayList<HandlerType>();
        }
        return this.handler;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link java.lang.String }
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link java.lang.String }
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

}
