package engine;

import java.io.IOException;

public interface DataSubscriber {
    /**
     *  update the data(collections) of the subscriber, get the data from the Web server
     * @throws IOException
     * @throws InterruptedException
     */
    public void update() throws IOException, InterruptedException;
}
