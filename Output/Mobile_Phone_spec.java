package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_init_to_Calls , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_init_to_Media , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_init_to_Screen , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_init_to_GPS , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Calls_reset , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Media_reset , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_Screen_reset , type = PortType.enforceable ),
	@Port(name = Mobile_Phone_ports.Mobile_Phone_p_GPS_reset , type = PortType.enforceable )
})
@ComponentType(initial = Mobile_Phone_states.Mobile_Phone_s_init , name ="Mobile_Phone")
public class Mobile_Phone_spec{
	public Mobile_Phone_spec(){
	}




@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_init_to_Calls, source = Mobile_Phone_states.Mobile_Phone_s_init, target = Mobile_Phone_states.Mobile_Phone_s_Calls)
	public void trans_init_to_Calls(){
		System.out.println( "component name: Mobile_Phone from :init---> Calls");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_init_to_Media, source = Mobile_Phone_states.Mobile_Phone_s_init, target = Mobile_Phone_states.Mobile_Phone_s_Media)
	public void trans_init_to_Media(){
		System.out.println( "component name: Mobile_Phone from :init---> Media");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_init_to_Screen, source = Mobile_Phone_states.Mobile_Phone_s_init, target = Mobile_Phone_states.Mobile_Phone_s_Screen)
	public void trans_init_to_Screen(){
		System.out.println( "component name: Mobile_Phone from :init---> Screen");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_init_to_GPS, source = Mobile_Phone_states.Mobile_Phone_s_init, target = Mobile_Phone_states.Mobile_Phone_s_GPS)
	public void trans_init_to_GPS(){
		System.out.println( "component name: Mobile_Phone from :init---> GPS");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Calls_reset, source = Mobile_Phone_states.Mobile_Phone_s_Calls, target = Mobile_Phone_states.Mobile_Phone_s_init)
	public void trans_Calls_to_init(){
		System.out.println( "component name: Mobile_Phone from :Calls---> init");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Media_reset, source = Mobile_Phone_states.Mobile_Phone_s_Media, target = Mobile_Phone_states.Mobile_Phone_s_init)
	public void trans_Media_to_init(){
		System.out.println( "component name: Mobile_Phone from :Media---> init");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_Screen_reset, source = Mobile_Phone_states.Mobile_Phone_s_Screen, target = Mobile_Phone_states.Mobile_Phone_s_init)
	public void trans_Screen_to_init(){
		System.out.println( "component name: Mobile_Phone from :Screen---> init");
	}



@Transition(name =Mobile_Phone_ports.Mobile_Phone_p_GPS_reset, source = Mobile_Phone_states.Mobile_Phone_s_GPS, target = Mobile_Phone_states.Mobile_Phone_s_init)
	public void trans_GPS_to_init(){
		System.out.println( "component name: Mobile_Phone from :GPS---> init");
	}



}
