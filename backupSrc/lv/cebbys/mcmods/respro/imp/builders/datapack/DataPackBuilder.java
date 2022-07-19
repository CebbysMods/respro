package lv.cebbys.mcmods.respro.imp.builders.datapack;

import lv.cebbys.mcmods.respro.imp.builders.kleeslabs.KleeSlabsCompatBuilder;
import lv.cebbys.mcmods.respro.imp.builders.resourcepack.ResourcePackBuilder;
import lv.cebbys.mcmods.respro.component.resource.RecipeResource;
import lv.cebbys.mcmods.respro.component.resource.kleeslabs.KleeSlabsCompatResource;
import lv.cebbys.mcmods.respro.component.resource.string.recipe.CustreRecipeResource;
import lv.cebbys.mcmods.respro.imp.utility.IdentifierUtils;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

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

    public void addKleeSlabsCompat(String namespace, String id, Consumer<KleeSlabsCompatBuilder> builderConsumer) {
        addKleeSlabsCompat(new Identifier(namespace, id), builderConsumer);
    }

    public void addKleeSlabsCompat(String namespace, String id, KleeSlabsCompatResource resource) {
        addKleeSlabsCompat(new Identifier(namespace, id), resource);
    }

    public void addKleeSlabsCompat(Identifier id, KleeSlabsCompatResource resource) {
        addResource(IdentifierUtils.wrapped("kleeslabs_compat", id, ".json"), resource);
    }

    public void addKleeSlabsCompat(Identifier id, Consumer<KleeSlabsCompatBuilder> builderConsumer) {
        KleeSlabsCompatBuilder builder = new KleeSlabsCompatBuilder();
        builderConsumer.accept(builder);
        addKleeSlabsCompat(id, builder.build());
    }
}
