// src/components/Book.js
import React from 'react';
import './BookList.css'

function Book({ isbn, title, price, unitsAvailable, image, category }) {
  return (
    <div className="book-single">
      <img src={image} alt={title} style={{ width: '100px', height: '150px' }} />
      <h2>{title}</h2>
      <p>ISBN: {isbn}</p>
      <p>Precio: ${price}</p>
      <p>Unidades Disponibles: {unitsAvailable}</p>
      {category && <p>Categoría: {category.name}</p>}
    </div>
  );
}
      //TODO ARREGLAR CATEGORIA

export default Book;
