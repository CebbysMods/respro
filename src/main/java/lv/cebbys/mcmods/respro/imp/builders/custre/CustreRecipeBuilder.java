package lv.cebbys.mcmods.respro.imp.builders.custre;

import lv.cebbys.mcmods.respro.imp.resource.recipe.CustreRecipeResource;
import net.minecraft.util.Identifier;

public class CustreRecipeBuilder {
    private Identifier ingredient;
    private Identifier result;

    public CustreRecipeBuilder setIngredient(Identifier i) {
        ingredient = i;
        return this;
    }

    public CustreRecipeBuilder setResult(Identifier r) {
        result = r;
        return this;
    }

    public CustreRecipeResource build() {
        if (ingredient == null) throw new RuntimeException("Missing ingredient for custre recipe");
        if (result == null) throw new RuntimeException("Missing result for custre recipe");
        CustreRecipeResource resource = new CustreRecipeResource(ingredient, result);
        ingredient = null;
        result = null;
        return resource;
    }
}
