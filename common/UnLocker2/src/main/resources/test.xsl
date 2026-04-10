<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes"

    <xsl:template match="property[not(exists(@type))]">
        <xsl:element name="{./@name}"/>
    </xsl:template>

</xsl:stylesheet>