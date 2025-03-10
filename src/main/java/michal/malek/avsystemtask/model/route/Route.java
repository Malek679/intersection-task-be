package michal.malek.avsystemtask.model.route;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import michal.malek.avsystemtask.model.route.enums.Direction;
import michal.malek.avsystemtask.model.route.enums.RouteType;
import michal.malek.avsystemtask.model.route.enums.TrafficLightColor;

import java.rmi.server.UID;
import java.util.LinkedList;

@Getter
@Setter
@ToString
public abstract class Route {
    protected String routeUid;
    protected Direction startDirection;
    protected LinkedList<Direction> endDirection;
    protected TrafficLightColor color;
    protected RouteType roadType;

    public Route( Direction startDirection, LinkedList<Direction> endDirection, TrafficLightColor color) {
        this.startDirection = startDirection;
        this.endDirection = endDirection;
        this.color = color;
        this.generateUid();
    }

    protected void generateUid(){
        UID uid = new UID();
        this.routeUid = uid.toString();
    }


    public abstract int getVehicleAmount();
}
