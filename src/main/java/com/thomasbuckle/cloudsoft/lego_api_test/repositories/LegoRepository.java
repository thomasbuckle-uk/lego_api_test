package com.thomasbuckle.cloudsoft.lego_api_test.repositories;

import com.thomasbuckle.cloudsoft.lego_api_test.entities.LegoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// More learning needed on RepositoryRestResource Annotation
//@RepositoryRestResource(collectionResourceRel = "lego", path = "lego")
//interface LegoRepository extends PagingAndSortingRepository<LegoEntity, Long> {
@Repository
public interface LegoRepository extends CrudRepository<LegoEntity, Long> {

    List<LegoEntity> getLegoEntitiesByLengthEquals(@Param("length") Integer length);

    Integer countLegoEntitiesByLengthEquals(@Param("length") Integer length);

    Integer countByLength(Integer length);

    //As assumption of only using 1 width bricks, only need length
    @Query("SELECT sum(e.length) from LegoEntity e")
    int totalArea();
}
