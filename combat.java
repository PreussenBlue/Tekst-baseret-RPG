import java.util.Random;
public class combat {

	//makes a function for later for combat
	static void fight(int hp, int ac, int speed, int attack){
		java.util.Scanner
		tastatur = new java.util.Scanner(System.in);
		Random rand = new Random();
		// delete later
		int temp_hp = 15; 
		int temp_speed = 10;
		int temp_attack = 6;
		int temp_ac = 16;
		// delete later
		while (hp>0 || temp_hp>0) {
			//player moves first
			if (temp_speed>speed) {
				System.out.print("Do you want to attack? (1) Or rest for a turn? (2)");
				int valg = tastatur.nextInt();
				switch (valg) {
				case 1:
					int bonus = rand.nextInt(20);
					if (bonus + temp_attack>ac) {
						hp = hp-4;
					if (bonus+temp_attack<ac){
						System.out.println("You miss your attack");
					}
					break;
					}
				case 2:
					temp_hp = temp_hp + 2;
					break;
				}
			int bonus = rand.nextInt(20);
			if (attack + bonus>)
			}
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		
		
		
		
		//makes the enemy class, which is used in the combat encounter
		class enemy {
			int speed;
			int hp;
			int attack;
			int ac;
		}
		
		System.out.println("As you enter the dank room, You Feel an eerie presence.");
		int enemy_type = 3;
		
		switch (enemy_type) {
		case 3:
			//for the goblin encounter
			enemy goblin = new enemy();
			goblin.speed = 5;
			System.out.println(goblin.speed);
			goblin.hp = 10;
			System.out.println(goblin.hp);
			goblin.attack = 4;
			System.out.println(goblin.attack);
			goblin.ac = 12;
			System.out.println(goblin.ac);
			
			break;
		
		case 1:
			//for the or
		}
		
	}

}
