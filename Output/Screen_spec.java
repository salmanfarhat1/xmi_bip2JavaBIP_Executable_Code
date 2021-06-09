package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Screen_ports.Screen_p_init_to_Basic , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_init_to_Colour , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_init_to_High_Resolution , type = PortType.enforceable )
})
@ComponentType(initial = Screen_states.Screen_s_init , name ="Screen")
public class Screen_spec{
	public Screen_spec(){}


@Transition(name =Screen_ports.Screen_p_init_to_Basic, source = Screen_states.Screen_s_init, target = Screen_states.Screen_s_Basic)
	public void trans_init_to_Basic(){
		System.out.println( "component name: Screen from :init---> Basic");
	}



@Transition(name =Screen_ports.Screen_p_init_to_Colour, source = Screen_states.Screen_s_init, target = Screen_states.Screen_s_Colour)
	public void trans_init_to_Colour(){
		System.out.println( "component name: Screen from :init---> Colour");
	}



@Transition(name =Screen_ports.Screen_p_init_to_High_Resolution, source = Screen_states.Screen_s_init, target = Screen_states.Screen_s_High_Resolution)
	public void trans_init_to_High_Resolution(){
		System.out.println( "component name: Screen from :init---> High_Resolution");
	}



}
