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
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';


function BookList() {
  const [books, setBooks] = useState([]);
  const [searchCriteria, setSearchCriteria] = useState({
    id: null,
    isbn: null,
    title: '',
    minPrice: 0,
    maxPrice: Infinity,
    author: '',
    publicationDateStart: '',
    publicationDateEnd: '',
  });
  const [cart, setCart] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isSnackbarOpen, setIsSnackbarOpen] = useState(false);
  const [isConfirmOpen, setIsConfirmOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');

  const handleDateChange = (field, date) => {
    setSearchCriteria({ ...searchCriteria, [field]: date });
  };

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
    const formattedStartDate = searchCriteria.publicationDateStart ?
      new Date(searchCriteria.publicationDateStart).toISOString().split('T')[0] : null;
    const formattedEndDate = searchCriteria.publicationDateEnd ?
      new Date(searchCriteria.publicationDateEnd).toISOString().split('T')[0] : null;

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        id: searchCriteria.id ? parseInt(searchCriteria.id, 10) : null,
        title: searchCriteria.title,
        isbn: searchCriteria.isbn,
        minPrice: searchCriteria.minPrice,
        maxPrice: searchCriteria.maxPrice,
        author: searchCriteria.author,
        publicationDateStart: formattedStartDate,
        publicationDateEnd: formattedEndDate,
      }),
    };

    fetch('http://localhost:3200/books/search', requestOptions)
      .then(response => response.json())
      .then(data => {
        setBooks(data.content);
      })
      .catch(error => console.error('There was an error!', error));
  };

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
          throw new Error(response);
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

  return (
    <div className="book-list">
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
      <div className='form-div'>
        <form onSubmit={handleSearch} style={{ margin: '20px' }}>
          <div className="date-picker-container">

            <div className="date-picker">
              <TextField label="Título" variant="outlined" size="small" style={{ marginRight: '10px' }}
                value={searchCriteria.title}
                onChange={(e) => setSearchCriteria({ ...searchCriteria, title: e.target.value })} />
            </div>
            <div className="date-picker">
              <TextField label="ISBN" variant="outlined" size="small" value={searchCriteria.isbn}
                onChange={(e) => setSearchCriteria({ ...searchCriteria, isbn: e.target.value })} />

            </div>
            <div className="date-picker">
              <TextField label="Precio Mínimo" type="number" variant="outlined" size="small" style={{ marginRight: '10px' }}
                value={searchCriteria.minPrice}
                onChange={(e) => setSearchCriteria({ ...searchCriteria, minPrice: e.target.value })} />

            </div>
            <div className="date-picker">
              <TextField label="Precio Máximo" type="number" variant="outlined" size="small" style={{ marginRight: '10px' }}
                value={searchCriteria.maxPrice}
                onChange={(e) => setSearchCriteria({ ...searchCriteria, maxPrice: e.target.value })} />
            </div>
            <div className="date-picker">
              <TextField label="Autor" variant="outlined" size="small" style={{ marginRight: '10px' }}
                value={searchCriteria.author}
                onChange={(e) => setSearchCriteria({ ...searchCriteria, author: e.target.value })} />
            </div>


            <div className="date-picker">
              <p>Fecha de Publicación Desde:</p>
              <DatePicker
                className="custom-date-picker"
                selected={searchCriteria.publicationDateStart}
                onChange={(date) => handleDateChange('publicationDateStart', date)}
              />
            </div>
            <div className="date-picker">
              <p>Fecha de Publicación Hasta:</p>
              <DatePicker
                className="custom-date-picker"
                selected={searchCriteria.publicationDateEnd}
                onChange={(date) => handleDateChange('publicationDateEnd', date)}
              />
            </div>
          </div>
          <Button type="submit" variant="contained" color="primary">Buscar</Button>
        </form>
      </div>
      <div className="book-container">
        {books.map((book) => (
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
