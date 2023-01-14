package simplon.back.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simplon.back.agent.Agent;
import simplon.back.agent.AgentService;
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

    private final AgentService agentService;

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

    public AuthenticationResponse registerAgent(Agent registerRequest) {
        Agent agent = new Agent();
        agent.setFullName(registerRequest.getFullName());
        agent.setEmail(registerRequest.getEmail());
        agent.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        agent.setRole(Role.ROLE_AGENT);
        agentService.save(agent);
        var token = jwtService.generateToken(agent);

        return AuthenticationResponse.builder().token(token).agent(agent).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {


        if (!authRequest.getEmail().startsWith("agent")) {
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
            // build token and get company by email
            return AuthenticationResponse.builder().token(token).company(company).build();
        }else {
            Agent agent = agentService.findByEmail(authRequest.getEmail());
            agent.setAuthorities(agent.getRole().toString());
            var token = jwtService.generateToken(agent);
            return AuthenticationResponse.builder().token(token).agent(agent).build();
        }
    }

}
