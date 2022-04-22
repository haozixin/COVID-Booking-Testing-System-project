package engine;

import java.io.IOException;

public interface DataSubscriber {
    public void update() throws IOException, InterruptedException;
}
