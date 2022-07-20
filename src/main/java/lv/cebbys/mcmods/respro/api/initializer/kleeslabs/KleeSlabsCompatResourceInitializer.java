package lv.cebbys.mcmods.respro.api.initializer.kleeslabs;

import lv.cebbys.mcmods.respro.component.property.KleeSlabsConverterType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SlabBlock;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("all")
public interface KleeSlabsCompatResourceInitializer {
    @NotNull("KleeSlabsCompatResourceInitializer initializer is null")
    KleeSlabsCompatResourceInitializer setModid(@NotNull("Provided mod id is null") String modid);

    @NotNull("KleeSlabsCompatResourceInitializer initializer is null")
    KleeSlabsCompatResourceInitializer setConverter(@NotNull("Provided slab converter is null") String converter);

    @NotNull("KleeSlabsCompatResourceInitializer initializer is null")
    default KleeSlabsCompatResourceInitializer setConverter(@NotNull("Provided slab converter is null") KleeSlabsConverterType type) {
        return setConverter(type.toString());
    }

    @NotNull("KleeSlabsCompatResourceInitializer initializer is null")
    default KleeSlabsCompatResourceInitializer setConverter(@NotNull("Provided slab converter is null") Class<?> clazz) {
        return setConverter(clazz.getName());
    }

    @NotNull("KleeSlabsCompatResourceInitializer initializer is null")
    KleeSlabsCompatResourceInitializer setSlab(@NotNull("Provided slab id is null") String slab);

    @NotNull("KleeSlabsCompatResourceInitializer initializer is null")
    KleeSlabsCompatResourceInitializer setSlab(@NotNull("Provided slab id is null") SlabBlock slab);

    @NotNull("KleeSlabsCompatResourceInitializer initializer is null")
    default KleeSlabsCompatResourceInitializer setSlab(@NotNull("Provided slab id is null") ResourceLocation slab) {
        return setSlab(slab.toString());
    }


}
