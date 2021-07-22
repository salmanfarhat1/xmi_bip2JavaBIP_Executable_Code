package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Media_ports.Media_p_init_to_Camera , type = PortType.enforceable ),
	@Port(name = Media_ports.Media_p_init_to_MP3 , type = PortType.enforceable ),
	@Port(name = Media_ports.Media_p_Camera_reset , type = PortType.enforceable ),
	@Port(name = Media_ports.Media_p_MP3_reset , type = PortType.enforceable )
})
@ComponentType(initial = Media_states.Media_s_init , name ="Media")
public class Media_spec{
	public Media_spec(){
	}




@Transition(name =Media_ports.Media_p_init_to_Camera, source = Media_states.Media_s_init, target = Media_states.Media_s_Camera)
	public void trans_init_to_Camera(){
		System.out.println( "component name: Media from :init---> Camera");
	}



@Transition(name =Media_ports.Media_p_init_to_MP3, source = Media_states.Media_s_init, target = Media_states.Media_s_MP3)
	public void trans_init_to_MP3(){
		System.out.println( "component name: Media from :init---> MP3");
	}



@Transition(name =Media_ports.Media_p_Camera_reset, source = Media_states.Media_s_Camera, target = Media_states.Media_s_init)
	public void trans_Camera_to_init(){
		System.out.println( "component name: Media from :Camera---> init");
	}



@Transition(name =Media_ports.Media_p_MP3_reset, source = Media_states.Media_s_MP3, target = Media_states.Media_s_init)
	public void trans_MP3_to_init(){
		System.out.println( "component name: Media from :MP3---> init");
	}



}
