package org.apache.axis2.rpc.receivers;

import org.apache.axis2.AxisFault;
import org.apache.axis2.om.OMElement;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
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
*
*
*/

/**
 * Author: Deepal Jayasinghe
 * Date: Oct 12, 2005
 * Time: 10:36:58 AM
 */
public class BeanSerializer {

    private Class beanClass;
    private OMElement beanElement;
    private HashMap fields;

    public BeanSerializer(Class beanClass, OMElement beanElement) {
        this.beanClass = beanClass;
        this.beanElement = beanElement;
        this.fields = new HashMap();
        fillMethods();
    }

    public Object deserialize() throws AxisFault {
        Object beanObj =null;
        try {
            beanObj = beanClass.newInstance();
            Iterator elements = beanElement.getChildren();
            while (elements.hasNext()) {
                OMElement parts = (OMElement) elements.next();
                String partsLocalName = parts.getLocalName();

                //getting the setter field
//                Field field = (Field) fields.get(partsLocalName);
                PropertyDescriptor field =(PropertyDescriptor)fields.get(partsLocalName.toLowerCase());
                if (field == null) {
                    throw new AxisFault("User Error , In vaild bean ! field does not exist " + "set" +
                            partsLocalName);
                } else {
                    Class parameters = field.getPropertyType();
                    if (field.equals("class"))
                        continue;
                    Object partObj = SimpleTypeMapper.getSimpleTypeObject(parameters, parts);
                    if (partObj == null) {
                        // Assuming paramter itself as a bean
                        partObj = new BeanSerializer(parameters, parts).deserialize();
                    }
                    Object [] parms = new Object[]{partObj};
//                    field.setAccessible(true);
//                    field.set(beanObj, partObj);
//                    field.setAccessible(false);
                    field.getWriteMethod().invoke(beanObj,parms);
                }


            }
        } catch (InstantiationException e) {
            throw new AxisFault("InstantiationException : " + e, e);
        } catch (IllegalAccessException e) {
            throw new AxisFault("IllegalAccessException : " + e, e);
        } catch (InvocationTargetException e) {
            throw new AxisFault(e);
        }
        return beanObj;
    }

    /**
     * This will fill the hashmap by getting all the methods in a given class , since it make easier
     * to acess latter
     */
    private void fillMethods() {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            PropertyDescriptor [] propDescs = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propDescs.length; i++) {
                PropertyDescriptor field = propDescs[i];
                this.fields.put(field.getName(), field);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
