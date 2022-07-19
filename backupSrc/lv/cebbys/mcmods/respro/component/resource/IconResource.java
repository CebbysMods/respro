package lv.cebbys.mcmods.respro.component.resource;

import lv.cebbys.mcmods.respro.imp.function.Factory;

import java.io.InputStream;

public class IconResource extends ImageResource {

    protected Factory<InputStream> streamFactory;

    public IconResource(Factory<InputStream> iconFactory) {
        streamFactory = iconFactory;
    }

    public IconResource(String resourcePath) {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        ClassLoader loader = walker.getCallerClass().getClassLoader();
        streamFactory = () -> loader.getResourceAsStream(resourcePath);
    }

    @Override
    protected InputStream getAsStream() {
        return streamFactory.get();
    }
}
