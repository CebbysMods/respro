package lv.cebbys.mcmods.respro.api;

import lv.cebbys.mcmods.respro.api.initializer.blockstate.VariantBlockstateResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.custre.CustreRecipeResourceInitializer;
import lv.cebbys.mcmods.respro.component.core.SimpleResourceBuilder;
import lv.cebbys.mcmods.respro.component.resource.core.AbstractResource;
import lv.cebbys.mcmods.respro.component.resource.string.blockstates.VariantBlockstateResource;
import lv.cebbys.mcmods.respro.component.resource.string.custre.CustreRecipeResource;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ResproBuilders {
    private static final BuilderMap BUILDERS = new BuilderMap();

    static {
        // Asset builders
        registerResproSupplier(VariantBlockstateResourceInitializer.class, VariantBlockstateResource::new);

        // Data builders
        registerResproSupplier(CustreRecipeResourceInitializer.class, CustreRecipeResource::new);
    }

    public static <I, R extends AbstractResource> void registerBuilderSupplier(@NotNull Class<I> clazz, @NotNull RS<I, R> instance) {
        registerBuilderSupplier(clazz, 0, instance);
    }

    public static <I, R extends AbstractResource> void registerBuilderSupplier(@NotNull Class<I> clazz, int priority, @NotNull RS<I, R> instance) {
        BUILDERS.set(clazz, priority, instance);
    }

    public static @Nullable <I, A extends ResourceBuilder<I, ?>> A supplyBuilder(@NotNull Class<I> clazz) {
        Supplier<A> builderSupplier = BUILDERS.get(clazz);
        if (builderSupplier == null) return null;
        return builderSupplier.get();
    }

    private static <I, R extends AbstractResource> void registerResproSupplier(@NotNull Class<I> clazz, @NotNull Supplier<R> resourceSupplier) {
        registerBuilderSupplier(clazz, () -> new SimpleResourceBuilder<>(resourceSupplier));
    }

    private static class BuilderMap {
        private final Map<Class<?>, Pair<Integer, Supplier<?>>> map = new HashMap<>();

        public <I, A extends ResourceBuilder<I, ?>> void set(@NotNull Class<I> key, int priority, @NotNull Supplier<A> value) {
            if (!map.containsKey(key)) map.put(key, new ImmutablePair<>(priority, value));
            else if (map.get(key).getKey() < priority) {
                map.put(key, new ImmutablePair<>(priority, value));
            }
        }

        @SuppressWarnings("all")
        public <I, A extends ResourceBuilder<I, ?>> @Nullable Supplier<A> get(Class<I> key) {
            if (!map.containsKey(key)) return null;
            return (Supplier<A>) map.get(key).getValue();
        }
    }

    private interface RS<I, R extends AbstractResource> extends Supplier<ResourceBuilder<I, R>> {
    }
}
