package ru.etraffic.station.addrobj

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.etraffic.stations.domain.AddrobjRepository
import ru.etraffic.stations.domain.model.Addrobj
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 *
 * @author Ushmodin N.
 * @since 31.08.2016 15:05
 */

@Service("addrobjDbService")
@Transactional
open class DbService @Autowired constructor(val addrobjRepository: AddrobjRepository) {

    open fun list(filter: AddrobjFilter, page: Pageable) = addrobjRepository.findAll(AddrobjSpecification(filter), page).map {
        AddrobjDto (
                guid = it.aoguid,
                offname = it.offname,
                regioncode = it.regioncode,
                areacode = it.areacode,
                placecode = it.placecode,
                okato = it.okato,
                actstatus = it.actstatus,
                currstatus = it.currstatus
        )
    }

}

class AddrobjSpecification(val filter: AddrobjFilter) : Specification<Addrobj> {
    override fun toPredicate(root: Root<Addrobj>?, query: CriteriaQuery<*>?, cb: CriteriaBuilder?): Predicate?
            = cb!!.and(*listOf(filter.guid?.let {
        cb!!.equal(root!!.get<String>("aoguid"), it)
    }, filter.offname?.let {
        cb!!.like(cb!!.lower(root!!.get<String>("offname")), "%${it.toLowerCase()}%")
    }, filter.areacode?.let {
        cb!!.equal(root!!.get<String>("areacode"), it)
    }, filter.regioncode?.let {
        cb!!.equal(root!!.get<String>("regioncode"), it)
    }, filter.areacode?.let {
        cb!!.equal(root!!.get<String>("areacode"), it)
    }, filter.placecode?.let {
        cb!!.equal(root!!.get<String>("placecode"), it)
    }, filter.okato?.let {
        cb!!.like(root!!.get<String>("okato"), "$it%")
    }).filterNotNull().toTypedArray())
}
