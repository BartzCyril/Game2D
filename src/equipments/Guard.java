package equipments;

public abstract class Guard extends Equipments {

	protected double guard;
	
	public Guard(int x, int y, double guard,  int numPicture) {
		super(x, y, numPicture);
		this.guard = guard;
	}

	public double getGuard() {
		return guard;
	}
	
}
