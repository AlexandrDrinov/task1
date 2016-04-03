package task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import task1.constants.Const;
import task1.annotation.Equal;
import task1.annotation.EqualType;
import task1.entity.Tomato;

public class Analizer {

    private static boolean equalObjects(Object t1, Object t2) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        Tomato tomato1 = (Tomato) t1;
        Tomato tomato2 = (Tomato) t2;

        Field[] fields1 = tomato1.getClass().getDeclaredFields();
        Field[] fields2 = tomato2.getClass().getDeclaredFields();

        int lenghtFields1 = fields1.length;
        int lenghtFields2 = fields2.length;

        //count fields
        int numberFields = 0;
        if (lenghtFields1 == lenghtFields2) {
            numberFields = lenghtFields1;
        }

        for (int i = 0; i < lenghtFields1; i++) {
            fields1[i].setAccessible(true);
            fields2[i].setAccessible(true);

            EqualType et1 = getEqualType(fields1[i]);
            EqualType et2 = getEqualType(fields2[i]);

            if (et1 == null || et2 == null) {
                numberFields--;
            } else {
                boolean bool = compare(et1, et2, fields1[i].get(tomato1), fields2[i].get(tomato2));
                if (bool) {
                    numberFields--;                    
                    //print equal is true
                    print(bool, fields1[i].getName(), fields1[i].get(t1), fields2[i].get(t2), et1, Const.EQUAL);
                } else {                    
                    //print equal is false
                    print(bool, fields1[i].getName(), fields1[i].get(t1), fields2[i].get(t2), et1, Const.NOT_EQUAL);                  
                }
            }
        }
        return numberFields == 0;
    }

    private static void print(boolean bool, Object fieldName, Object field1Value, Object field2Value, EqualType et, String statusEqual) {
        System.out.println(Const.SPACE + Const.SPACE + bool + Const.SPACE + "-" + Const.SPACE
                + fieldName + ":" + field1Value + Const.SPACE + statusEqual + Const.SPACE
                + field2Value + Const.SPACE + "-" + Const.SPACE + Const.COMPARE_TYPE + Const.SPACE
                + et.toString().toLowerCase());
    }

    private static boolean compare(EqualType et1, EqualType et2, Object field1, Object field2) {
        boolean bool = false;
        if ((et1 == et2) && (et1 == EqualType.REFERENCE)) {
            if (field1 == field2) {
                bool = true;
            }
        }
        if ((et1 == et2) && (et1 == EqualType.VALUE)) {
            if (field1.equals(field2)) {
                bool = true;
            }
            
        }
        return bool;
    }

    private static EqualType getEqualType(Field field) throws IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        Annotation a = field.getDeclaredAnnotation(Equal.class);
        EqualType et = null;
        if (a != null) {
            Class<?> type = a.annotationType();
            Method m = type.getMethod(Const.VALUE);
            et = (EqualType) m.invoke(a);
        }
        return et;
    }
}
