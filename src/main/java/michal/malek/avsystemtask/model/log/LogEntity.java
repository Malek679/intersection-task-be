package michal.malek.avsystemtask.model.log;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date timestamp;
    private int step;

    public LogEntity(String message, int step) {
        this.message = message;
        this.step = step;
        this.timestamp = new Date();
    }
}
