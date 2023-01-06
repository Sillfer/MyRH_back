package simplon.back.Auth;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class AuthenticationRequest {

    String email;
    String password;
}
