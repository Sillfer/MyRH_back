package simplon.back;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import simplon.back.agent.Agent;
import simplon.back.agent.AgentService;
import simplon.back.utils.Role;

@SpringBootApplication
@AllArgsConstructor
public class BackApplication {

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(AgentService agentService) {
//        return args -> {
////			add a new agent on commandline start using agentservice save
//            agentService.save(
//                    new Agent(
//                            "Elmahdi Gliouine",
//                            "gliouinemahdi@gmail.com",
//                            passwordEncoder.encode("12345678")
//                    )
//            );
//        };
//    }

}
