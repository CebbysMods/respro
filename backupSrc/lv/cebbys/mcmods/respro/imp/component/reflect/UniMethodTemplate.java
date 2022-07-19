package lv.cebbys.mcmods.respro.core.reflect;

public class UniMethodTemplate<P, R, A1> extends MethodTemplate<P, R> {

    public UniMethodTemplate(Class<R> returnType, String name, Class<A1> arg) {
        super(returnType, name, arg);
    }

    public R invoke(P parent, A1 a) {
        return super.invoke(parent, a);
    }
}
