/*   Copyright 2004 The Apache Software Foundation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.axis.om;

import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;

import org.apache.axis.om.SOAPFault;


/**
 * An object that represents the contents of the SOAP body
 * element in a SOAP message. A SOAP body element consists of XML data
 * that affects the way the application-specific content is processed.
 * <P>
 * A <code>SOAPBody</code> object contains <code>SOAPBodyElement</code>
 * objects, which have the content for the SOAP body.
 * A <code>SOAPFault</code> object, which carries status and/or
 * error information, is an example of a <code>SOAPBodyElement</code> object.
 *
 */
public interface SOAPBody extends OMElement {

    /**
     * Creates a new <code>SOAPFault</code> object and adds it to
     * this <code>SOAPBody</code> object.
     * @return the new <code>SOAPFault</code> object
     * @throws  org.apache.axis.om.OMException if there is a SOAP error
     */
    public abstract SOAPFault addFault() throws OMException;

    /**
     * Indicates whether a <code>SOAPFault</code> object exists in
     * this <code>SOAPBody</code> object.
     * @return <code>true</code> if a <code>SOAPFault</code> object exists in
     *     this <code>SOAPBody</code> object; <code>false</code>
     *     otherwise
     */
    public abstract boolean hasFault();

    /**
     * Returns the <code>SOAPFault</code> object in this <code>SOAPBody</code>
     * object.
     * @return the <code>SOAPFault</code> object in this <code>SOAPBody</code>
     *    object
     */
    public abstract SOAPFault getFault();

    /**
     * Creates a new <code>SOAPBodyElement</code> object with the
     * specified name and adds it to this <code>SOAPBody</code> object.
     * @param element a <code>OMNamedNode</code> object with the name for the new
     *   <code>SOAPBodyElement</code> object
     * @return the new <code>SOAPBodyElement</code> object
     * @throws OMException  if a SOAP error occurs
     */
    public abstract SOAPBodyElement addBodyElement(SOAPBodyElement element)
        throws OMException;


    /**
     *
     * @param soapFault
     * @throws OMException
     */
    public abstract void addFault(SOAPFault soapFault) throws OMException;

    /**
     * Adds the root node of the DOM <code>Document</code> to this
     * <code>SOAPBody</code> object.
     * <p>
     * Calling this method invalidates the <code>document</code> parameter. The
     * client application should discard all references to this
     * <code>Document</code> and its contents upon calling
     * <code>addDocument</code>. The behavior of an application that continues
     * to use such references is undefined.
     *
     * @param element the <code>Document</code> object whose root node will be
     *              added to this <code>SOAPBody</code>
     * @return the <code>SOAPBodyElement</code> that represents the root node
     *              that was added
     * @throws OMException if the <code>Document</code> cannot be added
     */
    public abstract SOAPBodyElement addDocument(OMElement element) throws OMException;
    }
