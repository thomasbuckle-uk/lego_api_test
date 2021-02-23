package com.thomasbuckle.cloudsoft.lego_api_test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LegoEntity{
    /*
    Future Improvements
    Expand Properties to include Colour
    Implement Shape Interface and possibly extend Rectangle2D Class from java.awt.geom
    Override toString to return info about brick
    Make Class Abstract + Define interface for Lego Brick
*/

    private @Id @GeneratedValue(strategy= GenerationType.AUTO) Long id;
    private Integer length;
    //Width not required at this stage, assumption: Dealing with (1  *width) * (N * Length) type bricks



    protected LegoEntity(){}

    public LegoEntity(Integer length) {
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

}
