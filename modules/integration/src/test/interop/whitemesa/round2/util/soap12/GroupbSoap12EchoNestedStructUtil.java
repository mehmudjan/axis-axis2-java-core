/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.interop.whitemesa.round2.util.soap12;

import test.interop.whitemesa.round2.util.SunRound2ClientUtil;
import org.apache.axis2.om.OMAbstractFactory;
import org.apache.axis2.om.OMElement;
import org.apache.axis2.soap.SOAPBody;
import org.apache.axis2.soap.SOAPEnvelope;
import org.apache.axis2.soap.SOAPFactory;

public class GroupbSoap12EchoNestedStructUtil implements SunRound2ClientUtil {

    public SOAPEnvelope getEchoSoapEnvelope() {

            SOAPFactory omfactory = OMAbstractFactory.getSOAP12Factory();
            SOAPEnvelope reqEnv = omfactory.createSOAPEnvelope();
            //reqEnv.declareNamespace("http://schemas.xmlsoap.org/soap/envelope/", "soapenv");
            //reqEnv.declareNamespace("http://schemas.xmlsoap.org/wsdl/", "xmlns");
            //reqEnv.declareNamespace("http://schemas.xmlsoap.org/wsdl/soap/", "soap");
            reqEnv.declareNamespace("http://www.w3.org/2001/XMLSchema", "xsd");
            reqEnv.declareNamespace("http://schemas.xmlsoap.org/soap/encoding/", "SOAP-ENC");
            reqEnv.declareNamespace("http://soapinterop.org/", "tns");
            reqEnv.declareNamespace("http://soapinterop.org/xsd", "s");
            //reqEnv.declareNamespace("http://schemas.xmlsoap.org/wsdl/", "wsdl");
            reqEnv.declareNamespace("http://www.w3.org/2001/XMLSchema-instance","xsi");

            OMElement operation = omfactory.createOMElement("echoNestedStruct", "http://soapinterop.org/", null);
            SOAPBody body = omfactory.createSOAPBody(reqEnv);
            body.addChild(operation);
            operation.addAttribute("soapenv:encodingStyle", "http://www.w3.org/2003/05/soap-encoding", null);

            OMElement part = omfactory.createOMElement("inputStruct", "", null);
            part.addAttribute("xsi:type", "s:SOAPStructStruct", null);


            OMElement value0 = omfactory.createOMElement("varString", "", null);
            value0.addAttribute("xsi:type", "xsd:string", null);
            value0.addChild(omfactory.createText("strss fdfing1"));

            OMElement value1 = omfactory.createOMElement("varInt", "", null);
            value1.addAttribute("xsi:type", "xsd:int", null);
            value1.addChild(omfactory.createText("25"));

            OMElement value2 = omfactory.createOMElement("varFloat", "", null);
            value2.addAttribute("xsi:type", "xsd:float", null);
            value2.addChild(omfactory.createText("25.23"));

            OMElement value3 = omfactory.createOMElement("varStruct", "", null);

            //OMElement part31 = omfactory.createOMElement("inputStruct", "", null);

            OMElement value30 = omfactory.createOMElement("varString", "", null);
            value30.addAttribute("xsi:type", "xsd:string", null);
            value30.addChild(omfactory.createText("strss fdfing1"));

            OMElement value31 = omfactory.createOMElement("varInt", "", null);
            value31.addAttribute("xsi:type", "xsd:int", null);
            value31.addChild(omfactory.createText("25"));

            OMElement value32 = omfactory.createOMElement("varFloat", "", null);
            value32.addAttribute("xsi:type", "xsd:float", null);
            value32.addChild(omfactory.createText("25.23"));

            value3.addChild(value30);
            value3.addChild(value31);
            value3.addChild(value32);

            part.addChild(value0);
            part.addChild(value1);
            part.addChild(value2);
            part.addChild(value3);

            operation.addChild(part);

            //reqEnv.getBody().addChild(method);
            return reqEnv;

        }

}