package com.bood_bank.bloodbank.exception;

public class RecieverCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public RecieverCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String recieverId) {
        if (recieverId.equals("")) {
            return "Please Enter Valid Reciever Id";
        }
        return "Reciever of " + recieverId + " not yet registered.";
    }

    public static String QuantityNotAvailable() {
        return "Insufficent Quantity available.";
    }
}
