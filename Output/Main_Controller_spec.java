package Output;
import org.javabip.annotations.ComponentType;
import org.javabip.annotations.Data;
import org.javabip.annotations.Guard;
import org.javabip.annotations.Port;
import org.javabip.annotations.Ports;
import org.javabip.annotations.Transition;
import org.javabip.api.PortType;
@Ports({
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_SBasic , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_SColour , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_SHigh_Resolution , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_SCamera , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_SMP3 , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_SGPS , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_SCalls , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_SBasic_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_SColour_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_SHigh_Resolution_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_SCamera_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_SMP3_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_SGPS_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_SCalls_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_RBasic , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_RColour , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_RHigh_Resolution , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_RCamera , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_RMP3 , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_RGPS , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_Spon_init_to_RCalls , type = PortType.spontaneous ),
	@Port(name = Main_Controller_ports.Main_Controller_p_RBasic_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_RColour_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_RHigh_Resolution_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_RCamera_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_RMP3_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_RGPS_to_init , type = PortType.enforceable ),
	@Port(name = Main_Controller_ports.Main_Controller_p_RCalls_to_init , type = PortType.enforceable )
})
@ComponentType(initial = Main_Controller_states.Main_Controller_s_init , name ="Main_Controller")
public class Main_Controller_spec{
	private Boolean Camera;
	private Boolean GPS;
	private Boolean Basic;
	public Main_Controller_spec(){
		Camera = false;
		GPS = false;
		Basic = false;
	}


@Guard(name = "isCameraOn")
public Boolean checkCamera(){
	 return Camera;
}



@Guard(name = "isGPSOn")
public Boolean checkGPS(){
	 return GPS;
}



@Guard(name = "isBasicOn")
public Boolean checkBasic(){
	 return Basic;
}





@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_SBasic, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_SBasic)
	public void trans_init_to_SBasic(){
		System.out.println( "component name: Main_Controller from :init---> SBasic");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_SColour, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_SColour)
	public void trans_init_to_SColour(){
		System.out.println( "component name: Main_Controller from :init---> SColour");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_SHigh_Resolution, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_SHigh_Resolution)
	public void trans_init_to_SHigh_Resolution(){
		System.out.println( "component name: Main_Controller from :init---> SHigh_Resolution");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_SCamera, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_SCamera)
	public void trans_init_to_SCamera(){
		System.out.println( "component name: Main_Controller from :init---> SCamera");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_SMP3, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_SMP3)
	public void trans_init_to_SMP3(){
		System.out.println( "component name: Main_Controller from :init---> SMP3");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_SGPS, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_SGPS)
	public void trans_init_to_SGPS(){
		System.out.println( "component name: Main_Controller from :init---> SGPS");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_SCalls, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_SCalls)
	public void trans_init_to_SCalls(){
		System.out.println( "component name: Main_Controller from :init---> SCalls");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_SBasic_to_init, source = Main_Controller_states.Main_Controller_s_SBasic, target = Main_Controller_states.Main_Controller_s_init, guard = "!isGPSOn")
	public void trans_SBasic_to_init(){
		Basic = true;
		System.out.println( "component name: Main_Controller from :SBasic---> init");
	}
