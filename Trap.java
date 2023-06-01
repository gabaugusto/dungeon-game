// Classe que representa uma armadilha
class Trap {
    private String name;
    private int damage;

    public Trap(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void trigger() {
        // Implemente a lógica de ativação da armadilha
    }

    public int activateTrap() {
        // Dano da armadilha
        return 10;
    }
}