package lv.cebbys.mcmods.respro.component.resource.image;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lv.cebbys.mcmods.respro.api.initializer.core.ImageResourceInitializer;
import lv.cebbys.mcmods.respro.component.resource.core.AbstractResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.function.Supplier;

@NoArgsConstructor
@AllArgsConstructor
public class ImageResource extends AbstractResource implements ImageResourceInitializer {
    private Supplier<InputStream> streamFactory;

    @Override
    public @NotNull ImageResourceInitializer setFromResources(@Nullable String path) {
        if (path == null || "".equals(path)) {
            streamFactory = InputStream::nullInputStream;
        } else {
            StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
            ClassLoader loader = walker.getCallerClass().getClassLoader();
            streamFactory = () -> loader.getResourceAsStream(path);
        }
        return this;
    }

    @Override
    public void validate() throws ResourceValidationException {
        if (streamFactory == null) throw new ResourceValidationException("ImageResource stream supplier is null");
        if (streamFactory.get() == null) throw new ResourceValidationException("ImageResource stream is null");
    }

    @Override
    public @NotNull InputStream getAsStream() {
        return streamFactory.get();
    }

    @Override
    public boolean belongsTo(PackType type) {
        return type.equals(PackType.CLIENT_RESOURCES);
    }
}
