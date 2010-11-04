<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>
    <xsl:template match="/bean">
        /**
        * <xsl:value-of select="@name"/>.java
        *
        * This file was auto-generated from WSDL
        * by the Apache Axis2 version: #axisVersion# #today#
        */
        package <xsl:value-of select="@package"/>;
        <xsl:variable name="name"><xsl:value-of select="@name"/></xsl:variable>

        /**
        *  <xsl:value-of select="$name"/> bean class
        */

        public class <xsl:value-of select="$name"/> <xsl:if test="@extension"> extends <xsl:value-of select="@extension"/></xsl:if>{

        <xsl:choose>
            <xsl:when test="@type">/* This type was generated from the piece of schema that had
                name = <xsl:value-of select="@originalName"/>
                Namespace URI = <xsl:value-of select="@nsuri"/>
                Namespace Prefix = <xsl:value-of select="@nsprefix"/>
                */
            </xsl:when>
            <xsl:otherwise>
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "<xsl:value-of select="@nsuri"/>",
                "<xsl:value-of select="@originalName"/>",
                "<xsl:value-of select="@nsprefix"/>");

            </xsl:otherwise>
        </xsl:choose>


        <xsl:for-each select="property">
            <xsl:variable name="propertyType"><xsl:value-of select="@type"></xsl:value-of></xsl:variable>
            <xsl:variable name="propertyName"><xsl:value-of select="@name"></xsl:value-of></xsl:variable>
            <xsl:variable name="javaName"><xsl:value-of select="@javaname"></xsl:value-of></xsl:variable>
            <xsl:variable name="varName">local<xsl:value-of select="$javaName"/></xsl:variable>
            /**
            * field for <xsl:value-of select="$javaName"/>
            <xsl:if test="@attribute">* This was an Attribute!</xsl:if>
            <xsl:if test="@array">* This was an Array!</xsl:if>

            */
            private <xsl:value-of select="$propertyType"/><xsl:text> </xsl:text><xsl:value-of select="$varName" /> ;

            /**
            * Auto generated getter method
            * @return <xsl:value-of select="$propertyType"/>
            */
            public  <xsl:value-of select="$propertyType"/><xsl:text> </xsl:text>get<xsl:value-of select="$javaName"/>(){
            return <xsl:value-of select="$varName"/>;
            }

            /**
            * Auto generated setter method
            * @param param<xsl:value-of select="$javaName"/>
            */
            public void set<xsl:value-of select="$javaName"/>(<xsl:value-of select="$propertyType"/> param){
            <!--Add the validation code. For now we only add the validation code for arrays-->
            <xsl:if test="@array">
                <xsl:if test="not(@unbound)">
                    if (param.length &gt; <xsl:value-of select="@maxOccurs"></xsl:value-of>){
                    throw new java.lang.RuntimeException();
                    }
                </xsl:if>
                <xsl:if test="@minOccurs">
                    if (param.length &lt; <xsl:value-of select="@minOccurs"></xsl:value-of>){
                    throw new java.lang.RuntimeException();
                    }
                </xsl:if>
            </xsl:if>
            this.<xsl:value-of select="$varName"/>=param;
            }
        </xsl:for-each>
        }
    </xsl:template>
</xsl:stylesheet>