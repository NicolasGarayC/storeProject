package com.store.project.service.imp;

import com.store.project.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserRepository userRepository;

    private final Random random = new Random();
    private CardService cardService;
    Card card = new Card();
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        card.setUser(savedUser);
        int cardNumber = 10001 + random.nextInt(89999);
        card.setCardNumber(cardNumber);
        cardService.saveCard(card);
        return savedUser;
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
}
