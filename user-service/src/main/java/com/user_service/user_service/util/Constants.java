package com.user_service.user_service.util;

public final class Constants {

    // API Base Paths
    public static final String API_BASE_PATH = "/api";
    public static final String API_USERS_PATH = API_BASE_PATH + "/users";

    // HTTP Status Messages
    public static final String USER_CREATED_SUCCESS = "User created successfully";
    public static final String USER_UPDATED_SUCCESS = "User updated successfully";
    public static final String USER_DELETED_SUCCESS = "User deleted successfully";
    public static final String USER_RETRIEVED_SUCCESS = "User retrieved successfully";
    public static final String USERS_RETRIEVED_SUCCESS = "Users retrieved successfully";

    // Error Messages
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String INVALID_INPUT = "Invalid input provided";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error occurred";

    // Validation Messages
    public static final String USERNAME_REQUIRED = "Username is required";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";

    // Field Constants
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 50;
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 100;

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }
}

