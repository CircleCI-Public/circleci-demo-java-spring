package se459.extremers.simulator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorArray {
    PathOptionsEnum n_sensor; //path info top of the cell
    PathOptionsEnum s_sensor; //path info bottom of the cell
    PathOptionsEnum e_sensor; //path info right of the cell
    PathOptionsEnum w_sensor; //path info left of the cell
    boolean bottom_sensor;  //there is a stair/decline
    boolean dirt_sensor; //whether there is dirt
    SurfaceEnum surface_sensor;  //kind of surface
    boolean charging_station; //whether there is a charging station
    
}
