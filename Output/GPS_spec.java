package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = GPS_ports.GPS_p_init_to_GPS , type = PortType.enforceable ),
	@Port(name = GPS_ports.GPS_p_GPS_reset , type = PortType.enforceable )
})
@ComponentType(initial = GPS_states.GPS_s_init , name ="GPS")
public class GPS_spec{
	public GPS_spec(){
	}




@Transition(name =GPS_ports.GPS_p_init_to_GPS, source = GPS_states.GPS_s_init, target = GPS_states.GPS_s_GPS)
	public void trans_init_to_GPS(){
		System.out.println( "component name: GPS from :init---> GPS");
	}



@Transition(name =GPS_ports.GPS_p_GPS_reset, source = GPS_states.GPS_s_GPS, target = GPS_states.GPS_s_init)
	public void trans_GPS_to_init(){
		System.out.println( "component name: GPS from :GPS---> init");
	}



}
