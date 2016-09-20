package ru.etraffic.stations.domain

import org.hibernate.search.jpa.Search
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import ru.etraffic.stations.domain.model.Station
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 *
 * @author Ushmodin N.
 * @since 20.09.2016 11:31
 */
@Repository
open class StationRepositoryImpl : StationFullTextRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    override fun searchLike(name: String?
                            , okato: String?
                            , countryName: String?
                            , countryGuid: String?
                            , regionName: String?
                            , regionGuid: String?
                            , areaName: String?
                            , areaGuid: String?
                            , cityName: String?
                            , cityGuid: String?
                            , page: Pageable): Page<Station> {
        val fullTextEntityManager = Search.getFullTextEntityManager(em)
        val builder = fullTextEntityManager.searchFactory.buildQueryBuilder().forEntity(Station::class.java).get()
        val junction = builder.bool()

        name?.let { junction.must(builder.keyword().onField("name").matching(it).createQuery()) }
        okato?.let { junction.must(builder.keyword().wildcard().onField("place.okato").matching(it.substring(0, 5).trimEnd { it == '0' }.plus('*')).createQuery()) }
        countryName?.let { junction.must(builder.keyword().onField("region.country.shortName").matching(it).createQuery()) }
        regionName?.let { junction.must(builder.keyword().onField("region.name").matching(it).createQuery()) }
        areaName?.let { junction.must(builder.keyword().onField("area.name").matching(it).createQuery()) }
        cityName?.let { junction.must(builder.keyword().onField("city.name").matching(it).createQuery()) }

        val query = junction.createQuery()

        val total = fullTextEntityManager
                .createFullTextQuery(query)
                .resultSize;

        val jpa = fullTextEntityManager
                .createFullTextQuery(query, Station::class.java)
                .setMaxResults(page.pageSize)
                .setFirstResult(page.offset)
        val list = jpa.resultList as List<Station>
        return PageImpl(list, page, total.toLong())
    }
}