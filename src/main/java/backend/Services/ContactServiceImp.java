package backend.Services;

import backend.DAO.ContactRepository;
import backend.Entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactServiceImp implements ContactService{
    @Autowired
    ContactRepository contactRepository;
    @Override
    public Contact saveContactMessage(Contact message) {
        message.setId(null);
        return contactRepository.save(message);
    }
}
