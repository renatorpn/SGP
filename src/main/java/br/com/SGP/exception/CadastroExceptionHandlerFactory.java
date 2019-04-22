package br.com.SGP.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CadastroExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory factory;

    public CadastroExceptionHandlerFactory(ExceptionHandlerFactory factory) {
        this.factory = factory;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new CadastroExceptionHandler(factory.getExceptionHandler());
    }
}
