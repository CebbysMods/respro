package lv.cebbys.mcmods.celib.respro.imp.exceptions;

public class RuntimePngNotFoundException extends RuntimeException {
    public RuntimePngNotFoundException() {
        super();
    }
    public RuntimePngNotFoundException(String msg) {
        super(msg);
    }
    public RuntimePngNotFoundException(Throwable e, String msg) {
        super(msg, e);
    }
}
