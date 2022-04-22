package lv.cebbys.mcmods.respro.imp.resource.recipe;

import lombok.Getter;
import lv.cebbys.mcmods.respro.imp.resource.RecipeResource;
import lv.cebbys.mcmods.respro.imp.parser.JsonPart;
import net.minecraft.block.Block;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Getter
public class CustreRecipeResource extends RecipeResource {
    @JsonPart(path = "ingredient")
    private final Identifier ingredient;
    @JsonPart(path = "result")
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
