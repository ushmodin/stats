#!/usr/bin/env groovy

@GrabConfig(systemClassLoader=true)
@Grab(group='org.postgresql', module='postgresql', version='9.3-1101-jdbc3')
import javax.xml.parsers.SAXParserFactory
import org.xml.sax.helpers.DefaultHandler
import org.xml.sax.*
import groovy.sql.Sql
class Loader {
    def counter = 0
    def db = Sql.newInstance('jdbc:postgresql://localhost:5432/fias', 'fias', 'fias', 'org.postgresql.Driver')
    def items = []
    def sql = """insert into addrobjtypes (LEVEL, SOCRNAME, SCNAME, KOD_T_ST) values (?,?,?,?)"""

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
            case 'addressobjecttype':
                def scname = attrs.getValue('SCNAME')
                if (scname) {
                    loader.add([attrs.getValue('LEVEL') as Integer, attrs.getValue('SOCRNAME'), scname, attrs.getValue('KOD_T_ST') as Integer])
                }
        }
    }

    void endDocument() {
        loader.flush()
    }
}


def sax = SAXParserFactory.newInstance().newSAXParser().XMLReader
sax.contentHandler = new AddrObjHandler()
sax.parse(new InputSource(new FileInputStream('AS_SOCRBASE_20160626_9164a7be-eba9-4d9a-ba52-f609d9a5b6b9.XML')))


