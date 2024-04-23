package com.fadevox.quotesapp.exception;

public class CustomException extends RuntimeException {
    public CustomException(Exception e) {
        super("Am primit eroarea: " + e + "\n"+
                "in fisierul "+e.getStackTrace()[0].getFileName() +"\n"+
                "in clasa "+e.getStackTrace()[0].getClassName() +"\n"+
                "in metoda "+e.getStackTrace()[0].getMethodName() +"\n"+
                "linia "+e.getStackTrace()[0].getLineNumber());
        System.out.println("Am primit eroarea: " + e + "\n"+
                "in fisierul "+e.getStackTrace()[0].getFileName() +"\n"+
                "in clasa "+e.getStackTrace()[0].getClassName() +"\n"+
                "in metoda "+e.getStackTrace()[0].getMethodName() +"\n"+
                "linia "+e.getStackTrace()[0].getLineNumber());
    }
}
