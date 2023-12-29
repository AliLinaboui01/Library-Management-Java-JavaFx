package Session;

public class SessionManager {
    private static String currentUser;
    private static int currentUserId;
    private static String email;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static void setCurrentUserId(int currentUserId) {
        SessionManager.currentUserId = currentUserId;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        SessionManager.currentUser = currentUser;
    }
    public static void clearSession(){
        currentUser=null;
    }
}
