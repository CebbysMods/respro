package lv.cebbys.mcmods.respro.imp.builders.datapack;

import lv.cebbys.mcmods.respro.imp.builders.resourcepack.ResourcePackBuilder;
import lv.cebbys.mcmods.respro.imp.resource.RecipeResource;
import lv.cebbys.mcmods.respro.imp.resource.recipe.CustreRecipeResource;
import lv.cebbys.mcmods.respro.imp.utilities.IdentifierUtils;
import net.minecraft.util.Identifier;

public class DataPackBuilder extends ResourcePackBuilder {

    public DataPackBuilder(Identifier id) {
        super(id);
    }

    public void addRecipe(Identifier id, RecipeResource resource) {
        addResource(id, resource);
    }

    public void addCustreRecipe(String id, CustreRecipeResource resource) {
        addCustreRecipe(new Identifier(id), resource);
    }

    public void addCustreRecipe(String namespace, String id, CustreRecipeResource resource) {
        addCustreRecipe(new Identifier(namespace, id), resource);
    }

    public void addCustreRecipe(Identifier id, CustreRecipeResource resource) {
        addRecipe(IdentifierUtils.wrapped("custre/", id, ".json"), resource);
    }
}
