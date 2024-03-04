package com.store.project.service.imp;

import com.store.project.model.Card;
import com.store.project.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.store.project.model.User;
import com.store.project.repository.UserRepository;
import com.store.project.service.UserService;
import com.store.project.service.CardService;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private final Random random = new Random();
    Card card = new Card();
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        Optional<User> userExist =  getUserById(user.getId());
        if(userExist.isPresent()){
            throw new RuntimeException("User Already Exist.");
        }
        String messageV = validarContrasena(user.getPassword());
        if(messageV!="ok"){
            throw new RuntimeException(messageV);
        }
        User savedUser = userRepository.save(user);
        card.setUser(savedUser);
        int cardNumber = 10001 + random.nextInt(89999);
        Optional<Card> cardP= cardService.getCardById(cardNumber);
        if(cardP.isPresent()){
            cardNumber = 10001 + random.nextInt(89999);
        }
        card.setCardNumber(cardNumber);
        cardService.saveCard(card);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User ID not found.");
        }
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public String validarContrasena(String nuevaContrasenia) {
        if (nuevaContrasenia.length() < 8) {
            return "The password must be at least 8 characters.";
        }
        if (!nuevaContrasenia.matches(".*[A-Z].*")) {
            return "Password must have at least one uppercase letter.";
        }
        if (!nuevaContrasenia.matches(".*[a-z].*")) {
            return "Password must have at least one lowercase letter.";
        }
        if (!nuevaContrasenia.matches(".*[0-9].*")) {
            return "Password must have at least one number.";
        }
        if (!nuevaContrasenia.matches(".*[!@#$%^&()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return "Password must have at least one symbol.";
        }
        return "ok";
    }
}
