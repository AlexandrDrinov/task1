package task1.entity;

import task1.Constants.Const;
import task1.annotation.Equal;
import task1.annotation.EqualType;

public class Tomato {

    private int id;

    @Equal(EqualType.REFERENCE)
    private String color;

    @Equal(EqualType.VALUE)
    private int weight;

    @Equal(EqualType.REFERENCE)
    private String condition;
    
    @Equal(EqualType.REFERENCE)
    private Seed seed;

    @Override
    public String toString() {
        return Const.TOMATO.toUpperCase() + "(" 
                + Const.ID + ":" + id + ";" 
                + Const.COLOR + ":" + color + ";" 
                + Const.WEIGHT + ":" + weight + ";" 
                + Const.CONDITION + ":" + condition + ";"
                + Const.SEED + ":" + seed
                + ")";
    }
}
