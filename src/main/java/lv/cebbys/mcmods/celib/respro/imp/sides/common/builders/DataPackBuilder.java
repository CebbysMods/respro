package lv.cebbys.mcmods.celib.respro.imp.sides.common.builders;

import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.recipes.RecipeResource;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.recipes.ShapedCraftingRecipe;
import lv.cebbys.mcmods.celib.respro.imp.utilities.Factory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public interface DataPackBuilder extends ResourcePackBuilder {
    ShapedCraftingRecipe addShapedCraftingRecipe(Identifier id, ShapedCraftingRecipe recipe);
    ShapedCraftingRecipe addShapedCraftingRecipe(Identifier id, Consumer<ShapedCraftingRecipe> recipeType);
    ShapedCraftingRecipe addShapedCraftingRecipe(Identifier id, Factory<ShapedCraftingRecipe> recipeFactory);

    @Deprecated
    RecipeResource addRecipe(Identifier resourcePath, RecipeResource resource);
    @Deprecated
    <T extends RecipeResource> T addRecipe(Identifier resourcePath, Factory<T> resourceFactory);
}
