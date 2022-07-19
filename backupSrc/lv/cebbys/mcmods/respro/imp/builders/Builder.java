package lv.cebbys.mcmods.respro.imp.builders;

public abstract class Builder<T> {
    public abstract void reset();
    public abstract T build();
}
