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
package org.apache.axis2.om.impl.dom;

import org.apache.axis2.om.OMElement;
import org.apache.axis2.om.OMException;
import org.apache.axis2.om.OMNode;
import org.apache.axis2.om.impl.OMContainerEx;
import org.apache.axis2.om.impl.llom.traverse.OMChildrenIterator;
import org.apache.axis2.om.impl.llom.traverse.OMChildrenQNameIterator;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Iterator;

import javax.xml.namespace.QName;

/**
 * @author Ruchith Fernando (ruchith.fernando@gmail.com)
 */
public abstract class ParentNode extends ChildNode implements OMContainerEx {


	protected ChildNode firstChild;
	
	protected ChildNode lastChild;
	

	
	/**
	 * @param ownerDocument
	 */
	protected ParentNode(DocumentImpl ownerDocument) {
		super(ownerDocument);
	}
	
	protected ParentNode() {
	}
	
	///
	///OMContainer methods
	///
	
	public void addChild(OMNode omNode) {
		this.appendChild((Node)omNode);
	}
	
	
	public void buildNext() {
		if(!this.done)
			builder.next();
	}
	
	public Iterator getChildren() {
		return new OMChildrenIterator(this.firstChild);
	}
	
	/**
	 * Returns an iterator of child nodes having a given qname
	 * @see org.apache.axis2.om.OMContainer#getChildrenWithName(javax.xml.namespace.QName)
	 */
	public Iterator getChildrenWithName(QName elementQName) throws OMException {
		return new OMChildrenQNameIterator(getFirstOMChild(),
                elementQName);
	}
	
	/**
	 * Return the first OMElement child node
	 * @see org.apache.axis2.om.OMContainer#getFirstChildWithName(javax.xml.namespace.QName)
	 */
	public OMElement getFirstChildWithName(QName elementQName)
			throws OMException {
		Iterator children = new OMChildrenQNameIterator(getFirstOMChild(),
                elementQName);
		while (children.hasNext()) {
			OMNode node = (OMNode) children.next();
			
			//Return the first OMElement node that is found
			if(node instanceof OMElement) {
				return (OMElement)node;
			}
		}
		return null;
	}
	
	public OMNode getFirstOMChild() {
		return this.firstChild;
	}
	
	public void setFirstChild(OMNode omNode) {
		this.firstChild = (ChildNode) omNode;
	}
	
	
	///
	///DOM Node methods
	///	
	
	public NodeList getChildNodes() {
		return new NodeListImpl(this, null,null);
	}
	
	public Node getFirstChild() {
		return this.firstChild;
	}
	
	public Node getLastChild() {
		return this.lastChild;
	}
			
	public boolean hasChildNodes() {
		return this.firstChild != null;
	}
	
	/**
	 * Inserts newChild before the refChild
	 * If the refChild is null then the newChild is nade the last child  
	 */
	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
	
		ChildNode newDomChild = (ChildNode)newChild;
		ChildNode refDomChild = (ChildNode)refChild;
		
