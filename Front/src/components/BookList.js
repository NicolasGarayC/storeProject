import React, { useState, useEffect } from 'react';
import Book from './Book';
import './BookList.css';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Tooltip from '@mui/material/Tooltip';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import Snackbar from '@mui/material/Snackbar';
import CartModal from '../dialogs/CartModal';
import ConfirmDialog from '../dialogs/ConfirmDialog';

function BookList() {
  const [books, setBooks] = useState([]);
  const [searchCriteria, setSearchCriteria] = useState({ id: '', title: '', isbn: '', });
  const [cart, setCart] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isSnackbarOpen, setIsSnackbarOpen] = useState(false);
  const [isConfirmOpen, setIsConfirmOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [filters, setFilters] = useState({
    genre: '',
    priceRange: { min: 0, max: Infinity },
    author: '',
    publicationDate: { start: '', end: '' },
  });

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = () => {
    fetch('http://localhost:3200/books/getAll')
      .then(response => response.json())
      .then(data => {
        console.log("data", data);
        setBooks(data);
      })
      .catch(error => console.error('There was an error!', error));
  };

  const promptPurchaseConfirmation = () => {
    setIsConfirmOpen(true);
  };

  const handleSearch = (e) => {
    e.preventDefault();

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        id: searchCriteria.id ? parseInt(searchCriteria.id, 10) : null,
        title: searchCriteria.title,
        isbn: searchCriteria.isbn,
      }),
    };

    fetch('http://localhost:3200/books/search', requestOptions)
      .then(response => response.json())
      .then(data => {
        setBooks(data.content);
      })
      .catch(error => console.error('There was an error!', error));
  };
  const handleGeneroChange = (e) => {
    setFilters({ ...filters, category: e.target.value });
  };

  const handlePriceRangeChange = (range) => {
    setFilters({ ...filters, priceRange: range });
  };

  const handleAuthorChange = (e) => {
    setFilters({ ...filters, author: e.target.value });
  };

  const handlePublicationDateChange = (dateRange) => {
    setFilters({ ...filters, publicationDate: dateRange });
  };

  const PriceRangeSelector = () => (
    <div style={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
      <TextField
        label="Precio Mínimo"
        type="number"
        value={filters.priceRange.min}
        onChange={(e) => handlePriceRangeChange({ ...filters.priceRange, min: e.target.value })}
        size="small"
      />
      <TextField
        label="Precio Máximo"
        type="number"
        value={filters.priceRange.max}
        onChange={(e) => handlePriceRangeChange({ ...filters.priceRange, max: e.target.value })}
        size="small"
      />
    </div>
  );

  const filteredBooks = books.filter(book => {
    return (
      (filters.genre === '' || book.genre === filters.genre) &&
      book.price >= filters.priceRange.min &&
      book.price <= filters.priceRange.max &&
      (filters.author === '' || book.author.includes(filters.author)) &&
      (filters.publicationDate.start === '' || new Date(book.publication_date) >= new Date(filters.publicationDate.start)) &&
      (filters.publicationDate.end === '' || new Date(book.publication_date) <= new Date(filters.publicationDate.end))
    );
  });

  const addToCart = (book, quantity) => {
    setCart(current => {
      const itemExists = current.find(item => item.book.id === book.id);
      if (itemExists) {
        return current.map(item => {
          if (item.book.id === book.id) {
            return { ...item, quantity: item.quantity + quantity };
          }
          return item;
        });
      }
      return [...current, { book, quantity }];
    });
    setSnackbarMessage('Artículos añadidos al carrito.');
    setIsSnackbarOpen(true);
  };


  const handleSnackbarClose = () => {
    setIsSnackbarOpen(false);
  };

  const completePurchase = () => {
    const purchaseDetails = {
      idUser: 1024594684,
      books: cart.map(item => ({
        book: item.book.id,
        units: item.quantity
      }))
    };
    fetch('http://localhost:3200/shop/purchasebooks', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(purchaseDetails),
    })
      .then(response => {
        if (response.status === 200) {
          setSnackbarMessage('Artículos comprados con éxito.');
          setIsSnackbarOpen(true);
          setCart([]);
          setIsModalOpen(false);
          fetchBooks();
        } else {
          throw new Error('Ocurrió un error al procesar la compra.');
        }
      })
      .catch(error => {
        setSnackbarMessage(error.message);
        setIsSnackbarOpen(true);
      });
  };

  const removeFromCart = (bookId) => {
    setCart(currentCart => currentCart.filter(item => item.book.id !== bookId));
  };
//TODO ARREGLAR DEPENDENCIAS
  return (
    <div className="book-list">
      <div style={{ margin: '20px', display: 'flex', gap: '20px', flexWrap: 'wrap' }}>
        <TextField
          label="Género"
          variant="outlined"
          size="small"
          value={filters.genero}
          onChange={handleGeneroChange}
        />
        <TextField
          label="Autor"
          variant="outlined"
          size="small"
          value={filters.author}
          onChange={handleAuthorChange}
        />
      </div>
      <Tooltip title="Ver Carrito">
        <IconButton onClick={() => setIsModalOpen(true)} style={{ position: 'fixed', top: '10px', right: '10px', zIndex: 1000 }}>
          <ShoppingCartIcon />
        </IconButton>
      </Tooltip>
      <CartModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        cart={cart}
        onCompletePurchase={promptPurchaseConfirmation}
        onRemoveFromCart={removeFromCart}
      />
      <ConfirmDialog
        isOpen={isConfirmOpen}
        handleClose={() => setIsConfirmOpen(false)}
        handleConfirm={() => {
          completePurchase();
          setIsConfirmOpen(false);
        }}
        title="Confirmar Compra"
        message="¿Estás seguro de que deseas realizar la compra?"
      />

      <form onSubmit={handleSearch} style={{ margin: '20px' }}>
        <TextField label="Title" variant="outlined" size="small" style={{ marginRight: '10px' }} value={searchCriteria.title} onChange={(e) => setSearchCriteria({ ...searchCriteria, title: e.target.value })} />
        <TextField label="ISBN" variant="outlined" size="small" value={searchCriteria.isbn} onChange={(e) => setSearchCriteria({ ...searchCriteria, isbn: e.target.value })} />
        <Button type="submit" variant="contained" color="primary" style={{ marginLeft: '10px' }}>Buscar</Button>
      </form>
      <div className="book-container">
        {filteredBooks.map((book) => (
          <div key={book.id} className="book-item">
            <Book {...book} />
            <TextField type="number" InputProps={{ inputProps: { min: 1, max: book.unitsAvailable, defaultValue: 1 } }} size="small" id={`quantity-${book.id}`} variant="outlined" />
            <Tooltip title="Añadir al Carrito">
              <IconButton onClick={() => addToCart(book, parseInt(document.getElementById(`quantity-${book.id}`).value, 10))}>
                <AddShoppingCartIcon />
              </IconButton>
            </Tooltip>
          </div>
        ))}
      </div>
      <Snackbar
        open={isSnackbarOpen}
        autoHideDuration={6000}
        onClose={handleSnackbarClose}
        message={snackbarMessage}
        action={
          <IconButton size="small" aria-label="close" color="inherit" onClick={handleSnackbarClose}>
            <CloseIcon fontSize="small" />
          </IconButton>
        }
      />
    </div>
  );
}

export default BookList;
