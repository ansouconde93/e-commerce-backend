package backend.ressources;

import backend.Entities.Client;
import backend.Services.ClientService;
import backend.modele.Tokens;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Api("Client access controller")
public class ClientControllerImp implements ClientController{
    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Operation to save client in data base." +
            " It need saving client in argument")
    @Override
    @PostMapping("/client/save")
    public Client saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @ApiOperation(value = "Operation to refresh access token." +
            " It need refresh token in argument")
    @Override
    @PostMapping("/client/token/refresh")
    public Tokens generateAccessTokenFromRefreshToken(@RequestBody String userRefreshToken) {
        return clientService.generateAccessTokenFromRefreshToken(userRefreshToken);
    }

    @ApiOperation(value = "Operation to get a necessary information about client." +
            " It need username in argument")
    @Override
    @PostMapping("/client")
    public Client getClientByUsername(@RequestBody String username){
       return clientService.getClientByUsername(username);
    }
}
