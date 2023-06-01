import java.util.Scanner;

// Classe que representa o jogador
class Player {

    private String name;
    private int health;
    private int attackDamage;


    public Player(int health, int attackDamage) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Qual o seu nome, pobre alma?");
		this.name = scanner.nextLine();
        this.health = health; // Saúde inicial do jogador
		this.attackDamage = attackDamage; // Dano de ataque do jogador
		
    }

    public int getHealth() {
        return this.health;
    }

    public void reduceHealth(int damage) {
        this.health -= damage;
    }

    public void increaseHealth(int value) {
        this.health += value;
    }


    public boolean isDefeated() {
        return this.health <= 0;
    }


    public void increaseAttackDamage(int value) {
        this.attackDamage += value;
    }
	
    public void decreaseAttackDamage(int value) {
        this.attackDamage -= value;
    }	
	

    public int attack(Enemy enemy) {
        int damageDealt = calculateDamage();

        enemy.reduceHealth(damageDealt);

        return damageDealt;
    }

    private int calculateDamage() {
        // Lógica para calcular o dano do jogador

        // Exemplo simples: Dano fixo de 10
        return 10;
    }

    public void collectTreasure(Treasure selectedTreasure) {
        // Lógica para coletar o tesouro
    }

    public String getName() {
        return this.name;
    }
}
