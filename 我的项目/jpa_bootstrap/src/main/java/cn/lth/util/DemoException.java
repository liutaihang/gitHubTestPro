package cn.lth.util;

/**
 *  demo
 */
public class DemoException extends Exception{
    public DemoException(){
        super();
    }

    public DemoException(String msg){
        super(msg);
    }

    public DemoException(String msg, Throwable cause){
        super(msg, cause);
    }
}
