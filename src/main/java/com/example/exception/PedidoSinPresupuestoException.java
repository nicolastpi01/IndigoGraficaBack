package com.example.exception;

public class PedidoSinPresupuestoException extends Exception{
    public PedidoSinPresupuestoException(String errorMessage) { super(errorMessage); }
}
