import utility.Network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameApp {
    public static void main(String[] args) {
        try {
            // setup the important main objects and broadcast the server info
            Network network = new Network("228.5.6.7", 49152);
            network.broadcast(network.getIP() + "," + network.getPort());

            List<String> players = new ArrayList<>();

            // WAITING for kids to connect
            int numberOfPlayers = getNumberOfPlayers();
            for (int i = 0; i < numberOfPlayers; ++i) {
                String command = network.read();

                if (command.startsWith("add player")) {
                    String[] tokens = command.split(" ");
                    String name = tokens[2];
                    if (players.contains(name)) {
                        network.send(network.getLatestReadIP(),
                                network.getLatestReadPort(),
                                "error name already in use");
                    } else {
                        players.add(name);
                        network.send(network.getLatestReadIP(),
                                network.getLatestReadPort(),
                                "okay");
                    }
                }
            }

            // TODO PLAY game

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNumberOfPlayers() {
        // assuming user input is valid
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of kids: ");
        return scanner.nextInt();
    }
}
