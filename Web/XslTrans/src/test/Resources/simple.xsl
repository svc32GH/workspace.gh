<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()">
                <xsl:sort select="firstName"/>
            </xsl:apply-templates>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>