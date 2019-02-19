package my.examples.JBCmart.service;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.Role;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.repository.RoleRepository;
import my.examples.JBCmart.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getUserAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User join(User user) {
        Role role = roleRepository.getRoleByName("USER");
        user.addRole(role);
        return userRepository.save(user);
    }
}
