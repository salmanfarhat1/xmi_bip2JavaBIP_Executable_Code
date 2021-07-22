package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Calls_ports.Calls_p_init_to_Calls , type = PortType.enforceable ),
	@Port(name = Calls_ports.Calls_p_Calls_reset , type = PortType.enforceable )
})
@ComponentType(initial = Calls_states.Calls_s_init , name ="Calls")
public class Calls_spec{
	public Calls_spec(){
	}




@Transition(name =Calls_ports.Calls_p_init_to_Calls, source = Calls_states.Calls_s_init, target = Calls_states.Calls_s_Calls)
	public void trans_init_to_Calls(){
		System.out.println( "component name: Calls from :init---> Calls");
	}



@Transition(name =Calls_ports.Calls_p_Calls_reset, source = Calls_states.Calls_s_Calls, target = Calls_states.Calls_s_init)
	public void trans_Calls_to_init(){
		System.out.println( "component name: Calls from :Calls---> init");
	}



}
