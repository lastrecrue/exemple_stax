<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
               version="1.0"
               xmlns:xs="http://www.w3.org/2001/XMLSchema"
               xmlns:msdata="urn:schemas-microsoft-com:xml-msdata">
  <xsl:output method="text" omit-xml-declaration="yes" />
  <xsl:template match="/">
    <xsl:text>
-- ---------------------------------------------------------------
-- DDL generated from a .Net DataSet XSD via XSLT 
-- Stylesheet by Ann Lewkowicz, 2007
-- ---------------------------------------------------------------
</xsl:text>
    <xsl:apply-templates />
  </xsl:template>
  <!-- Get schema nodes from this schema and any included schemas -->
  <xsl:variable name="contents" select="/|document(//xs:include/@schemaLocation)" />
  <xsl:variable name="complexTypes" select="$contents//xs:complexType[@name]" />
  <!-- Match data table elements -->
  <xsl:template match="*" >
    <xsl:for-each select="$contents" >
      <xsl:apply-templates select=".//xs:element" />
    </xsl:for-each>
  </xsl:template>
  <!--xsl:template match="xs:element[xs:complexType]"-->
  <xsl:template match="xs:element">
    <!-- Get the complex type that defines it, if any -->
    <xsl:variable name="complexType" select="xs:complexType[not(@name)]|$complexTypes[@name=current()/@type]" />
    <xsl:if test="$complexType">
      <!-- Only create a table if the table has at least one simple type'd
    element or attribute.
  -->
      <xsl:variable name="fields"
       select="$complexType/xs:sequence/xs:element[not(@type=$complexTypes/@name)]|$complexType/xs:attribute|$complexType/xs:simpleContent|$complexType/xs:simpleContent/xs:extension/xs:attribute" />
      <xsl:if test="$fields">
        <!-- table name -->
        <xsl:variable name="tableName" select="@name" />
        <!-- Generated primary key name.  We may not use this, depending on
     whether a primary key is declared in the xsd.  -->
        <xsl:variable name="generatedKey" select="concat($tableName, '_id')" />
        <!-- Create statement -->
        <xsl:text>CREATE TABLE dbo.[</xsl:text>
        <xsl:value-of select="$tableName" />
        <xsl:text>] &#10;( &#10;</xsl:text>
        <!-- Get primary key declaration, if any, and use that to determine
     whether to create an Id field -->
        <xsl:variable name="primaryKey"
         select="//xs:key[@msdata:PrimaryKey='true'][xs:selector[@xpath=concat('.//mstns:',$tableName)]]" />
        <!-- Insert ID field -->
        <xsl:if test="not($primaryKey)" >
          <xsl:text>&#9;[</xsl:text>
          <xsl:value-of select="$generatedKey"/>
          <xsl:text>] int Identity(1,1),&#10;</xsl:text>
        </xsl:if>
        <!-- If table is a nested table, insert parent table Id field -->
        <!--   '<xsl:value-of select="$contents//xs:element[@type=current()/ancestor::*/@name]/@name" />'-->
        <xsl:variable name="ancestorTables"
         select="ancestor::xs:element[@name][not(@msdata:IsDataSet)][xs:complexType[xs:sequence[xs:element[@type]] or xs:attribute[@type] or xs:simpleContent]]|$contents//xs:element[@type=current()/ancestor::*/@name]" />
        <xsl:if test="$ancestorTables">
          <xsl:variable name="foreignKey" select="concat( $ancestorTables[1]/@name, '_id' )" />
          <!-- Build foreign key field name -->
          <xsl:text>&#9;[</xsl:text>
          <xsl:value-of select="$foreignKey" />
          <xsl:text>] int</xsl:text>
          <!-- Keywords and key name -->
          <xsl:text> CONSTRAINT FK_</xsl:text>
          <xsl:value-of select="$tableName"/>
          <xsl:text>_</xsl:text>
          <xsl:value-of select="$ancestorTables[1]/@name" />
          <!-- Build reference to parent table -->
          <xsl:text> FOREIGN KEY REFERENCES dbo.[</xsl:text>
          <xsl:value-of select="$ancestorTables[1]/@name" />
          <xsl:text>]</xsl:text>
          <!-- Name of key field of parent -->
          <xsl:text> ( [</xsl:text>
          <xsl:value-of select="$foreignKey" />
          <xsl:text>] ),&#10;</xsl:text>
        </xsl:if>
        <!-- Iterate through fields -->
        <xsl:for-each select="$fields[not(@type=$complexTypes/@name)]">
          <!-- white space -->
          <xsl:text>&#9;</xsl:text>
          <!-- Field name -->
          <xsl:text>[</xsl:text>
          <xsl:choose>
            <xsl:when test="@msdata:ColumnName">
              <xsl:value-of select="@msdata:ColumnName" />
            </xsl:when>
            <xsl:when test="@name">
              <xsl:value-of select="@name" />
            </xsl:when>
            <xsl:when test="local-name()='simpleContent'">
              <xsl:value-of select="concat(parent::*/@name,'_Text')" />
            </xsl:when>
          </xsl:choose>
          <xsl:text>] </xsl:text>
          <!-- Type -->
          <xsl:choose>
            <xsl:when test="@type">
              <xsl:call-template name="Types">
                <xsl:with-param name="type" select="@type" />
              </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
              <xsl:call-template name="Types">
                <xsl:with-param name="type" select="xs:extension/@base" />
              </xsl:call-template>
            </xsl:otherwise>
          </xsl:choose>
          <!-- Handle identity columns -->
          <xsl:if test="@msdata:AutoIncrement='true'">
            <xsl:text> IDENTITY(</xsl:text>
            <xsl:choose>
              <!-- If a seed was specified -->
              <xsl:when test="@msdata:AutoIncrementSeed">
                <xsl:value-of select="@msdata:AutoIncrementSeed"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:text>1</xsl:text>
              </xsl:otherwise>
            </xsl:choose>
            <xsl:text>,</xsl:text>
            <xsl:choose>
              <!-- If a step is specified -->
              <xsl:when test="@msdata:AutoIncrementStep">
                <xsl:value-of select="@msdata:AutoIncrementStep" />
              </xsl:when>
              <xsl:otherwise>
                <xsl:text>1</xsl:text>
              </xsl:otherwise>
            </xsl:choose>
            <xsl:text>)</xsl:text>
          </xsl:if>
          <!-- Optional elements become nullable columns -->
          <xsl:if test="@minOccurs=0"> NULL</xsl:if>
          <!-- separator -->
          <xsl:if test="position()&lt;last()">,</xsl:if>
          <xsl:text>&#10;</xsl:text>
        </xsl:for-each>
        <!-- Primary key.  We determine the primary key by selecting an xs:key
     element that has the msdata:PrimaryKey attribute flag, that has a 
      child xs:selector element that references our table name.  -->
        <xsl:choose>
          <xsl:when test="$primaryKey">
            <!-- White space and keywords -->
            <xsl:text>&#10;&#9;PRIMARY KEY ( </xsl:text>
            <!-- Go through all the fields -->
            <xsl:for-each select="$primaryKey/xs:field">
              <xsl:text>[</xsl:text>
              <xsl:value-of select="substring-after(@xpath, 'mstns:')" />
              <xsl:text>]</xsl:text>
              <xsl:if test="position()&lt;last()">, </xsl:if>
            </xsl:for-each>
            <!-- Closing parenthesis -->
            <xsl:text> )</xsl:text>
          </xsl:when>
          <xsl:otherwise>
            <!-- White space and keywords -->
            <xsl:text>&#10;&#9;PRIMARY KEY ( [</xsl:text>
            <xsl:value-of select="$generatedKey" />
            <xsl:text>] )</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
        <!-- closing parenthesis and white space -->
        <xsl:text>&#10;)&#10;&#10;</xsl:text>
      </xsl:if>
    </xsl:if>
    <xsl:apply-templates />
  </xsl:template>
  <!-- Process the value of the "type" attribute of an element.  -->
  <xsl:template name="Types">
    <xsl:param name="type" />
    <xsl:choose>
      <!-- Process ordinary simple types -->
      <xsl:when test="substring-after($type,':')='int'">int</xsl:when>
      <xsl:when test="substring-after($type, ':')='string'">
        varchar<xsl:if test="local-name()!='restriction'">(255)</xsl:if>
      </xsl:when>
      <xsl:when test="$type='money_type'">money</xsl:when>
      <xsl:when test="substring-after($type,':')='nonNegativeInteger'">int</xsl:when>
      <xsl:when test="substring-after($type,':')='date'">datetime</xsl:when>
      <xsl:when test="substring-after($type,':')='boolean'">bit</xsl:when>
      <xsl:when test="substring-after($type,':')='decimal'">decimal(19,9)</xsl:when>
      <xsl:when test="substring-after($type,':')='gYearMonth'">datetime</xsl:when>
      <!-- If a type is not one of the simple types above, look for a 
    defined simple type and reference its base.  This is recursive,
    so a defined simple type that restricts another defined simple
    type will eventually reach an xsl: data type -->
      <xsl:otherwise>
        <xsl:apply-templates select="$contents//xs:simpleType[@name=$type]" mode="derivedType" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <!-- Handles restriction element of xs:simpleType declaration -->
  <xsl:template match="xs:restriction" mode="derivedType" >
    <xsl:call-template name="Types">
      <xsl:with-param name="type">
        <xsl:value-of select="@base"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:apply-templates mode="derivedType" />
  </xsl:template>
  <!-- Handles max length constraint (for strings) of xs:simpleType declaration -->
  <xsl:template match="xs:maxLength" mode="derivedType" >
    (<xsl:value-of select="@value" />)
  </xsl:template>
  <!-- Create foreign keys.
  For this, we're processing xs:keyref elements.  We determine the 
  child table name from the selector child of the keyref, and the
  parent table name from the key specified in the xs:keyref as the
  key of the parent table.  
