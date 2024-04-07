import './CartModal.css'
import React, { useState, useEffect } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';

function CartModal({ isOpen, onClose, cart, onCompletePurchase, onRemoveFromCart }) {
    const totalCost = cart.reduce((total, item) => total + (item.book.price * item.quantity), 0);

    return (
        <Dialog onClose={onClose} aria-labelledby="customized-dialog-title" open={isOpen}>
            <DialogTitle sx={{ m: 0, p: 2 }} id="customized-dialog-title">
                Carrito de Compras
                <IconButton aria-label="close" onClick={onClose} sx={{ position: 'absolute', right: 8, top: 8, color: (theme) => theme.palette.grey[500] }}>
                    <CloseIcon />
                </IconButton>
            </DialogTitle>
            <DialogContent dividers>
                {cart.map(item => (
                    <div key={item.book.id} style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                        <Typography gutterBottom>
                            {item.book.title} - {item.quantity} unidad(es) - ${item.book.price} c/u
                        </Typography>
                        <IconButton onClick={() => onRemoveFromCart(item.book.id)}>
                            <DeleteIcon />
                        </IconButton>
                    </div>
                ))}
                <Typography gutterBottom>Total: ${totalCost}</Typography>
            </DialogContent>
            <DialogActions>
                <Button onClick={onCompletePurchase}>Comprar</Button>
                <Button onClick={onClose}>Cerrar</Button>
            </DialogActions>
        </Dialog>
    );
}

export default CartModal;
