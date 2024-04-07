// src/components/Book.js
import React from 'react';

function Book({ isbn, title, price, unitsAvailable, image, category }) {
  return (
    <div style={{ border: '1px solid #ddd', padding: '10px', margin: '10px' }}>
      <img src={image} alt={title} style={{ width: '100px', height: '150px' }} />
      <h2>{title}</h2>
      <p>ISBN: {isbn}</p>
      <p>Precio: ${price}</p>
      <p>Unidades Disponibles: {unitsAvailable}</p>
      {/* Muestra la categoría si está disponible */}
      {category && <p>Categoría: {category.name}</p>}
    </div>
  );
}

export default Book;
