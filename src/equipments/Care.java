package equipments;

public abstract class Care extends Equipments {

	protected double health;
	
	public Care(int x, int y, double health,  int numPicture) {
		super(x, y, numPicture);
		this.health = health;
	}

	public double getHealth() {
		return health;
	}
	
}
