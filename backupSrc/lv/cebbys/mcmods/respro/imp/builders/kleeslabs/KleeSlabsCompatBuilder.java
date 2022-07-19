package lv.cebbys.mcmods.respro.imp.builders.kleeslabs;

import lv.cebbys.mcmods.respro.imp.builders.Builder;
import lv.cebbys.mcmods.respro.imp.component.KleeSlabsConverterType;
import lv.cebbys.mcmods.respro.component.resource.kleeslabs.KleeSlabsCompatResource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class KleeSlabsCompatBuilder extends Builder<KleeSlabsCompatResource> {
    private String modid;
    private String converter;
    private final Set<String> slabs = new HashSet<>();

    public KleeSlabsCompatBuilder setModid(String m) {
        modid = m;
        return this;
    }

    public KleeSlabsCompatBuilder setModid(Identifier m) {
        return setModid(m == null ? null : m.getNamespace());
    }

    public KleeSlabsCompatBuilder setConverter(KleeSlabsConverterType type) {
        converter = type == null ? null : type.toString();
        return this;
    }

    public KleeSlabsCompatBuilder setConverter(Class<?> type) {
        converter = type == null ? null : type.getName();
        return this;
    }

    public KleeSlabsCompatBuilder addSlab(BlockState block) {
        return addSlab(block == null ? null : block.getBlock());
    }

    public KleeSlabsCompatBuilder addSlab(Block block) {
        return addSlab(Registry.BLOCK.getId(block));
    }

    public KleeSlabsCompatBuilder addSlab(Identifier id) {
        return addSlab(id == null ? null : id.getPath());
    }

    public KleeSlabsCompatBuilder addSlab(String id) {
        slabs.add(id);
        return this;
    }

    @Override
    public void reset() {
        modid = null;
        converter = null;
        slabs.clear();
    }

    @Override
    public KleeSlabsCompatResource build() {
        KleeSlabsCompatResource out = new KleeSlabsCompatResource(modid, converter, new ArrayList<>(slabs));
        reset();
        return out;
    }
}
