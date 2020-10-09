import java.util.Random;
public class combat {

	//makes a function for later for combat
	 public static int fight(int hp, int ac, int speed, int attack, String name){
		java.util.Scanner
		tastatur = new java.util.Scanner(System.in);
		Random rand = new Random();
		// delete later
		int temp_hp = 15; 
		int temp_speed = 1;
		int temp_attack = 6;
		int temp_ac = 16;
		// delete later
		while (hp>0 && temp_hp>0) {
			//player moves first
			if (temp_speed>speed) {
				System.out.print("Do you want to attack? (1)");
				int valg = tastatur.nextInt();
				switch (valg) {
				case 1:
					//player attacks
					int bonus = rand.nextInt(20);
					if (bonus + temp_attack>=ac) {
						int damage = rand.nextInt(7)+1;
						hp = hp - damage;
						System.out.println("You hit the " + name + " and deal " + damage + " damage to it \n");
						System.out.println("enemy hp: " + hp);
						
					}
					//you miss your attack
					else if (bonus+temp_attack<ac){
						System.out.println("You miss your attack \n");
					}
					//error
					else {
						System.out.println("Something went wrong\n");
					}
					break;
					}
				// monster attacks
				int bonus = rand.nextInt(20);
				if (attack + bonus>temp_ac) {
					System.out.println("You are attacked by the " + name + " and it hits \n");
					temp_hp = temp_hp - (attack/2);
					System.out.println("Your hp is now: " + temp_hp + "\n");
				}
				else if (attack + bonus<temp_ac) {
					System.out.println("The " + name + " misses you!");
				}
				}
			//enemy moves first
			else if (speed>temp_speed) {
				int bonus = rand.nextInt(20);
				//the enemy hits
				if (attack + bonus>temp_ac) {
					System.out.println("You are attacked by the " + name + " and it hits \n");
					temp_hp = temp_hp - (attack/2);
					System.out.println("Your hp is now: " + temp_hp + "\n");
				}
				//enemy misses
				else if (attack + bonus<temp_ac) {
					System.out.println("The " + name + " misses you!");
				}
				//player attacks
				System.out.print("Do you want to attack? (1)");
				int valg = tastatur.nextInt();
				switch (valg) {
				case 1:
					if (bonus + temp_attack>=ac) {
						int damage = rand.nextInt(7)+1;
						hp = hp - damage;
						System.out.println("You hit the " + name + " and deal " + damage + " damage to it \n");
						System.out.println("enemy hp: " + hp);
						
					}
					//you miss your attack
					else if (bonus+temp_attack<ac){
						System.out.println("You miss your attack \n");
					}
			}
				}
			
		}
		return temp_hp;
			}
		
		
	

	public static void main(String[] args) {
		Random rand = new Random();
		
		
		
		
		//makes the enemy class, which is used in the combat encounter
		class enemy {
			int speed;
			int hp;
			int attack;
			int ac;
			String name;
		}
		
		System.out.println("As you enter the dank room, You Feel an eerie presence.");
		int enemy_type = rand.nextInt(3);
		
		switch (enemy_type) {
		case 0:
			//stats for the goblin encounter
			enemy goblin = new enemy();
			goblin.speed = 7;
			System.out.println(goblin.speed);
			goblin.hp = 10;
			System.out.println(goblin.hp);
			goblin.attack = 4;
			System.out.println(goblin.attack);
			goblin.ac = 12;
			System.out.println(goblin.ac);
			goblin.name = "Goblin";
			System.out.println(goblin.name);
			fight(goblin.hp, goblin.ac, goblin.speed, goblin.attack, goblin.name);
			break;
		
		case 1:
			//stats for the orc encounter
			enemy orc = new enemy();
			orc.speed = 4;
			System.out.println(orc.speed);
			orc.hp = 14;
			System.out.println(orc.hp);
			orc.attack = 6;
			System.out.println(orc.attack);
			orc.ac = 14;
			System.out.println(orc.ac);
			orc.name = "Orc";
			System.out.println(orc.name);
			fight(orc.hp, orc.ac, orc.speed, orc.attack, orc.name);
			break;
			
		case 2:
			//stats for the mimic encounter
			enemy mimic = new enemy();
			mimic.speed = 2;
			System.out.println(mimic.speed);
			mimic.hp = 16;
			System.out.println(mimic.hp);
			mimic.attack = 5;
			System.out.println(mimic.attack);
			mimic.ac = 15;
			System.out.println(mimic.ac);
			mimic.name = "Mimic";
			System.out.println(mimic.name);
			fight(mimic.hp, mimic.ac, mimic.speed, mimic.attack, mimic.name);
			break;
			
		case 3:
			//stats for the hobgoblin encounter
			enemy hgoblin = new enemy();
			hgoblin.speed = 6;
			System.out.println("enemy speed: " + hgoblin.speed);
			hgoblin.hp = 16;
			System.out.println("enemy hp: " + hgoblin.hp);
			hgoblin.attack = 4;
			System.out.println("enemy attack: " + hgoblin.attack);
			hgoblin.ac = 14;
			System.out.println("Enemy ac: " + hgoblin.ac);
			hgoblin.name = "Hobgoblin";
			System.out.println(hgoblin.name);
			fight(hgoblin.hp, hgoblin.ac, hgoblin.speed, hgoblin.attack, hgoblin.name);
			break;
		}
	}

}
