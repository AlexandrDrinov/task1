package task1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import task1.Constants.Const;
import task1.entity.Tomato;
import task1.exception.TomatoException;

public class Runner {

    public static void main(String[] args) {
        try {
            List<Tomato> tomatoList = getTomatos();
            if (!tomatoList.isEmpty()) {
                analize(tomatoList);
            } else {
                System.out.println(Const.NO_OBJECTS_FOR_COMPARE);
            }
        } catch (TomatoException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static List<Tomato> getTomatos() throws TomatoException {
        try {
            Class<GenerateObjects> clGenerateObjects = GenerateObjects.class;
            GenerateObjects genObj = clGenerateObjects.newInstance();
            Field[] fields = clGenerateObjects.getDeclaredFields();
            List<Tomato> tomatoList = new ArrayList<>();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType() == Tomato.class) {
                    Tomato tomato = (Tomato) field.get(genObj);
                    tomatoList.add(tomato);
                }
            }
            return tomatoList;
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            throw new TomatoException(ex);
        }
    }

    private static void analize(List<Tomato> tomatoList) throws TomatoException {
        try {
            Class<Analizer> clAnalizer = Analizer.class;
            Analizer analizer = clAnalizer.newInstance();
            Method m = clAnalizer.getDeclaredMethod(Const.METHOD_EQUAL_OBJECTS, Object.class, Object.class);
            m.setAccessible(true);
            for (int i = 0; i < tomatoList.size(); i++) {
                for (int j = i + 1; j < tomatoList.size(); j++) {
                    
                    //print header - two compare objects
                    System.out.println(Const.COMPARE.toUpperCase()
                            + " " + tomatoList.get(i) + "-" + Const.INDEX + ":" + i + " "
                            + Const.AND.toUpperCase()
                            + " " + tomatoList.get(j) + "-" + Const.INDEX + ":" + j);
                    
                    //class Analizer - start compare method equalObjects (object, Object)
                    boolean bool = (boolean) m.invoke(analizer, tomatoList.get(i), tomatoList.get(j));
                    
                    //print result compare
                    System.out.println(Const.RESULT.toUpperCase() + ":"
                            + " " + Const.TOMATOES + " " + (bool ? Const.ANSI_GREEN + Const.EQUAL_STRING + Const.ANSI_RESET : Const.NOT_EQUAL_STRING) + "\n");
                }
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            throw new TomatoException(ex);
        }
    }
}
