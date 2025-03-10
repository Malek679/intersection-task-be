package michal.malek.avsystemtask.model.dto.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {
    private List<StepStatus> stepStatuses;
}
