package lv.cebbys.mcmods.respro.component.resource;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class AbstractStringResource extends AbstractResource {
    public abstract @NotNull("AbstractStringResource content is null") String getAsString();

    @Override
    public @NotNull("AbstractResource stream is null") InputStream getAsStream() {
        return new ByteArrayInputStream(getAsString().getBytes(StandardCharsets.UTF_8));
    }
}
