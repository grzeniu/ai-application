package pl.edu.wat.ai.app.util;

public class Assert {

    public static void notNull(Object obj, String exceptionMessage){
        if(obj == null){
            throw new IllegalArgumentException(exceptionMessage);
        }
    }
}
