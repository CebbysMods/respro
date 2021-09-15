package lv.cebbys.mcmods.celib.respro.imp.builders;

import net.minecraft.util.Identifier;

public class DataPackBuilder extends ResourcePackBuilder<DataPackBuilder> {

    public static DataPackBuilder builder(Identifier id) {
        DataPackBuilder builder = new DataPackBuilder(id);
        return builder.setInstance(builder);
    }

    protected DataPackBuilder(Identifier id) {
        super(id);
    }
}
