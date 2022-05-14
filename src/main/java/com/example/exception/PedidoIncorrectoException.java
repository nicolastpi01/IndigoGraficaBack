package com.example.exception;

public class PedidoIncorrectoException extends Exception{

    public static String ALTO_O_ANCHO_INVALIDOS = "El alto o ancho del pedido debe ser mayor que cero";
    public static String TIPO_DE_ARCHIVOS_INVALIDOS = "";

    public PedidoIncorrectoException(String errorMessage) { super(errorMessage); }
}