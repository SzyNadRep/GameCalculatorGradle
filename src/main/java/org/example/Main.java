package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

class PlayersListDTO {
    List<Player> playerList;

    public List<Player> getPlayerList() {
        return playerList;
    }
}

public class Main {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Scanner scanner = new Scanner(System.in);

    private static List<Player> playersLists = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        boolean flagMenu = true;
        while (flagMenu) {
            playersLists = readPlayers();
            printMenu();
            System.out.println("Chose your option:");
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            int select = scanner.nextInt();
            switch (select) {
                case 0:
                    flagMenu = false;
                    break;
                case 1:
                    addNewPlayer();
                    break;
                case 2:
                    addPlayerResult();
                    break;
                case 3:
                    removePlayer();
                    break;
                case 4:
                    addMatchScore();
                    break;
                case 5:
                    savePlayersStats(playersLists);
                    break;
                case 6:
                    sortPlayerByScore();
                    break;
                case 7:
                    showPlayerStats();
                    break;
                case 8:
                    // TODO allTableInfo();
                    break;
                default:
                    System.out.println("Choose form 0 to 8");
                    break;
            }

        }
        scanner.close();
    }

    private static void savePlayersStats(List<Player> playersLists) throws IOException {
        PlayersListDTO playersListDTO = new PlayersListDTO();
        playersListDTO.playerList = playersLists;
        objectMapper.writeValue(new File("/home/sn/IdeaProjects/GamesCalculatorGradle/src/main/java/org/example/player.json"), playersListDTO);
    }

    private static List<Player> readPlayers() throws IOException {
        PlayersListDTO playersListDTO = objectMapper.readValue(new File("/home/sn/IdeaProjects/GamesCalculatorGradle/src/main/java/org/example/player.json"), PlayersListDTO.class);
        return playersListDTO.playerList;
    }

    public static void printMenu() {
        System.out.println("""
                0 - Exit\s
                1 - Add new player\s
                2 - Add result for player\s
                3 - Remove player\s
                4 - Add match score\s
                5 - Save changes\s
                6 - Show table
                7 - Show player stats
                8 - Show all statistic""");
    }

    public static void addNewPlayer() {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter player name:");
            String userName = scanner.next();
            if (userName.isEmpty()) {
                System.out.println("Please write player name!");
            } else {
                playersLists.add(new Player(userName));
                System.out.println("New player has been added");
                System.out.println("Players count: " + playersLists.size());
                flag = false;
            }
        }
    }

    public static void removePlayer() {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter player name:");
            String userName = scanner.next();

            for (int i = 0; i < playersLists.size(); i++) {
                if (userName.equals(playersLists.get(i).getPlayerName())) {
                    playersLists.remove(playersLists.get(i));
                    System.out.println("Player has been deleted!");
                    flag = false;
                } else {
                    System.out.println("Player not found!");
                }
            }
        }
    }

    public static void addPlayerResult() {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter player name:");
            String userName = scanner.next();

            for (Player playersList : playersLists) {
                if (userName.equals(playersList.getPlayerName())) {
                    System.out.println("Please enter the winner:");
                    String predictedWinner = scanner.next();
                    System.out.println("Please enter the score:");
                    String predictedScore = scanner.next();
                    playersList.bet(predictedWinner, predictedScore);
                    System.out.println(playersList.getPlayerName() +
                            " predicted result: " + predictedWinner + " " + predictedScore);
                    System.out.println("Your score has been saved");
                    flag = false;
                }
            }
        }
    }

    public static void addMatchScore() {
        boolean flag = true;
        while (flag) {
            System.out.println("Enter game name");
            String gameName = scanner.next();
            System.out.println("Enter the winner");
            String gameWinner = scanner.next();
            System.out.println("Enter score");
            String gameScore = scanner.next();

            for (Player playersList : playersLists) {
                playersList.awardPoints(new FinalResult(gameWinner, gameScore));
            }

            System.out.println("Score has been given to players!");

            flag = false;
        }

    }

    public static void sortPlayerByScore(){
        playersLists.sort(new Comparator<>() {

            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        System.out.println("Players table:");

        for (int i=0; i < playersLists.size();i++){
            System.out.println((i+1 + ". " + playersLists.get(i).getPlayerName()));
        }
    }

    public static void showPlayerStats(){

        System.out.println("Players stats: ");

        for (Player playersList : playersLists) {
            System.out.println(playersList.getPlayerName() + " has " + playersList.getScore() + " points.");
        }
    }

}



