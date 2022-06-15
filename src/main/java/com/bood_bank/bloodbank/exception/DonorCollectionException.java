package com.bood_bank.bloodbank.exception;

public class DonorCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public DonorCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String donorId) {
        if (donorId.equals("")) {
            return "Please Enter Valid Donor Id";
        }
        return "Donor of " + donorId + " not yet registered.";
    }

}
