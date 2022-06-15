package com.bood_bank.bloodbank.exception;

public class OrganizationCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public OrganizationCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String organizationName) {
        if (organizationName.equals("")) {
            return "Please Enter Valid Organization Name";
        }
        return "Organization of " + organizationName + " not yet registered.";
    }

    public static String OrganizationAlreadyExists(String organizationName) {
        return "Organization of " + organizationName + " is already registered.";
    }
}
