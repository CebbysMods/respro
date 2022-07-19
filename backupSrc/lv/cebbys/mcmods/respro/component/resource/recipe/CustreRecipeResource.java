package lv.cebbys.mcmods.respro.component.resource.string.recipe;

import lombok.Getter;
import lv.cebbys.mcmods.respro.component.resource.RecipeResource;
import lv.cebbys.mcmods.respro.mapper.JsonPart;
import net.minecraft.block.Block;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Getter
public class CustreRecipeResource extends RecipeResource {
    @JsonPart(value = "ingredient")
    private final Identifier ingredient;
    @JsonPart(value = "result")
    private final Identifier result;

    public CustreRecipeResource(Identifier i, Identifier r) {
        ingredient = i;
        result = r;
    }

    public CustreRecipeResource(Block i, Block r) {
        this(Registry.BLOCK.getId(i), Registry.BLOCK.getId(r));
    }

    @Override
    protected boolean belongsTo(ResourceType type) {
        return ResourceType.SERVER_DATA.equals(type);
    }
}
