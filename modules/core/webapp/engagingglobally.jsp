 <%
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
 * Author : Deepal Jayasinghe
 * Date: May 26, 2005
 * Time: 7:14:26 PM
 */
        %>

<%@ page import="org.apache.axis2.Constants,
                 org.apache.axis2.description.ModuleDescription,
                 java.util.Collection,
                 java.util.HashMap,
                 java.util.Iterator"%><html>
<jsp:include page="include/adminheader.jsp"></jsp:include>
<%
    String status = (String)request.getSession().getAttribute(Constants.ENGAGE_STATUS);
%>
<h1>Engage Module Globally</h1>
<p>To engage a module on all services across the system, select a module from the combo box below and click on the "Engage" button. Any module that needs to place handlers into the pre-dispatch phase needs to be engaged globally.</p>
<form method="get" name="engaginModule" action="engagingglobally">
<table border="0" width="100%" cellspacing="1" cellpadding="1">
<tr>
<td width="15%">Select a Module :</td>
<td width="75%" align="left">
<select name="modules">
            <%
                HashMap moduels = (HashMap)request.getSession().getAttribute(Constants.MODULE_MAP);
                Collection moduleCol =  moduels.values();
                for (Iterator iterator = moduleCol.iterator(); iterator.hasNext();) {
                    ModuleDescription axisOperation = (ModuleDescription) iterator.next();
                    String modulename = axisOperation.getName().getLocalPart();
            %> <option  align="left" value="<%=modulename%>"><%=modulename%></option>
             <%
                }
             %>
             </td>
             </tr>
             <tr><td>&nbsp;</td>
             <td>
             <input name="submit" type="submit" value=" Engage " >
             </td>
             </tr>
             </table>
             </form>
              <%
              if(status != null){
                  %>
                  <p><font color="blue"><%=status%></font></p>
                  <%
              } %>
<jsp:include page="include/adminfooter.jsp"></jsp:include>

