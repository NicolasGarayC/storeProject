import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import BookList from './components/BookList';
import Login from './components/login/login';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/books" element={<BookList />} />
        <Route path="/" element={<Login />} />
        {}
      </Routes>
    </Router>
  );
}

export default App;
