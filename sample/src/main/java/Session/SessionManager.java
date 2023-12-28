package Session;

public class SessionManager {
    private static String currentUser;

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
