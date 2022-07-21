package lv.cebbys.mcmods.respro;

import lv.cebbys.mcmods.respro.api.ResproBuilders;
import lv.cebbys.mcmods.respro.api.ResproRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class Respro implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(Respro.class);
    public static final String MODID = "respro";

    @Override
    public void onInitialize() {
        LOGGER.info("Respro - Resource Provider Library has been initialized");
    }

    private static final ResproBuilders _BUILDERS = new ResproBuilders();
    private static final ResproRegistry _REGISTRY = new ResproRegistry();
}
