package simplon.back.recruiter;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import simplon.back.company.Company;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recruiter {
    @Id
    @GeneratedValue(generator = "recruiter_id_seq")
    @SequenceGenerator(name = "recruiter_id_seq", sequenceName = "recruiter_id_seq", allocationSize = 1)
    private Long id;

    private String fullName;

    @ManyToOne
    private Company company;
}
