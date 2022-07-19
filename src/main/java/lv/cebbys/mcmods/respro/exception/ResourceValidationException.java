package lv.cebbys.mcmods.respro.exception;

public class ResourceValidationException extends RuntimeException {
    public ResourceValidationException(String msg, Throwable t) {
        super(msg, t);
    }

    public ResourceValidationException(String msg) {
        super(msg);
    }
}
