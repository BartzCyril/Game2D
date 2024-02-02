package equipments;

import java.util.ArrayList;
import entity.Player;

public class Inventaire {
	
	private Equipments[] inventaire;
	private ArrayList<Items> items;
	
	public Inventaire() {
		inventaire = new Equipments[3];
		items = new ArrayList<>();
	}
	
	/**
	 * Cette méthode permet de remplir l'inventaire
	 * indice 0 pour les armes
	 * indice 1 pour le soin
	 * indice 2 pour la protection
	 * et si c'est une pomme ça l'ajoute à la liste items
	 * @param player
	 * @param equipments
	 */
	public void setInventaire(Player player, Equipments equipments) {
		if (equipments instanceof Weapons) {
			inventaire[0] = equipments;
		}
		if (equipments instanceof Items) {
			if (items.size() == 3) {
				items.clear();
				items.add(new GoldApple(player.getX(), player.getY()));
			} else {
				items.add((Items) equipments);
			}
		}
		if (equipments instanceof Care) {
			inventaire[1] = equipments;
		}
		if (equipments instanceof Guard) {
			inventaire[2] = equipments;
		}
	}
	
	public Equipments[] getInventaire() {
		return inventaire;
	}

	public ArrayList<Items> getItems() {
		return items;
	}
	
	public void deleteGoldApple() {
		items.clear();
	}
	
	
	
}
