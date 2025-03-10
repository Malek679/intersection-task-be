package michal.malek.avsystemtask.model.dto.json;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JsonCommands {
    List<Command> commands;
}
