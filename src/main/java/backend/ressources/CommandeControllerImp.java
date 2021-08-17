package backend.ressources;

import backend.Entities.Commande;
import backend.Services.CommandeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Api("Order access controller")
public class CommandeControllerImp implements CommandeController{
    @Autowired
    private CommandeService commandeService;

    @ApiOperation(value = "Operation to save order in data base." +
            " It need saving order and action we to do(saving or updating) in argument")
    @Override
    @PostMapping("/orders/{actionToDo}")
    public Commande saveOrder(@RequestBody Commande commande, @PathVariable int actionToDo) {
        return commandeService.saveOrder(commande, actionToDo);
    }
}
