package backend.Services;

import backend.DAO.ClientRepository;
import backend.DAO.CommandeRepository;
import backend.DAO.PaymentRepository;
import backend.DAO.ProductItemRepository;
import backend.Entities.Client;
import backend.Entities.Commande;
import backend.Entities.Payment;
import backend.Entities.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class CommandeServiceImp implements CommandeService {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Commande saveOrder(Commande commande, int actionToDo) {
        Commande commande1;
        if (actionToDo == 2){
            Payment p =paymentRepository.save(commande.getPayment());
            commande1 = commandeRepository.findById(commande.getId()).get(); commande1.setPayment(p);
        }else{
            commande.setId(null);

            if(commande.getPayment()!=null){
                paymentRepository.save(commande.getPayment());
            }
            commande.getProductItems().forEach(productItem -> {
                productItem.setId(null);
                productItem.setOrder(commande);
                productItemRepository.save(productItem);
            });
            commande1= commandeRepository.save(commande);
        }
        Payment payment = commande1.getPayment();
        if(payment != null)
            payment.setOrder(null);

        Commande cmde = new Commande();
        cmde.setClient(commande1.getClient());
        cmde.setPayment(payment);
        cmde.setId(commande1.getId());
        cmde.setDate(commande1.getDate());

        commande1.getProductItems().forEach( productItem -> {
            ProductItem pi = productItem;
            pi.setOrder(null);
            cmde.getProductItems().add(pi);
        });
        return cmde;
    }
}
