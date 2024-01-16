package eoz.client.lobbyToTable;

import sharedClasses.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.UUID;

/**
 * Client program for sending requests to the server.
 * This class demonstrates a method to receive a greeting message from the server.
 * To start, execute the main method. Ensure that the server is already running.
 * Note: This is not a JavaFX implementation; it is assumed to run in the background and be invisible to the user.
 */
public class Client implements ClientInter {


    // Attributes
    private UUID clientId = UUID.randomUUID();

    private ServerInter serverStub;

    public String getClientName() {
        return playerName;
    }

    @Override
    public void setTableController(tableController tableController) throws RemoteException {
        updateListener.setTableController(tableController);
    }

    private String playerName;
    private String gameName;
    private UUID gameId;
    private ClientUIUpdateListener updateListener;
    boolean isConnectedToServer = false;

    // Constructor for initializing with player name only
    public Client(String playerName) {
        this.playerName = playerName;
        try {
            updateListener = new ClientUIUpdateListenerImpl();
            updateListener.setClientName(playerName);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    // Getter for clientId
    public UUID getClientId() {
        return clientId;
    }



    // Getter and setter for number of players
    public Integer getNumOfPlayers() throws RemoteException {
        return updateListener.getNumOfPlayers();
    }

    public void setNumOfPlayers(Integer numOfPlayers) {
        try {
            updateListener.setNumOfPlayers(numOfPlayers);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public void setLobbyRoomController(LobbyRoomController lobbyRoomController) {
        try {
            updateListener.setLobbyRoomController(lobbyRoomController);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    // Getter for game name using gameId, handles RemoteException
    public String getGameName(UUID gameId) throws RemoteException {
        try {
            return serverStub.getGameName(gameId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return "Client unable to get Game Name";
        }
    }

    // Setter for game name
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    // Getter and setter for gameId
    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    // Method to add a player to a game
    public void addPlayer(String playerName, UUID gameId) throws RemoteException {
        try {
            serverStub.addPlayer(this.clientId,updateListener,playerName, gameId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to start a game
    public void startGame() throws RemoteException {
        try {
            serverStub.startGame(this.gameId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to create or join a game session
    public void createGameSession() {
        try {
            gameId = serverStub.createGameSession(clientId,updateListener,gameName, playerName, updateListener.getNumOfPlayers());
            System.out.println("Game session created/joined.");
        } catch (Exception e) {
            System.err.println("Error creating/joining game session: ");
            e.printStackTrace();
        }
    }

    // Method to start a game table session
    public boolean startGameTable() {
        try {
            boolean isStartTableGameAllowed = serverStub.checkStartTable(gameId, playerName);
            if (isStartTableGameAllowed) {
                serverStub.startGameTable( gameId);
                System.out.println("Running");
                int numOfPlayers = updateListener.getNumOfPlayers();
                updateListener.setNumOfPlayers(numOfPlayers);
                return  true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error starting game table session: ");
            e.printStackTrace();
            return false;
        }
    }

    // Method to check if a game is ready
    public boolean isGameReady() throws RemoteException {
        try {
            return serverStub.isGameReady(gameId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

// Method to retrieve the player list

    public List<ServerPlayer> getPlayerList() throws RemoteException {
        try {
            return serverStub.getPlayerList(gameId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to check if a game exists
    public boolean doesGameExist(UUID gameId) throws RemoteException {
        try {
            return serverStub.doesGameExit(gameId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to connect to the server
    public void connectToServer() {
        try {
            // Connect to the server's registry
            Registry registry = LocateRegistry.getRegistry(15000);
            System.out.println("Verbindung zum Server hergestellt");

            // Compare the client's interface with the server's registry
            serverStub = (ServerInter) registry.lookup("ServerInter");
            System.out.println("Registry-Bibliothek gefunden");

            // Store and print the response from the server
            String response = serverStub.sayHello();
            System.out.println("Server says: " + response);
            serverStub.registerClient(clientId,updateListener);
            this.isConnectedToServer = true;


        } catch (Exception e) {
            // Handle exceptions in server connection
            System.err.println("Client exception: " + e);
            e.printStackTrace();

        }
    }


    // Additional methods (e.g., fetchRemainingTime) can be uncommented and used as required
}