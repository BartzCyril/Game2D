package equipments;

public abstract class Weapons extends Equipments {
	
	protected double attack;
	protected int attack_distance;

	public Weapons(int x, int y, int attack, int attack_distance,  int numPicture) {
		super(x,y,numPicture);
		this.attack = attack;
		this.attack_distance = attack_distance;
	}

	public double getAttack() {
		return attack;
	}

	public int getAttack_distance() {
		return attack_distance;
	}
	
	
	
	

}
