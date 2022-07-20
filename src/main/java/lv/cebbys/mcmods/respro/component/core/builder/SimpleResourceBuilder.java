package lv.cebbys.mcmods.respro.component.core.builder;

import lv.cebbys.mcmods.respro.api.builder.ResourceBuilder;
import lv.cebbys.mcmods.respro.component.resource.AbstractResource;
import lv.cebbys.mcmods.respro.exception.ResourceBuilderException;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SimpleResourceBuilder<I, R extends AbstractResource> implements ResourceBuilder<I, R> {
    private final R resource;

    @SuppressWarnings("all")
    public SimpleResourceBuilder(R resourceBuilder) {
        try {
            I ignored = (I) resourceBuilder;
        } catch (Exception e) {
            throw new ResourceBuilderException(resourceBuilder.getClass().getName() + " class is not implementing initializer", e);
        }
        resource = resourceBuilder;
    }

    public SimpleResourceBuilder(Supplier<R> supplier) {
        this(supplier.get());
    }

    @SuppressWarnings("all")
    @Override
    public @NotNull I getInitializer() {
        return (I) resource;
    }

    @Override
    public void validate() throws ResourceValidationException {
        resource.validate();
    }

    @Override
    public @NotNull R build() {
        return resource;
    }
}
