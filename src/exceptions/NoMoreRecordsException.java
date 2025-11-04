package exceptions;

public class NoMoreRecordsException  extends RuntimeException {
    public NoMoreRecordsException(String msg){
        super(msg);
}
}
