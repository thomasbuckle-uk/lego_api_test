package com.thomasbuckle.cloudsoft.lego_api_test.controllers;

import com.thomasbuckle.cloudsoft.lego_api_test.entities.LegoEntity;
import com.thomasbuckle.cloudsoft.lego_api_test.repositories.LegoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lego")
public class LegoController {

    private final LegoRepository legoRepository;

    public LegoController(LegoRepository legoRepository) {
        this.legoRepository = legoRepository;
    }

    @GetMapping
    public List<LegoEntity> findAllBricks() {
        //Wasn't able to figure this out so just added in a cast
        // Used @Autowire for repository originally but wasn't able to get working in time,
        // as complained about accessing statically/non-statically
        return (List<LegoEntity>) legoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LegoEntity> findBrickById(@PathVariable(value = "id") long id) {
        Optional<LegoEntity> brick = legoRepository.findById(id);
/*
        Originally had this code but Inspection suggested used replacement

        if (brick.isPresent()){return ResponseEntity.ok().body(brick.get());}
        return ResponseEntity.notFound().build();
*/
        return brick.map(legoEntity -> ResponseEntity.ok().body(legoEntity)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public LegoEntity saveNewBrick(@Validated @RequestBody LegoEntity lego){
        return legoRepository.save(lego);
    }


    @PostMapping({"/new"})
    public LegoEntity newBrickByLength(@Validated @RequestBody Integer length){
        return legoRepository.save(new LegoEntity(length));
    }


    @GetMapping("/count/length")
    public String countByLength(@Validated @RequestBody Integer length){
        return "Number of bricks(length 4) = " + legoRepository.countByLength(length);
    }

    @GetMapping("/area/all")
    public String areaAll() {
        return "Total Area: " + legoRepository.totalArea();
    }

}
