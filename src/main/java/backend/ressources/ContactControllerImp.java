package backend.ressources;

import backend.Entities.Contact;
import backend.Services.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Api("Contact us controller")

public class ContactControllerImp implements ContactController{
    @Autowired
    ContactService contactService;

    @ApiOperation(value = "Operation to save user message in data base." +
            " It need saving message in argument")
    @Override
    @PostMapping("/contactus")
    public Contact saveContactMessage(@RequestBody Contact message) {
        return  contactService.saveContactMessage(message);
    }
}
