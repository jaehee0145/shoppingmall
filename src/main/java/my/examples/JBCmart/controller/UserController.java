package my.examples.JBCmart.controller;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.dto.JoinForm;
import my.examples.JBCmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(
            @RequestParam(name = "fail",
                    required = false,
                    defaultValue = "false") String errorFlag) {

        return "users/login"; // view name 을 리턴한다.
    }

    @GetMapping("/join")
    public String Joinform() {
        return "users/joinform";
    }

    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm, BindingResult bindingResult ) {
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.toString());
        }
        if(!joinForm.getPassword1().equals(joinForm.getPassword2())){
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        User user = new User();
        user.setName(joinForm.getName());
        user.setEmail(joinForm.getEmail());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPasswd(passwordEncoder.encode(joinForm.getPassword1()));
        user.setPhone(joinForm.getPhone());

        User result = userService.join(user);
        return "redirect:/main";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "users/welcome";
    }
}
