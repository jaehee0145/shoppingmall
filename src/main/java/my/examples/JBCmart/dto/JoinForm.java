package my.examples.JBCmart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {

    //<form method="post" action="/users/login">
    //    name : <input type="text" name="name"><br>
    //    email : <input type="text" name="email"><br>
    //    password : <input type="password" name="password1"><br>
    //    password 확인 : <input type="password" name="password2"><br>
    //    phone : <input type="text" name="phone"><br>

    private String name;
    private String email;
    private String password1;
    private String password2;
    private String phone;
}
