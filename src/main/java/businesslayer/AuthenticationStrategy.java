package businesslayer;

import datalayer.User;


public interface AuthenticationStrategy {
    User authenticate(String email, String password) throws Exception;
    
}
