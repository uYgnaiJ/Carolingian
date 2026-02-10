import java.util.Arrays;

public class ExceptionHandler {
    public static String handle(Exception e){
        String result = Arrays.toString(e.getStackTrace());
        System.out.println(result);
        return result;
    }
}
