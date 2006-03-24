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

package org.apache.axis2.security.trust;

import org.apache.axis2.context.MessageContext;
import org.apache.ws.commons.om.OMElement;
import org.apache.ws.commons.soap.SOAPEnvelope;

public class TempIssuer implements TokenIssuer {

    public SOAPEnvelope issue(OMElement request, MessageContext msgCtx) throws TrustException {
        // TODO TODO
        throw new UnsupportedOperationException("TODO");
    }

    /* (non-Javadoc)
     * @see org.apache.axis2.security.trust.TokenIssuer#getResponseAction(org.apache.ws.commons.om.OMElement, org.apache.axis2.context.MessageContext)
     */
    public String getResponseAction(OMElement request, MessageContext inMsgCtx) throws TrustException {
        // TODO TODO
        throw new UnsupportedOperationException("TODO");
    }

}
