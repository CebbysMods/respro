package lv.cebbys.mcmods.respro.component.core.maybe;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Maybe<A> {
    private A value;

    public Maybe(A v) {
        value = v;
    }

    public Maybe<A> provide(A v) {
        value = v;
        return this;
    }

    public <B> Maybe<A> ifNull(B otherValue) {
        if (value == null) return this;
        if (otherValue != null) value = null;
        return this;
    }

    public Maybe<A> ifTrue(boolean bool) {
        if (value == null) return this;
        return ifFalse(!bool);
    }

    public Maybe<A> ifTrue(Function<A, Boolean> bool) {
        if (value == null) return this;
        return ifTrue(bool.apply(value));
    }

    public <B> Maybe<A> ifNotNull(B otherValue) {
        if (value == null) return this;
        if (otherValue == null) value = null;
        return this;
    }

    public Maybe<A> ifFalse(boolean bool) {
        if (value == null) return this;
        if (!bool) value = null;
        return this;
    }

    public Maybe<A> ifFalse(Function<A, Boolean> bool) {
        if (value == null) return this;
        return ifFalse(bool.apply(value));
    }

    public <B> Maybe<B> transform(Function<A, B> function) {
        if (value != null) {
            return new Maybe<>(function.apply(value));
        }
        return new Maybe<>(null);
    }

    public Maybe<A> manipulate(Consumer<A> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
        return this;
    }

    public <B> Maybe<A> manipulate(B parameter, BiConsumer<A, B> consumer) {
        if (value != null) {
            consumer.accept(value, parameter);
        }
        return this;
    }

    public A get() {
        return value;
    }
}
