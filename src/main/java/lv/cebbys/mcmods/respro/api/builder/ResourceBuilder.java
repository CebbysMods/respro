package lv.cebbys.mcmods.respro.api.builder;

import lv.cebbys.mcmods.respro.component.resource.AbstractResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface ResourceBuilder<I, R extends AbstractResource> {
    @NotNull I getInitializer();

    default void initialize(@NotNull Consumer<I> consumer) {
        consumer.accept(getInitializer());
    }

    void validate() throws ResourceValidationException;

    @NotNull R build();
}
