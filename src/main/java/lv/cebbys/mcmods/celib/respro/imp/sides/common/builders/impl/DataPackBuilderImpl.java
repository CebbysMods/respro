package lv.cebbys.mcmods.celib.respro.imp.sides.common.builders.impl;

import net.minecraft.util.Identifier;

public class DataPackBuilderImpl extends ResourcePackBuilderImpl<DataPackBuilderImpl> {

    public void addRecipe() {

    }



    public static DataPackBuilderImpl builder(Identifier id) {
        DataPackBuilderImpl builder = new DataPackBuilderImpl(id);
        return builder.setInstance(builder);
    }

    protected DataPackBuilderImpl(Identifier id) {
        super(id);
    }
}
