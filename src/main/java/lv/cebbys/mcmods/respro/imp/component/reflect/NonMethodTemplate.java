package lv.cebbys.mcmods.respro.imp.component.reflect;

public class NonMethodTemplate<P, R> extends MethodTemplate<P, R> {

    public NonMethodTemplate(Class<R> returnType, String name) {
        super(returnType, name);
    }

    public R invoke(P parent) {
        return super.invoke(parent);
    }
}
