<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="node()|@*">
    <xsl:template match="../../project/packaging/text()[.='jar']">
      <xsl:text>war</xsl:text>
    </xsl:template>
    <!-- remplacement 'jar' en 'war' -->  
    <xsl:apply-templates select="/"/>
    <!-- copy le document -->
    <xsl:copy>
      <xsl:apply-templates select="node()|@*"/>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
