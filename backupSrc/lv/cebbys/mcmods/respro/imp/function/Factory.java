package lv.cebbys.mcmods.respro.imp.function;

@FunctionalInterface
public interface Factory<T> {
    T get();
}
