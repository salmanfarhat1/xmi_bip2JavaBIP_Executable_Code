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
	@Port(name = Screen_ports.Screen_p_init_to_High_Resolution , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_Basic_reset , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_Colour_reset , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_High_Resolution_reset , type = PortType.enforceable ),
	@Port(name = Screen_ports.Screen_p_High_Resolution_to_High_Resolution , type = PortType.enforceable )
})
@ComponentType(initial = Screen_states.Screen_s_init , name ="Screen")
public class Screen_spec{
	public Screen_spec(){
	}




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



@Transition(name =Screen_ports.Screen_p_Basic_reset, source = Screen_states.Screen_s_Basic, target = Screen_states.Screen_s_init)
	public void trans_Basic_to_init(){
		System.out.println( "component name: Screen from :Basic---> init");
	}



@Transition(name =Screen_ports.Screen_p_Colour_reset, source = Screen_states.Screen_s_Colour, target = Screen_states.Screen_s_init)
	public void trans_Colour_to_init(){
		System.out.println( "component name: Screen from :Colour---> init");
	}



@Transition(name =Screen_ports.Screen_p_High_Resolution_reset, source = Screen_states.Screen_s_High_Resolution, target = Screen_states.Screen_s_init)
	public void trans_High_Resolution_to_init(){
		System.out.println( "component name: Screen from :High_Resolution---> init");
	}



@Transition(name =Screen_ports.Screen_p_High_Resolution_to_High_Resolution, source = Screen_states.Screen_s_High_Resolution, target = Screen_states.Screen_s_High_Resolution)
	public void trans_High_Resolution_to_High_Resolution(){
		System.out.println( "component name: Screen from :High_Resolution---> High_Resolution");
	}



}
