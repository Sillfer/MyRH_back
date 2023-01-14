package simplon.back.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simplon.back.company.Company;
import simplon.back.company.CompanyService;
import simplon.back.config.JwtService;
import simplon.back.utils.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CompanyService companyService;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(Company registerRequest) {
        Company company = new Company();
        company.setName(registerRequest.getName());
        company.setEmail(registerRequest.getEmail());
        company.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        company.setAddress(registerRequest.getAddress());
        company.setPhone(registerRequest.getPhone());
        company.setLogo(registerRequest.getLogo());
        company.setCity(registerRequest.getCity());
        company.setRole(Role.ROLE_COMPANY);
        companyService.save(company);
        var token = jwtService.generateToken(company);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        Company company = companyService.findByEmail(authRequest.getEmail());
        company.setAuthorities(company.getRole().toString());

        var token = jwtService.generateToken(company);
        return AuthenticationResponse.builder().token(token).build();
    }

}
