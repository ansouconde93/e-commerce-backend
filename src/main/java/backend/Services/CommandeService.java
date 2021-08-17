package backend.Services;

import backend.Entities.Commande;

public interface CommandeService {
    public Commande saveOrder(Commande commande, int actionToDo);

}
