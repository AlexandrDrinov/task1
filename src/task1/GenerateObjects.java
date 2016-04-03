package task1;

import java.lang.reflect.Field;
import task1.constants.Const;
import task1.entity.Seed;
import task1.entity.Tomato;

public class GenerateObjects {

    private final Seed seed1;
    private final Seed seed2;
    
    private final Tomato tomato1;
    private final Tomato tomato2;
    private final Tomato tomato3;
    private final Tomato tomato4;
    private final Tomato tomato5;
    private final Tomato tomato6;
    private final Tomato tomato7;
    private final Tomato tomato8;
    private final Tomato tomato9;
    private final Tomato tomato10;

    public GenerateObjects() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        
            Class<Seed> clSeed= Seed.class;
            
            seed1 = clSeed.newInstance();
            setValues(seed1, Const.SMALL, 10);
            
            seed2 = clSeed.newInstance();
            setValues(seed2, Const.BIG, 5);
        
            Class<Tomato> clTomato = Tomato.class;

            tomato1 = clTomato.newInstance();
            setValues(tomato1, 1, Const.COLOR_RED, Const.VALUE_100, Const.CONDITION_FRESH, seed1);

            tomato2 = clTomato.newInstance();
            setValues(tomato2, 2, Const.COLOR_GREEN, Const.VALUE_100, Const.CONDITION_ROTTEN, seed2);

            tomato3 = clTomato.newInstance();
            setValues(tomato3, 3, Const.COLOR_GREEN, Const.VALUE_90, Const.CONDITION_ROTTEN, seed2);

            tomato4 = clTomato.newInstance();
            setValues(tomato4, 4, Const.COLOR_RED, Const.VALUE_100, Const.CONDITION_FRESH, seed1);

            tomato5 = clTomato.newInstance();
            setValues(tomato5, 5, Const.COLOR_RED, Const.VALUE_80, Const.CONDITION_FRESH, seed1);

            tomato6 = clTomato.newInstance();
            setValues(tomato6, 6, Const.COLOR_GREEN, Const.VALUE_90, Const.CONDITION_ROTTEN, seed2);

            tomato7 = clTomato.newInstance();
            setValues(tomato7, 7, Const.COLOR_GREEN, Const.VALUE_90, Const.CONDITION_FRESH, seed1);

            tomato8 = clTomato.newInstance();
            setValues(tomato8, 8, Const.COLOR_RED, Const.VALUE_80, Const.CONDITION_ROTTEN, seed1);

            tomato9 = clTomato.newInstance();
            setValues(tomato9, 9, Const.COLOR_RED, Const.VALUE_100, Const.CONDITION_FRESH, seed1);

            tomato10 = clTomato.newInstance();
            setValues(tomato10, 10, Const.COLOR_GREEN, Const.VALUE_80, Const.CONDITION_ROTTEN, seed2);        
    }

    private void setValues(Tomato tomato, int id, String color, int weight, String condition, Seed seed) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        setValueField(Const.ID, tomato, id);
        setValueField(Const.COLOR, tomato, color);
        setValueField(Const.WEIGHT, tomato, weight);
        setValueField(Const.CONDITION, tomato, condition);
        setValueField(Const.SEED, tomato, seed);
    }

    private void setValueField(String fieldName, Tomato tomato, int valueField) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = Tomato.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(tomato, valueField);
    }

    private void setValueField(String fieldName, Tomato tomato, String valueField) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = Tomato.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(tomato, valueField);
    }
    
    private void setValueField(String fieldName, Tomato tomato, Seed seed) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = Tomato.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(tomato, seed);
    }
    
    private void setValues(Seed seed, String size, int number) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        setValueField(Const.SIZE, seed, size);
        setValueField(Const.NUMBER, seed, number);        
    }    
    
    private void setValueField(String fieldName, Seed seed, String valueField) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = Seed.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(seed, valueField);
    }
    
    private void setValueField(String fieldName, Seed seed, int valueField) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = Seed.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(seed, valueField);
    }

}