		if(this == newChild || !isAncestor(newChild)) {
			throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
					DOMMessageFormatter.formatMessage(
							DOMMessageFormatter.DOM_DOMAIN,
							"HIERARCHY_REQUEST_ERR", null));
		}
		
		if(!(this instanceof Document) && !(this.ownerNode == newDomChild.getOwnerDocument())) {
			throw new DOMException(DOMException.WRONG_DOCUMENT_ERR,
					DOMMessageFormatter.formatMessage(
							DOMMessageFormatter.DOM_DOMAIN,
							"WRONG_DOCUMENT_ERR", null));			
		}
		
		if(this.isReadonly()) {
			throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR,
					DOMMessageFormatter.formatMessage(
							DOMMessageFormatter.DOM_DOMAIN,
							"NO_MODIFICATION_ALLOWED_ERR", null));
		}
		
		if(this instanceof Document) {
			if(this.firstChild != null) {
				//Throw exception since there cannot be two document elements
				throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
						DOMMessageFormatter.formatMessage(
								DOMMessageFormatter.DOM_DOMAIN,
								"HIERARCHY_REQUEST_ERR", null));
			} else {
				this.firstChild = newDomChild;
				newDomChild.isFirstChild(true);
				this.lastChild = newDomChild;
				if(newDomChild.parentNode == null) {
					newDomChild.parentNode = this;
				}
				return newDomChild;
			}
		}
		
		if(refChild == null) { //Append the child to the end of the list
			//if there are no children 
			if(this.lastChild == null && firstChild == null ) {
				this.lastChild = newDomChild;
				this.firstChild = newDomChild;
				this.firstChild.isFirstChild(true);
			} else {
				this.lastChild.nextSibling = newDomChild;
				newDomChild.previousSubling = this.lastChild;
			
				this.lastChild = newDomChild;
			}
			if(newDomChild.parentNode == null) {
				newDomChild.parentNode = this;
			}
			return newChild;
		} else {
			Iterator children = this.getChildren(); 
			boolean found = false;
			while(children.hasNext()) {
				ChildNode tempNode = (ChildNode)children.next();
				
				if(tempNode.equals(refChild)) { 
					//RefChild found
					if(tempNode.isFirstChild()) { //If the refChild is the first child
						
						if(newChild instanceof DocumentFragmentimpl) {
							//The new child is a DocumentFragment
							DocumentFragmentimpl docFrag = (DocumentFragmentimpl)newChild;
							this.firstChild = docFrag.firstChild;
							docFrag.lastChild.nextSibling = refDomChild;
							refDomChild.previousSubling = docFrag.lastChild.nextSibling; 
							
						} else {
							
							//Make the newNode the first Child
							this.firstChild = newDomChild;
							
							newDomChild.nextSibling = refDomChild;
							refDomChild.previousSubling = newDomChild;
							
							this.firstChild.isFirstChild(true);
							refDomChild.isFirstChild(false);
							newDomChild.previousSubling = null; //Just to be sure :-)
							
						}
					} else { //If the refChild is not the fist child
						ChildNode previousNode = refDomChild.previousSubling;
						
						if(newChild instanceof DocumentFragmentimpl) {
							//the newChild is a document fragment
							DocumentFragmentimpl docFrag = (DocumentFragmentimpl)newChild;
							
							previousNode.nextSibling = docFrag.firstChild;
							docFrag.firstChild.previousSubling = previousNode;
							
							docFrag.lastChild.nextSibling = refDomChild;
							refDomChild.previousSubling = docFrag.lastChild;
						} else {
							
							previousNode.nextSibling = newDomChild;
							newDomChild.previousSubling = previousNode;
							
							newDomChild.nextSibling = refDomChild;
							refDomChild.previousSubling = newDomChild;
						}
						
					}
					found = true;
					break;
				}
			}
			
			if(!found) {
				throw new DOMException(DOMException.NOT_FOUND_ERR,
						DOMMessageFormatter.formatMessage(
								DOMMessageFormatter.DOM_DOMAIN,
								"NOT_FOUND_ERR", null));
			}
			
			if(newDomChild.parentNode == null) {
				newDomChild.parentNode = this;
			}
			
			return newChild;
		}
	}
	
	/**
	 * Replaces the oldChild with the newChild
	 */
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		ChildNode newDomChild = (ChildNode)newChild;
		ChildNode oldDomChild = (ChildNode)oldChild;
		
		if(this == newChild || !isAncestor(newChild)) {
				throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
						DOMMessageFormatter.formatMessage(
								DOMMessageFormatter.DOM_DOMAIN,
								"HIERARCHY_REQUEST_ERR", null));
		}
		
		if(!this.ownerNode.equals(newDomChild.ownerNode)) {
			throw new DOMException(DOMException.WRONG_DOCUMENT_ERR,
					DOMMessageFormatter.formatMessage(
							DOMMessageFormatter.DOM_DOMAIN,
							"WRONG_DOCUMENT_ERR", null));			
		}
		
		if (this.isReadonly()) { 
			throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR,
					DOMMessageFormatter.formatMessage(
							DOMMessageFormatter.DOM_DOMAIN,
							"NO_MODIFICATION_ALLOWED_ERR", null));
		}
		
		Iterator children = this.getChildren(); 
		boolean found = false;
		while(children.hasNext()) {
			ChildNode tempNode = (ChildNode)children.next();
			if(tempNode.equals(oldChild)) {
				if(newChild instanceof DocumentFragmentimpl) {
					DocumentFragmentimpl docFrag = (DocumentFragmentimpl)newDomChild;
					ChildNode child = (ChildNode)docFrag.getFirstChild();
					child.parentNode = this;
					this.replaceChild(child, oldChild);
//					DocumentFragmentimpl docFrag = (DocumentFragmentimpl)newDomChild;
//					docFrag.firstChild.previousSubling = oldDomChild.previousSubling;
//					
				} else {
					newDomChild.nextSibling = oldDomChild.nextSibling;
					newDomChild.previousSubling = oldDomChild.previousSubling;
					
					oldDomChild.previousSubling.nextSibling = newDomChild;
					
					//If the old child is not the last
					if(oldDomChild.nextSibling != null) {
						oldDomChild.nextSibling.previousSubling = newDomChild;
					} else {
						this.lastChild = newDomChild;
					}
					
					if(newDomChild.parentNode == null) {
						newDomChild.parentNode = this;
					}
					
				}
				found = true;
				
				//remove the old child's references to this tree
				oldDomChild.nextSibling = null;
				oldDomChild.previousSubling = null;
				oldDomChild.parentNode = null;
			}	
		}
		
		
		if(!found) 
			throw new DOMException(DOMException.NOT_FOUND_ERR,
					DOMMessageFormatter.formatMessage(
							DOMMessageFormatter.DOM_DOMAIN,
							"NOT_FOUND_ERR", null));
		
		return oldChild;
	}
	
	
	/**
	 * Removes the given child from the DOM Tree
	 */
	public Node removeChild(Node oldChild) throws DOMException {
		//Check if this node is readonly
		if(this.isReadonly()) {
			throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR,
					DOMMessageFormatter.formatMessage(
							DOMMessageFormatter.DOM_DOMAIN,
							"NO_MODIFICATION_ALLOWED_ERR", null));
		}
		
		//Check if the Child is there
		Iterator children = this.getChildren();
		boolean childFound = false;
		while(children.hasNext()) {
			ChildNode tempNode = (ChildNode)children.next();
			if(tempNode.equals(oldChild)) {
				
				if(tempNode.isFirstChild()) {
					//If this is the first child
					this.firstChild = null;
					this.lastChild = null;
					tempNode.parentNode = null;
				} else if (this.lastChild == tempNode) {
					//not the first child, but the last child 
					ChildNode prevSib = tempNode.previousSubling;
					prevSib.nextSibling = null;
					tempNode.parentNode = null;
					tempNode.previousSubling = null;
				} else {
	
					ChildNode oldDomChild = (ChildNode)oldChild;
					ChildNode privChild = oldDomChild.previousSubling;
					
					privChild.nextSibling = oldDomChild.nextSibling;
					oldDomChild.nextSibling.previousSubling = privChild;
					
					//Remove old child's references to this tree
					oldDomChild.nextSibling = null;
					oldDomChild.previousSubling = null;
				}
				//Child found
				childFound = true;
			}
		}
		
		if(!childFound) 
			throw new DOMException(DOMException.NOT_FOUND_ERR,
					DOMMessageFormatter.formatMessage(
							DOMMessageFormatter.DOM_DOMAIN,
							"NOT_FOUND_ERR", null));
		
		return oldChild;
	}

	
	
	private boolean isAncestor(Node newNode) {
		
		//TODO isAncestor
		return true;
	}

}