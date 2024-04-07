import React, { useState, useEffect } from 'react';
import Book from './Book';
import './BookList.css';

function BookList() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    const requestOptions = {
      method: 'GET', // 'Get' es válido, pero por convención se usa 'GET' en mayúsculas
    };
    
    // Asegúrate de incluir el protocolo 'http://' en la URL
    fetch('http://localhost:3200/books/getAll', requestOptions)
      .then(response => response.json())
      .then(data => {
        setBooks(data);
      })
      .catch(error => console.error('There was an error!', error));
  }, []);

  return (
    <div className="book-list">
      {books.map((book) => (
        <div key={book.id} className="book-item">
          <Book
            isbn={book.isbn}
            title={book.title}
            price={book.price}
            unitsAvailable={book.unitsAvailable}
            image={book.image}
            // El manejo de la categoría depende de si esta información viene con los datos del libro
          />
        </div>
      ))}
    </div>
  );
}

export default BookList;
