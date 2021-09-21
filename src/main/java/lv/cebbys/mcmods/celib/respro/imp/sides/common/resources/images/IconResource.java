package lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.images;

import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.ImageResource;
import lv.cebbys.mcmods.celib.respro.imp.utilities.Factory;

import java.io.InputStream;

public class IconResource extends ImageResource {

    protected Factory<InputStream> streamFactory;

    public IconResource(Factory<InputStream> iconFactory) {
        streamFactory = iconFactory;
    }

    @Override
    public InputStream getAsStream() {
        return streamFactory.get();
    }
}
