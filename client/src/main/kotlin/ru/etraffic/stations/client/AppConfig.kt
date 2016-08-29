package ru.etraffic.stations.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.scheduling.annotation.EnableScheduling
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 *
 * @author Ushmodin N.
 * @since 29.08.2016 14:16
 */
@Configuration
@EnableScheduling
@EnableJpaRepositories
open class AppConfig {

    @Bean
    open fun placeholderConfigurer(): PropertyPlaceholderConfigurer {
        val configurer = PropertyPlaceholderConfigurer()
        configurer.setLocations(ClassPathResource("/config/application.properties"))
        configurer.setIgnoreResourceNotFound(true)
        return configurer
    }

    @Bean
    open fun dataSource(@Value("\${jdbc.url}") url: String,
                        @Value("\${jdbc.username}") username: String,
                        @Value("\${jdbc.password}") password: String,
                        @Value("\${jdbc.nlsLanguage}") nlsLanguage: String,
                        @Value("\${jdbc.nlsTerritory}") nlsTerritory: String,
                        @Value("\${jdbc.nlsNumericCharacters}") nlsNumericCharacters: String
    ) = OraDataSource(url, username, password, nlsLanguage, nlsTerritory, nlsNumericCharacters)

    @Bean
    open fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val emf = LocalContainerEntityManagerFactoryBean()
        emf.dataSource = dataSource
        emf.jpaVendorAdapter = HibernateJpaVendorAdapter()
        emf.setPackagesToScan("ru.etraffic.stations.client")
        emf.persistenceUnitName = "av2"
        return emf
    }

    @Bean
    open fun transactionManager(emf: EntityManagerFactory) = JpaTransactionManager(emf)
}