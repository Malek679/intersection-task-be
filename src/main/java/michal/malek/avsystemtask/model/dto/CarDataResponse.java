package michal.malek.avsystemtask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDataResponse {
    private String license_plate;
    private String make_and_model;
}
