package michal.malek.avsystemtask.model.dto.json;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Command {
    String type;
    String vehicleId;
    String startRoad;
    String endRoad;
}
