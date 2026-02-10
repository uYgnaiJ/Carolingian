import engine.Engine;

public class Application {
    public static void main(String[] args){
        try{
            Engine.E.start();
        }
        catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }
}
