package ch.bfh.bti7081.s2019.black.spitexorganizer.security;

public class Role {
    public final static String ADMIN = "admin";
    public final static String USER = "user";

    private Role() {
    }

    public static String[] getAllRoles() {
        return new String[]{USER, ADMIN};
    }
}
