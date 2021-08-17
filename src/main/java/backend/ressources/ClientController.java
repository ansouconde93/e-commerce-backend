package backend.ressources;

import backend.Entities.Client;
import backend.modele.Tokens;

public interface ClientController {
    public Client saveClient(Client client);
    public Tokens generateAccessTokenFromRefreshToken(String userRefreshToken);
    public Client getClientByUsername(String username);
}
