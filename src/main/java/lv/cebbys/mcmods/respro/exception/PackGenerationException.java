package lv.cebbys.mcmods.respro.exception;

public class PackGenerationException extends RuntimeException {
    public PackGenerationException(String msg, Throwable t) {
        super(msg, t);
    }

    public PackGenerationException(String msg) {
        super(msg);
    }
}
