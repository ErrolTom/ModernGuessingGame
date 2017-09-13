import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;

public class TestNetwork {
    @Test
    public void testSendRead() {
        try {
            Network network = new Network(49152, "228.5.6.7", 49153);
            System.out.println("host IP: " + network.getHostIP());
            network.send(network.getHostIP(), "testing 1 2 3");
            String message = network.read();
            network.close();

            Assert.assertEquals("testing 1 2 3", message);

        } catch (IOException exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void testBroadcastListen() {
        try {
            Network network = new Network(49152, "228.5.6.7", 49153);
            network.broadcast("hello world");
            String message = network.listen();
            network.close();

            Assert.assertEquals("hello world", message);

        } catch (IOException exception) {
            Assert.fail(exception.getMessage());
        }
    }
}
