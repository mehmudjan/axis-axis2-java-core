package org.apache.axis2.databinding.schema.populate.simple;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
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

public class SimpleTypeBooleanPopulateTest extends AbstractSimplePopulater{
    private String values[]= {
            "true",
            "false"
    };

    private String xmlString[] = {
            "<booleanParam>"+ values[0] +"</booleanParam>",
            "<booleanParam>"+ values[1] +"</booleanParam>",
    };

    protected void setUp() throws Exception {
        className ="org.soapinterop.booleanParam";
        propertyClass = boolean.class;
    }

    // force others to implement this method
    public void testPopulate() throws Exception {
        for (int i = 0; i < values.length; i++) {
             checkValue(xmlString[i],values[i]);
        }
    }


}