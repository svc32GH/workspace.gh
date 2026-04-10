<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="yes" method="xml"/>

    <xsl:variable name="const1" select="'!!!   '"/>

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="text()">
        <xsl:if test="starts-with(., '\r\n')">
            <xsl:value-of select="concat($const1, .)"/>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>