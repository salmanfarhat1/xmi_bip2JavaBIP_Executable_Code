package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = MP3_ports.MP3_p_init_to_MP3 , type = PortType.enforceable ),
	@Port(name = MP3_ports.MP3_p_MP3_reset , type = PortType.enforceable )
})
@ComponentType(initial = MP3_states.MP3_s_init , name ="MP3")
public class MP3_spec{
	public MP3_spec(){
	}




@Transition(name =MP3_ports.MP3_p_init_to_MP3, source = MP3_states.MP3_s_init, target = MP3_states.MP3_s_MP3)
	public void trans_init_to_MP3(){
		System.out.println( "component name: MP3 from :init---> MP3");
	}



@Transition(name =MP3_ports.MP3_p_MP3_reset, source = MP3_states.MP3_s_MP3, target = MP3_states.MP3_s_init)
	public void trans_MP3_to_init(){
		System.out.println( "component name: MP3 from :MP3---> init");
	}



}
