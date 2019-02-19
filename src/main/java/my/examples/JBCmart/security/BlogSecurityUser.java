package my.examples.JBCmart.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

@Setter
@Getter
public class BlogSecurityUser extends User {
    private Long id;
    private String name;

    public BlogSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, true, true, true, true, authorities);
    }

    public String getEmail(){
        return super.getUsername();
    }
}
