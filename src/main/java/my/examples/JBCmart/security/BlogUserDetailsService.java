package my.examples.JBCmart.security;


import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.Role;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = false)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.getUserByEmail(email);

        if(user == null)
            throw new UsernameNotFoundException(email + "에 해당하는 사용자 없어!");

        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        // 리턴한 값은 세션에 저장된다.
        BlogSecurityUser blogSecurityUser = new BlogSecurityUser(email, user.getPasswd(), authorities);
        blogSecurityUser.setId(user.getId());
        blogSecurityUser.setName(user.getName());
        return blogSecurityUser;
    }
}
