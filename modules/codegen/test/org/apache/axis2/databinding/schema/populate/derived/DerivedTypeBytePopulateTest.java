package org.apache.axis2.databinding.schema.populate.derived;

import org.apache.axis2.databinding.schema.types.HexBinary;
import org.apache.axis2.databinding.schema.util.ConverterUtil;

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

public class DerivedTypeBytePopulateTest extends AbstractDerivedPopulater{

     private String values[]= {
            "1",
            "0",
            "2"
    };
    private String xmlString[] = {
            "<DerivedByte>"+values[0]+"</DerivedByte>",
            "<DerivedByte>"+values[1]+"</DerivedByte>",
            "<DerivedByte>"+values[2]+"</DerivedByte>"
    };

    protected void setUp() throws Exception {
        className = "org.soapinterop.DerivedByte";
        propertyClass = byte.class;
    }

    // force others to implement this method
    public void testPopulate() throws Exception {
        for (int i = 0; i < values.length; i++) {
            checkValue(xmlString[i],values[i]);
        }
    }

    protected String convertToString(Object o) {
        return ConverterUtil.convertToString((Byte)o);
    }
}