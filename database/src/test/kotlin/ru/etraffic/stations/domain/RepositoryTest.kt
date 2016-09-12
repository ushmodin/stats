package ru.etraffic.stations.domain

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by nikolay on 26.08.16.
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Configuration::class))
class RepositoryTest {
    @Autowired lateinit var countryRepository: CountryRepository
    @Autowired lateinit var regionRepository: RegionRepository
    @Autowired lateinit var areaRepository: AreaRepository
    @Autowired lateinit var placeRepository: PlaceRepository
    @Autowired lateinit var stationRepository: StationRepository
    @Autowired lateinit var avsHostRepository: AvsHostRepository
    @Autowired lateinit var addrobjRepository: AddrobjRepository
    @Autowired lateinit var addrobjTypeRepository: AddrobjTypeRepository

    @Test
    fun testAllRepositories() {
        Assert.assertTrue(countryRepository.findAll(PageRequest(1, 10)).totalElements > 0)
        Assert.assertTrue(regionRepository.findAll(PageRequest(1, 10)).totalElements > 0)
        Assert.assertTrue(areaRepository.findAll(PageRequest(1, 10)).totalElements > 0)
        Assert.assertTrue(placeRepository.findAll(PageRequest(1, 10)).totalElements > 0)
        Assert.assertTrue(stationRepository.findAll(PageRequest(1, 10)).totalElements > 0)
        Assert.assertTrue(addrobjRepository.findAll(PageRequest(1,10)).totalElements > 0)
        Assert.assertTrue(addrobjTypeRepository.findAll(PageRequest(1,10)).totalElements > 0)
    }
}