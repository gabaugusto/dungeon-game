import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Room {
    private String name;
    private Map<String, Room> exits;
    private List<Enemy> enemies;
    private List<Treasure> treasures;
    private Trap trap;

    public Room(String name) {
        this.name = name;
        this.exits = new HashMap<>();
        this.enemies = new ArrayList<>();
        this.treasures = new ArrayList<>();
        this.trap = null;
    }

    public String getName() {
        return name;
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Treasure> getTreasures() {
        return treasures;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addTreasure(Treasure treasure) {
        treasures.add(treasure);
    }

    public boolean hasTrap() {
        return trap != null;
    }

    public Trap getTrap() {
        return trap;
    }

    public int activateTrap() {
        if (trap != null) {
            int trapDamage = trap.getDamage();
            trap = null; // Desativa a armadilha após ser ativada
            return trapDamage;
        } else {
            return 0; // Não há armadilha na sala
        }
    }

    public void setTrap(Trap trap) {
        this.trap = trap;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public void removeEnemy(Enemy enemy) {
    }

    // Restante dos métodos da classe Room...
}
