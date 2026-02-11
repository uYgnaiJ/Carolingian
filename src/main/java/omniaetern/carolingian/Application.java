package omniaetern.carolingian;

import omniaetern.carolingian.model.Character;

import static omniaetern.carolingian.engine.Engine.E;

public class Application {
    public static void main(String[] args){
        try{
            E.start();
        }
        catch (Exception e){
            ExceptionHandler.handle(e);
        }
    }
}
