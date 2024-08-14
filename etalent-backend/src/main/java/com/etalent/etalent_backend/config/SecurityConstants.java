package com.etalent.etalent_backend.config;

public class SecurityConstants {
    public static final String AUTH_REGISTER_URL = "/api/admins/auth/register";
    public static final String AUTH_LOGIN_URL = "/api/admins/auth/login";
    public static final String AUTH_VERIFY_EMAIL_URL = "/api/admins/auth/verify-email";
    public static final String AUTH_FORGOT_PASSWORD = "/api/admins/auth/forgot-password";
    public static final String AUTH_RESET_PASSWORD = "/api/admins/auth/reset-password";
    public static final String ADMIN_PROFILE_UPDATE_URL = "/api/admins/profile-update";
    public static final String ADMIN_PROFILE_URL = "/api/admins/profile";
    public static final String ADMIN_BY_ROLE_URL = "/api/admins/byRole";
    public static final String USERS_BY_ADMIN_URL = "/api/admins/usersAdmin";
    public static final String USERS_GET = "/api/usuarios";
    public static final String CURSOS_BY_ADMINID = "/api/cursosUsuario";
    public static final String USERS_BY_CURSO = "/api/cursosUsuario/usuarios";

    public static final String CHANGE_ADMINS_BY_ID = "/api/admins";

    public static final String ROLE_ADMIN_DO = "AdminDO";
    public static final String ROLE_ADMIN_TIENDA = "AdminTienda";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
}