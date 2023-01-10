package simplon.back.recruiter;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @RequestMapping("/save")
    public Recruiter save(@RequestBody Recruiter recruiter) {
        System.out.println(recruiter.toString());
        return recruiterService.save(recruiter);
    }
}