@Transition(name ="", source = Main_Controller_states.Main_Controller_s_SBasic, target = Main_Controller_states.Main_Controller_s_init, guard = "isGPSOn")
	public void Internal_trans_SBasic_to_init(){
		System.out.println( "component name: Main_Controller from :SBasic---> init (nothing Done cannot be both on )");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_SColour_to_init, source = Main_Controller_states.Main_Controller_s_SColour, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_SColour_to_init(){
		System.out.println( "component name: Main_Controller from :SColour---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_SHigh_Resolution_to_init, source = Main_Controller_states.Main_Controller_s_SHigh_Resolution, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_SHigh_Resolution_to_init(){
		System.out.println( "component name: Main_Controller from :SHigh_Resolution---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_SCamera_to_init, source = Main_Controller_states.Main_Controller_s_SCamera, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_SCamera_to_init(){
		Camera = true;
		System.out.println( "component name: Main_Controller from :SCamera---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_SMP3_to_init, source = Main_Controller_states.Main_Controller_s_SMP3, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_SMP3_to_init(){
		System.out.println( "component name: Main_Controller from :SMP3---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_SGPS_to_init, source = Main_Controller_states.Main_Controller_s_SGPS, target = Main_Controller_states.Main_Controller_s_init, guard = "!isBasicOn")
	public void trans_SGPS_to_init(){
		GPS = true;
		System.out.println( "component name: Main_Controller from :SGPS---> init");
	}
@Transition(name ="", source = Main_Controller_states.Main_Controller_s_SGPS, target = Main_Controller_states.Main_Controller_s_init, guard = "isBasicOn")
	public void Internal_trans_SGPS_to_init(){
		System.out.println( "component name: Main_Controller from :SGPS---> init (nothing Done cannot be both on )");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_SCalls_to_init, source = Main_Controller_states.Main_Controller_s_SCalls, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_SCalls_to_init(){
		System.out.println( "component name: Main_Controller from :SCalls---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_RBasic, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_RBasic)
	public void trans_init_to_RBasic(){
		System.out.println( "component name: Main_Controller from :init---> RBasic");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_RColour, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_RColour)
	public void trans_init_to_RColour(){
		System.out.println( "component name: Main_Controller from :init---> RColour");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_RHigh_Resolution, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_RHigh_Resolution)
	public void trans_init_to_RHigh_Resolution(){
		System.out.println( "component name: Main_Controller from :init---> RHigh_Resolution");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_RCamera, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_RCamera)
	public void trans_init_to_RCamera(){
		System.out.println( "component name: Main_Controller from :init---> RCamera");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_RMP3, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_RMP3)
	public void trans_init_to_RMP3(){
		System.out.println( "component name: Main_Controller from :init---> RMP3");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_RGPS, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_RGPS)
	public void trans_init_to_RGPS(){
		System.out.println( "component name: Main_Controller from :init---> RGPS");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_Spon_init_to_RCalls, source = Main_Controller_states.Main_Controller_s_init, target = Main_Controller_states.Main_Controller_s_RCalls)
	public void trans_init_to_RCalls(){
		System.out.println( "component name: Main_Controller from :init---> RCalls");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_RBasic_to_init, source = Main_Controller_states.Main_Controller_s_RBasic, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_RBasic_to_init(){
		Basic = false;
		System.out.println( "component name: Main_Controller from :RBasic---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_RColour_to_init, source = Main_Controller_states.Main_Controller_s_RColour, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_RColour_to_init(){
		System.out.println( "component name: Main_Controller from :RColour---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_RHigh_Resolution_to_init, source = Main_Controller_states.Main_Controller_s_RHigh_Resolution, target = Main_Controller_states.Main_Controller_s_init, guard = "!isCameraOn")
	public void trans_RHigh_Resolution_to_init(){
		System.out.println( "component name: Main_Controller from :RHigh_Resolution---> init");
	}
@Transition(name ="", source = Main_Controller_states.Main_Controller_s_RHigh_Resolution, target = Main_Controller_states.Main_Controller_s_init, guard = "isCameraOn")
	public void Internal_trans_RHigh_Resolution_to_init(){
		System.out.println( "component name: Main_Controller from :RHigh_Resolution---> init (nothing Done)");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_RCamera_to_init, source = Main_Controller_states.Main_Controller_s_RCamera, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_RCamera_to_init(){
		Camera = false;
		System.out.println( "component name: Main_Controller from :RCamera---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_RMP3_to_init, source = Main_Controller_states.Main_Controller_s_RMP3, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_RMP3_to_init(){
		System.out.println( "component name: Main_Controller from :RMP3---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_RGPS_to_init, source = Main_Controller_states.Main_Controller_s_RGPS, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_RGPS_to_init(){
		GPS = false;
		System.out.println( "component name: Main_Controller from :RGPS---> init");
	}



@Transition(name =Main_Controller_ports.Main_Controller_p_RCalls_to_init, source = Main_Controller_states.Main_Controller_s_RCalls, target = Main_Controller_states.Main_Controller_s_init)
	public void trans_RCalls_to_init(){
		System.out.println( "component name: Main_Controller from :RCalls---> init");
	}



}
