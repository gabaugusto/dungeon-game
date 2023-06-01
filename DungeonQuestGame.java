import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DungeonQuestGame {
    private Player player;
    private List<Room> rooms;
    private Room currentRoom;

    public DungeonQuestGame() {
        player = new Player(100, 10);
        rooms = createRooms();
    }

    public void startGame() {
        System.out.println("Bem-vindo ao jogo Dungeon Quest!");

        // Define a sala inicial
        currentRoom = rooms.get(0);

        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println("\nVocê está na sala: " + currentRoom.getName());

            // Verifica se há inimigos na sala
            if (!currentRoom.getEnemies().isEmpty()) {
                System.out.println("Inimigos encontrados!");

                // Realiza ações de batalha
                attackEnemy();
            }

            // Verifica se há tesouros na sala
            if (!currentRoom.getTreasures().isEmpty()) {
                System.out.println("Tesouros encontrados!");

                // Realiza ações de coleta de tesouro
                collectTreasure();
            }

            // Verifica se há armadilha na sala
            if (currentRoom.hasTrap()) {
                System.out.println("Você ativou uma armadilha!");
                int trapDamage = currentRoom.getTrap().activateTrap();
                player.reduceHealth(trapDamage);
                System.out.println("Você sofreu " + trapDamage + " de dano da armadilha!");
            }

            // Verifica se o jogador ainda está vivo
            if (player.isDefeated()) {
                System.out.println("Você foi derrotado! Fim de jogo.");
                gameRunning = false;
                continue;
            }

            // Verifica se o jogador alcançou a última sala
            if (currentRoom == rooms.get(rooms.size() - 1)) {
                System.out.println("Você chegou à sala final e completou o jogo! Parabéns!");
                gameRunning = false;
                continue;
            }

            // Permite ao jogador escolher o próximo movimento
            System.out.println("Escolha seu próximo movimento:");
            System.out.println("1. Mover para uma sala adjacente");
            System.out.println("2. Ver status do jogador");
            System.out.println("3. Desistir do jogo");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (choice) {
                case 1:
                    System.out.println("Escolha uma direção para se mover (norte, sul, leste, oeste):");
                    String direction = scanner.nextLine().toLowerCase();
                    movePlayer(direction);
                    break;
                case 2:
                    showPlayerStatus();
                    break;
                case 3:
                    System.out.println("Você desistiu do jogo. Fim de jogo.");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private void movePlayer(String direction) {
        Room nextRoom = currentRoom.getExits().get(direction);

        if (nextRoom == null) {
            System.out.println("Não há uma sala nessa direção.");
        } else {
            currentRoom = nextRoom;
            System.out.println("Você entrou na sala: " + currentRoom.getName());

            int trapDamage = currentRoom.activateTrap();
            if (trapDamage > 0) {
                System.out.println("Você ativou uma armadilha e sofreu " + trapDamage + " de dano!");
                player.reduceHealth(trapDamage);
            }

            if (currentRoom.getEnemies().isEmpty() && currentRoom.getTreasures().isEmpty()) {
                System.out.println("A sala está vazia. Continue explorando.");
            } else {
                System.out.println("Há inimigos ou tesouros nesta sala. Tome cuidado!");
            }
        }
    }

    private void showPlayerStatus() {
        System.out.println("Status do Jogador:");
        System.out.println("Nome: " + player.getName());
        System.out.println("Saúde: " + player.getHealth());
        System.out.println("----------");
    }

    // Método para criar e desenhar a Dungeon
    private List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();

        // Criação das salas
        Room room1 = new Room("Sala 1");
        Room room2 = new Room("Sala 2");
        Room room3 = new Room("Sala 3");
        Room room4 = new Room("Sala 4");
        Room room5 = new Room("Sala 5");
        Room room6 = new Room("Sala 6");

        // Definição das conexões entre as salas
        room1.setExit("leste", room2);
        room2.setExit("oeste", room1);
        room2.setExit("sul", room3);
        room3.setExit("norte", room2);
        room3.setExit("leste", room4);
        room4.setExit("leste", room5);
        room4.setExit("norte", room6);
        room4.setExit("oeste", room3);
        room5.setExit("oeste", room4);

        // Adição de inimigos às salas
        Enemy enemy1 = new Enemy("Goblin", 20, 5);
        Enemy enemy2 = new Enemy("Orc", 30, 8);
        Enemy enemy3 = new Enemy("Esqueleto", 30, 8);
        Enemy enemy4 = new Enemy("Múmia", 30, 8);
        Enemy enemy5 = new Enemy("Mago do Mal", 50, 20);
        room2.addEnemy(enemy1);
        room3.addEnemy(enemy2);
        room4.addEnemy(enemy4);
        room5.addEnemy(enemy3);
        room6.addEnemy(enemy5);

        // Adição de tesouros às salas
        Treasure treasure1 = new Treasure("Espada", 50);
        Treasure treasure2 = new Treasure("Poção de Cura", 30);
        Treasure treasure3 = new Treasure("Tridente", 1000);
        room1.addTreasure(treasure1);
        room4.addTreasure(treasure2);
        room6.addTreasure(treasure3);

        // Adição de armadilha em uma sala
        Trap trap1 = new Trap("Armadilha de espinhos", 10);
        Trap trap2 = new Trap("Armadilha de Fumaça Venenosa", 20);
        room3.setTrap(trap1);
        room6.setTrap(trap2);

        // Adicione as salas à lista de salas
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);

        return rooms;
    }

    private void attackEnemy() {
        List<Enemy> enemies = currentRoom.getEnemies();
        int enemyCount = enemies.size();

        System.out.println("Selecione o inimigo para atacar:");

        for (int i = 0; i < enemyCount; i++) {
            Enemy enemy = enemies.get(i);
            System.out.println((i + 1) + ". " + enemy.getName() + " (HP: " + enemy.getHealth() + ")");
        }

        Scanner scanner = new Scanner(System.in);
        int enemyChoice = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        if (enemyChoice >= 1 && enemyChoice <= enemyCount) {
            Enemy selectedEnemy = enemies.get(enemyChoice - 1);

            int playerDamage = player.attack(selectedEnemy);
            int enemyDamage = selectedEnemy.attack();

            System.out.println("Você atacou o inimigo " + selectedEnemy.getName() + " e causou " + playerDamage + " de dano.");
            System.out.println("O inimigo " + selectedEnemy.getName() + " contra-atacou e causou " + enemyDamage + " de dano.");

            selectedEnemy.reduceHealth(playerDamage);
            player.reduceHealth(enemyDamage);

            if (selectedEnemy.isDefeated()) {
                System.out.println("Você derrotou o inimigo " + selectedEnemy.getName() + "!");
                enemies.remove(selectedEnemy);
            }

            if (player.isDefeated()) {
                System.out.println("Você foi derrotado! Fim de jogo.");
            }
        } else {
            System.out.println("Escolha inválida. Tente novamente.");
        }
    }

    private void collectTreasure() {
        List<Treasure> treasures = currentRoom.getTreasures();
        int treasureCount = treasures.size();

        System.out.println("Selecione o tesouro para coletar:");

        for (int i = 0; i < treasureCount; i++) {
            Treasure treasure = treasures.get(i);
            System.out.println((i + 1) + ". " + treasure.getName() + " (" + treasure.getValue() + " pontos)");
        }

        Scanner scanner = new Scanner(System.in);
        int treasureChoice = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        if (treasureChoice >= 1 && treasureChoice <= treasureCount) {
            Treasure selectedTreasure = treasures.get(treasureChoice - 1);

            player.collectTreasure(selectedTreasure);
            System.out.println("Você coletou o tesouro " + selectedTreasure.getName() + "!");

            treasures.remove(selectedTreasure);
        } else {
            System.out.println("Escolha inválida. Tente novamente.");
        }
    }

    // Iniciar o jogo
    public static void main(String[] args) {
        DungeonQuestGame game = new DungeonQuestGame();
        game.startGame();
    }
}

