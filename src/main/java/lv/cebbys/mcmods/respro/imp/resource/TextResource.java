package lv.cebbys.mcmods.respro.imp.resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public abstract class TextResource extends Resource {
    protected abstract String getAsString();

    @Override
    protected InputStream getAsStream() {
        String data = getAsString();
        if (data == null) return InputStream.nullInputStream();
        return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    }
}
