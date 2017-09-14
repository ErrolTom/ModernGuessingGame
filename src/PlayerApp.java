import utility.Network;

import java.io.IOException;
import java.util.Scanner;

public class PlayerApp {
    public static void main(String[] args) {
        try {
            Network network = new Network("228.5.6.7", 49152);

            // read and record server info
            String[] serverInfo = network.listen().split(",");
            String serverIP = serverInfo[0];
            int serverPort = Integer.parseInt(serverInfo[1]);

            // SETUP player name
            while (true) {
                String name = getName();
                network.send(serverIP, serverPort, "add player " + name);

                String status = network.read();
                if (status.equals("okay")) {
                    break;
                } else {
                    System.out.println(status);
                }
            }

            // TODO WAIT for game to start...

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        return scanner.nextLine();
    }
}
