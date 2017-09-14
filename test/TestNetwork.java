import org.junit.Assert;
import org.junit.Test;
import utility.Network;

import java.io.IOException;

public class TestNetwork {
    @Test
    public void testSendRead() {
        try {
            Network network = new Network("228.5.6.7", 49153);
            System.out.println("host IP: " + network.getIP() + " on port: " + network.getPort());
            network.send(network.getIP(), network.getPort(), "testing 1 2 3");
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
            Network network = new Network("228.5.6.7", 49153);
            network.broadcast("hello world");
            String message = network.listen();
            network.close();

            Assert.assertEquals("hello world", message);

        } catch (IOException exception) {
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void testTwoNetworks() {
        try {
            Network server = new Network("228.5.6.7", 49153);
            Network client = new Network("228.5.6.7", 49153);

            System.out.println("host IP: " + server.getIP() + " on port: " + server.getPort());
            server.broadcast(server.getIP() + "," + server.getPort());

            String[] data = client.listen().split(",");
            String ip = data[0];
            int port = Integer.parseInt(data[1]);

            client.send(ip, port, "testing 1 2 3");

            String message = server.read();

            Assert.assertEquals("testing 1 2 3", message);
            Assert.assertEquals(client.getIP(), server.getLatestReadIP());
            Assert.assertEquals(client.getPort(), server.getLatestReadPort());

            server.close();
            client.close();

        } catch (IOException exception) {
            Assert.fail(exception.getMessage());
        }
    }
}
