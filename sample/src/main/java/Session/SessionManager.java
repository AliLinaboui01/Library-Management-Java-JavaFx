package Session;

public class SessionManager {
    private static String currentUser;
    private static int currentUserId;

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
