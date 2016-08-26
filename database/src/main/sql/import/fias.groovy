#!/usr/bin/env groovy

@GrabConfig(systemClassLoader=true)
@Grab(group='org.postgresql', module='postgresql', version='9.3-1101-jdbc3')
import javax.xml.parsers.SAXParserFactory
import org.xml.sax.helpers.DefaultHandler
import org.xml.sax.*
import groovy.sql.Sql
/*
println """AOGUID,FORMALNAME,REGIONCODE,AUTOCODE\
    ,AREACODE,CITYCODE,CTARCODE,PLACECODE\
    ,STREETCODE,EXTRCODE,SEXTCODE,OFFNAME\
    ,POSTALCODE,IFNSFL,TERRIFNSFL,IFNSUL\
    ,TERRIFNSUL,OKATO,OKTMO,UPDATEDATE\
    ,SHORTNAME,AOLEVEL,PARENTGUID,AOID\
    ,PREVID,NEXTID,CODE,PLAINCODE\
    ,ACTSTATUS,CENTSTATUS,OPERSTATUS,CURRSTATUS\
    ,STARTDATE,ENDDATE,NORMDOC,LIVESTATUS\
    ,CADNUM,DIVTYPE"""
*/

String.metaClass.toDate {
    Sql.in(java.sql.Types.DATE, new Date().parse('yyyy-MM-dd',delegate))
}

class Loader {
    def counter = 0
    def db = Sql.newInstance('jdbc:postgresql://localhost:5432/fias', 'fias', 'fias', 'org.postgresql.Driver')
    def items = []
    def sql = """
insert into addrobj (
    AOGUID,FORMALNAME,REGIONCODE,AUTOCODE
    ,AREACODE,CITYCODE,CTARCODE,PLACECODE
    ,STREETCODE,EXTRCODE,SEXTCODE,OFFNAME
    ,POSTALCODE,IFNSFL,TERRIFNSFL,IFNSUL
    ,TERRIFNSUL,OKATO,OKTMO,UPDATEDATE
    ,SHORTNAME,AOLEVEL,PARENTGUID,AOID
    ,PREVID,NEXTID,CODE,PLAINCODE
    ,ACTSTATUS,CENTSTATUS,OPERSTATUS,CURRSTATUS
    ,STARTDATE,ENDDATE,NORMDOC,LIVESTATUS
    ,CADNUM,DIVTYPE) 
values (
    ?,?,?,?
    ,?,?,?,?
    ,?,?,?,?
    ,?,?,?,?
    ,?,?,?,?
    ,?,?,?,?
    ,?,?,?,?
    ,?,?,?,?
    ,?,?,?,?
    ,?,?)"""

    def add(item) {
        items.add(item)
        if (items.size() >= 10000) {
            flush()
        }
    }
    def flush() {
        load(items)
        counter += items.size()
        items.clear()
        println "Flushed $counter"
    }

    def load(items) {
        db.withTransaction {
            db.withBatch(sql) {batch->
                items.each {
                    batch.addBatch(it)
                }
            }
        }
    }
}


class AddrObjHandler extends DefaultHandler {

    def loader = new Loader()

    void startElement(String ns, String localName, String qName, Attributes attrs) {
        switch (qName.toLowerCase()) {
        case 'object':
            loader.add([attrs.getValue('AOGUID'),attrs.getValue('FORMALNAME'),attrs.getValue('REGIONCODE'),attrs.getValue('AUTOCODE'),
            attrs.getValue('AREACODE'),attrs.getValue('CITYCODE'),attrs.getValue('CTARCODE'),attrs.getValue('PLACECODE'),
            attrs.getValue('STREETCODE'),attrs.getValue('EXTRCODE'),attrs.getValue('SEXTCODE'),attrs.getValue('OFFNAME'),
            attrs.getValue('POSTALCODE'),attrs.getValue('IFNSFL'),attrs.getValue('TERRIFNSFL'),attrs.getValue('IFNSUL'),
            attrs.getValue('TERRIFNSUL'),attrs.getValue('OKATO'),attrs.getValue('OKTMO'),attrs.getValue('UPDATEDATE').toDate(),
            attrs.getValue('SHORTNAME'),attrs.getValue('AOLEVEL') as Integer,attrs.getValue('PARENTGUID'),attrs.getValue('AOID'),
            attrs.getValue('PREVID'),attrs.getValue('NEXTID'),attrs.getValue('CODE'),attrs.getValue('PLAINCODE'),
            attrs.getValue('ACTSTATUS') as Integer,attrs.getValue('CENTSTATUS') as Integer,attrs.getValue('OPERSTATUS') as Integer,attrs.getValue('CURRSTATUS') as Integer,
            attrs.getValue('STARTDATE').toDate(),attrs.getValue('ENDDATE').toDate(),attrs.getValue('NORMDOC'),attrs.getValue('LIVESTATUS') as Short,
            attrs.getValue('CADNUM'),attrs.getValue('DIVTYPE') as Integer])
        }
    }

    void endDocument() {
        loader.flush()
    }
}


def sax = SAXParserFactory.newInstance().newSAXParser().XMLReader
sax.contentHandler = new AddrObjHandler()
sax.parse(new InputSource(new FileInputStream('AS_ADDROBJ_20160626_d9568f02-e3ed-4400-9f7b-63bbd191a158.XML')))


