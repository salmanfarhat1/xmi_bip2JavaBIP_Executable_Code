package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Camera_ports.Camera_p_init_to_Camera , type = PortType.enforceable ),
	@Port(name = Camera_ports.Camera_p_Camera_reset , type = PortType.enforceable )
})
@ComponentType(initial = Camera_states.Camera_s_init , name ="Camera")
public class Camera_spec{
	public Camera_spec(){
	}




@Transition(name =Camera_ports.Camera_p_init_to_Camera, source = Camera_states.Camera_s_init, target = Camera_states.Camera_s_Camera)
	public void trans_init_to_Camera(){
		System.out.println( "component name: Camera from :init---> Camera");
	}



@Transition(name =Camera_ports.Camera_p_Camera_reset, source = Camera_states.Camera_s_Camera, target = Camera_states.Camera_s_init)
	public void trans_Camera_to_init(){
		System.out.println( "component name: Camera from :Camera---> init");
	}



}
