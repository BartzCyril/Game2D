package equipments;

public abstract class Equipments {
	
	protected int x;
	protected int y;
	protected int numPicture;
	
	public Equipments(int x, int y, int numPicture) {
		this.x = x;
		this.y = y;
		this.numPicture = numPicture;
	}

	/**
	 * renvoie le numéro de l'objet présent dans le tableau d'objets dans la class Map
	 * @return
	 */
	public int getNumPicture() {
		return numPicture;
	}
	

}
