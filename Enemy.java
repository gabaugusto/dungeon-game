// Classe que representa um inimigo
class Enemy {
    private String name;
    private int health;
    private int attackDamage;

    public Enemy(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public int attack() {
        // Implemente a lógica do ataque do inimigo
        return this.attackDamage;
    }
	
    public void reduceHealth(int damage) {
        this.health -= damage;
    }

    public void increaseHealth(int damage) {
        this.health += damage;
    }    

    public boolean isDefeated() {
        return this.health <= 0;
    }	
}