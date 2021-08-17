package backend.ressources;

import backend.Entities.Commande;

public interface CommandeController {
    public Commande saveOrder(Commande commande, int actionToDo);
}
