import java.util.Random;
public class combat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		java.util.Scanner
		tastatur = new java.util.Scanner(System.in);
		//makes the enemy class, which is used in the combat encounter
		class enemy {
			int speed;
			int hp;
			int attack;
			int ac;
		}
		
		System.out.println("As you enter the dank room, You Feel an eerie presence.");
		int enemy_type = rand.nextInt(3);
		
		switch (enemy_type) {
		case 0:
			//for the goblin encounter
			enemy goblin = new enemy();
			goblin.speed = 5;
		}
		
	}

}
