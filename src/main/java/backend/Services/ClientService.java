package backend.Services;

import backend.Entities.Client;
import backend.modele.Tokens;

public interface ClientService {
    public Client saveClient(Client client);
    public Tokens generateAccessTokenFromRefreshToken(String userRefreshToken);
    public Client getClientByUsername(String username);
}