-->
  <xsl:template name="ForeignKey" match="xs:keyref[@refer]" >
    <!-- variables -->
    <xsl:variable name="parentKeyName" select="@refer" />
    <xsl:variable name="childTable" select="substring-after(xs:selector[1]/@xpath, './/mstns:')" />
    <xsl:variable name="parentTable" select="substring-after(//xs:key[@name=$parentKeyName][1]/xs:selector[1]/@xpath, './/mstns:')" />
    <!-- Opening keywords -->
    <xsl:text>ALTER TABLE dbo.</xsl:text>
    <!-- Child table being changed -->
    <xsl:value-of select="$childTable" />
    <!-- Whitespace and constraint keywords -->
    <xsl:text>&#10;&#9;ADD CONSTRAINT </xsl:text>
    <!-- Make constraint name -->
    <xsl:text>[FK_</xsl:text>
    <xsl:value-of select="$childTable" />
    <xsl:text>_</xsl:text>
    <xsl:value-of select="$parentTable" />
    <xsl:text>] </xsl:text>
    <!-- Foreign key keyword and parenthesis -->
    <xsl:text>FOREIGN KEY &#10;&#9;( &#10;</xsl:text>
    <!-- Fields in foreign key -->
    <xsl:for-each select="xs:field">
      <xsl:text>&#9;&#9;[</xsl:text>
      <xsl:value-of select="substring-after(@xpath, 'mstns:')" />
      <xsl:text>]</xsl:text>
      <xsl:if test="position()&lt;last()">
        <xsl:text>,</xsl:text>
      </xsl:if>
      <xsl:text>&#10;</xsl:text>
    </xsl:for-each>
    <!-- Closing parenthesis and REFERENCES keyword -->
    <xsl:text>&#9;) &#10;&#9;REFERENCES </xsl:text>
    <!-- Referenced table name -->
    <xsl:text>dbo.</xsl:text>
    <xsl:value-of select="$parentTable" />
    <xsl:text>&#10;&#9;( &#10;</xsl:text>
    <!-- Referenced fields -->
    <xsl:for-each select="//xs:key[@name=$parentKeyName][1]/xs:field">
      <xsl:text>&#9;&#9;[</xsl:text>
      <xsl:value-of select="substring-after(@xpath, 'mstns:')" />
      <xsl:text>]</xsl:text>
      <xsl:if test="position()&lt;last()">
        <xsl:text>,</xsl:text>
      </xsl:if>
      <xsl:text>&#10;</xsl:text>
    </xsl:for-each>
    <!-- Closing parenthesis and white space-->
    <xsl:text>&#9;) &#10;&#10;</xsl:text>
  </xsl:template>
</xsl:stylesheet>