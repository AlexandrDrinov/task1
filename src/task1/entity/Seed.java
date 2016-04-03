package task1.entity;

import task1.Constants.Const;

public class Seed {

    private String size;
    private int number;
    
    @Override
    public String toString() {
        return Const.SEED.toUpperCase() + "(" 
                + Const.SIZE + ":" + size + ";" 
                + Const.NUMBER + ":" + number + ";"                  
                + ")";
    }
}
