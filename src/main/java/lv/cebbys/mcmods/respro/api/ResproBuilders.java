package lv.cebbys.mcmods.respro.api;

import lv.cebbys.mcmods.respro.api.builder.ResourceBuilder;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.BlockVariantResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartBlockPropertyResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartBlockstateResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.multipart.MultipartWhenResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.variant.VariantBlockPropertyResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.blockstate.variant.VariantBlockstateResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.custre.CustreRecipeResourceInitializer;
import lv.cebbys.mcmods.respro.api.initializer.kleeslabs.KleeSlabsCompatResourceInitializer;
import lv.cebbys.mcmods.respro.component.core.builder.SimpleResourceBuilder;
import lv.cebbys.mcmods.respro.component.resource.AbstractResource;
import lv.cebbys.mcmods.respro.component.resource.blockstate.BlockVariantResource;
import lv.cebbys.mcmods.respro.component.resource.blockstate.multipart.MultipartBlockPropertyResource;
import lv.cebbys.mcmods.respro.component.resource.blockstate.multipart.MultipartBlockstateResource;
import lv.cebbys.mcmods.respro.component.resource.blockstate.multipart.MultipartWhenResource;
import lv.cebbys.mcmods.respro.component.resource.blockstate.variant.VariantBlockPropertyResource;
import lv.cebbys.mcmods.respro.component.resource.blockstate.variant.VariantBlockstateResource;
import lv.cebbys.mcmods.respro.component.resource.string.custre.CustreRecipeResource;
import lv.cebbys.mcmods.respro.component.resource.string.kleeslabs.KleeSlabsCompatResource;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ResproBuilders {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResproBuilders.class);
    private static final BuilderMap BUILDERS = new BuilderMap();

    static {
        // Asset builders
        registerResproSupplier(VariantBlockstateResourceInitializer.class, VariantBlockstateResource::new);
        registerResproSupplier(VariantBlockPropertyResourceInitializer.class, VariantBlockPropertyResource::new);
        registerResproSupplier(BlockVariantResourceInitializer.class, BlockVariantResource::new);
        registerResproSupplier(MultipartBlockstateResourceInitializer.class, MultipartBlockstateResource::new);
        registerResproSupplier(MultipartWhenResourceInitializer.class, MultipartWhenResource::new);
        registerResproSupplier(MultipartBlockPropertyResourceInitializer.class, MultipartBlockPropertyResource::new);

        // Data builders
        registerResproSupplier(CustreRecipeResourceInitializer.class, CustreRecipeResource::new);
        registerResproSupplier(KleeSlabsCompatResourceInitializer.class, KleeSlabsCompatResource::new);
    }

    public static <I, R extends AbstractResource> void registerBuilderSupplier(@NotNull Class<I> clazz, @NotNull RS<I, R> instance) {
        registerBuilderSupplier(clazz, 0, instance);
    }

    public static <I, R extends AbstractResource> void registerBuilderSupplier(@NotNull Class<I> clazz, int priority, @NotNull RS<I, R> instance) {
        BUILDERS.set(clazz, priority, instance);
    }

    public static @Nullable <I, A extends ResourceBuilder<I, ?>> A supplyBuilder(@NotNull Class<I> clazz) {
        Supplier<A> builderSupplier = BUILDERS.get(clazz);
        if (builderSupplier == null) {
            LOGGER.error("Implementation of {} does not exist or is not registered", clazz.getName());
            LOGGER.error("Skipping all resources build by {}", clazz.getName());
            return null;
        }
        return builderSupplier.get();
    }

    @SuppressWarnings("all")
    public static @Nullable <I, B extends ResourceBuilder<I, ?>> B supplyTypedBuilder(@NotNull Class<I> clazz) {
        ResourceBuilder<I, ?> builder = supplyBuilder(clazz);
        if (builder == null) return null;
        try {
            return (B) builder;
        } catch (Exception ignored) {
            LOGGER.error("Failed to cast {} to required output type", builder.getClass().getName());
            return null;
        }
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
