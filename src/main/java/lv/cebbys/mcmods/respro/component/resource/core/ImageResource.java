package lv.cebbys.mcmods.respro.component.resource.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lv.cebbys.mcmods.respro.api.initializer.core.ImageResourceInitializer;
import lv.cebbys.mcmods.respro.component.resource.AbstractResource;
import lv.cebbys.mcmods.respro.exception.ResourceValidationException;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.function.Supplier;

@NoArgsConstructor
@AllArgsConstructor
public class ImageResource extends AbstractResource implements ImageResourceInitializer {
    private Supplier<InputStream> streamFactory;

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

    @Override
    public @NotNull ImageResourceInitializer setFromResources(@NotNull ClassLoader modClassLoader, @NotNull String path) {
        if ("".equals(path)) {
            streamFactory = InputStream::nullInputStream;
        } else {
            streamFactory = () -> modClassLoader.getResourceAsStream(path);
        }
        return this;
    }

    @Override
    public @NotNull ImageResourceInitializer setFromStream(@NotNull InputStream stream) {
        try (InputStream in = stream; ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            in.transferTo(out);
            out.flush();
            byte[] bytes = out.toByteArray();
            streamFactory = () -> new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            streamFactory = InputStream::nullInputStream;
        }
        return this;
    }
}
