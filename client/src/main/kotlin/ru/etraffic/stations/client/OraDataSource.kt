package ru.etraffic.stations.client

import org.apache.commons.dbcp.BasicDataSource
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import java.io.PrintWriter
import java.sql.Connection
import java.util.logging.Logger
import javax.sql.DataSource

/**
 *
 * @author Ushmodin N.
 * @since 29.08.2016 15:58
 */
class OraDataSource: DataSource {

    constructor(
             url: String
            ,username: String
    ,password: String
    ,nlsLanguage: String
    ,nlsTerritory: String
    ,nlsNumericCharacters: String) {
        this.internalDataSource = BasicDataSource()
        internalDataSource.username = username
        internalDataSource.password = password
        internalDataSource.url = url
        internalDataSource.driverClassName = "oracle.jdbc.OracleDriver"
        this.nlsLanguage = nlsLanguage
        this.nlsNumericCharacters = nlsNumericCharacters
        this.nlsTerritory = nlsTerritory

    }
    lateinit var nlsLanguage: String
    lateinit var nlsTerritory: String
    lateinit var nlsNumericCharacters: String
    lateinit var internalDataSource: BasicDataSource



    override fun getConnection(): Connection? {
        val connection = internalDataSource.connection
        alterSession(connection)
        return connection
    }


    override fun getConnection(username: String?, password: String?): Connection? {
        val connection = internalDataSource.getConnection(username, password)
        alterSession(connection)
        return connection
    }

    private fun alterSession(connection: Connection) {
        val st = connection.createStatement()
        st.execute("ALTER SESSION SET NLS_LANGUAGE='$nlsLanguage' NLS_TERRITORY='$nlsTerritory' NLS_NUMERIC_CHARACTERS = '$nlsNumericCharacters'")
        st.close()
    }

    override fun setLogWriter(out: PrintWriter?) {
        internalDataSource.logWriter = out
    }

    override fun setLoginTimeout(seconds: Int) {
        internalDataSource.loginTimeout
    }

    override fun getParentLogger(): Logger? = internalDataSource.parentLogger

    override fun getLogWriter(): PrintWriter? = internalDataSource.logWriter

    override fun getLoginTimeout(): Int = internalDataSource.loginTimeout

    override fun isWrapperFor(iface: Class<*>?): Boolean = internalDataSource.isWrapperFor(iface)

    override fun <T : Any?> unwrap(iface: Class<T>?): T = internalDataSource.unwrap(iface)
}