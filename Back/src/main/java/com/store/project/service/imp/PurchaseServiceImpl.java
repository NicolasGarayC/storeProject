package com.store.project.service.imp;

import com.store.project.model.Book;
import com.store.project.model.Card;
import com.store.project.model.dto.BooksPurchaseDTO;
import com.store.project.model.dto.PurchaseDTO;
import com.store.project.repository.BookRepository;
import com.store.project.repository.CardRepository;
import com.store.project.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public String buyBooks(PurchaseDTO books) {
            if (books.getBooks() == null || books.getBooks().isEmpty()) {
                throw new RuntimeException("Please select a book");
            }
            Double totalPrice = 0.00;
            Card card = cardRepository.findByUserId(books.getIdUser());
            if (card == null) {
                throw new RuntimeException("Card not found for User ID: " + books.getIdUser());
            }
            Double balance = card.getBalance();

            List<Book> booksToUpdate = new ArrayList<>();
            for (BooksPurchaseDTO bookPurchaseDTO : books.getBooks()) {
                Optional<Book> bookOptional = bookRepository.findById(bookPurchaseDTO.getBook());
                if (bookOptional.isEmpty()) {
                    throw new RuntimeException("Book not found for ID: " + bookPurchaseDTO.getBook());
                }
                Book tempBook = bookOptional.get();
                if (tempBook.getUnitsAvailable() < bookPurchaseDTO.getUnits()) {
                    throw new RuntimeException("Units not available for the book " + tempBook.getTitle());
                }
                totalPrice += tempBook.getPrice() * bookPurchaseDTO.getUnits();
                tempBook.setUnitsAvailable(tempBook.getUnitsAvailable() - bookPurchaseDTO.getUnits());
                booksToUpdate.add(tempBook);
            }

            if (balance < totalPrice) {
                throw new RuntimeException("User doesn't have enough balance to make the purchase");
            }

            for (Book book : booksToUpdate) {
                bookRepository.save(book);
            }
            card.setBalance(balance - totalPrice);
            cardRepository.save(card);

            return "Purchase successful";
    }
}
