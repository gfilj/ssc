package share;

/**
 * session 异常
 */
public class SessionException extends RuntimeException{

    private static final long serialVersionUID = 3232914220215521271L;
    
    public SessionException(){
        
    }
    
    public SessionException(String message){
        super(message);
    }
    
    public SessionException(String message,Throwable throwable){
        super(message,throwable);
    }
    
}
