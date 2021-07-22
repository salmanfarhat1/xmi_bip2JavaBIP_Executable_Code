package Output;
import org.javabip.api.BIPGlue;
import org.javabip.glue.GlueBuilder;



public class Project_Glue{
	private BIPGlue bipGlue;

	public Project_Glue(BIPGlue bipGlue){
		this.bipGlue = bipGlue;
	}

	public Project_Glue(){
		this.bipGlue = init();
	}

	public BIPGlue getBipGlue(){
		return this.bipGlue;
	}

	public void setBipGlue(BIPGlue bipGlue){
		this.bipGlue = bipGlue;
	}
	private BIPGlue init () {
		return bipGlue = new GlueBuilder() {
			@Override
			public void configure() {



				port(Camera_spec.class, Camera_ports.Camera_p_init_to_Camera).requires(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution, Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCamera_to_init);
				port(Camera_spec.class, Camera_ports.Camera_p_init_to_Camera).accepts(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution, Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCamera_to_init);
				port(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution).requires(Camera_spec.class, Camera_ports.Camera_p_init_to_Camera, Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCamera_to_init);
				port(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution).accepts(Camera_spec.class, Camera_ports.Camera_p_init_to_Camera, Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCamera_to_init);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCamera_to_init).requires(Camera_spec.class, Camera_ports.Camera_p_init_to_Camera, Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCamera_to_init).accepts(Camera_spec.class, Camera_ports.Camera_p_init_to_Camera, Screen_spec.class, Screen_ports.Screen_p_High_Resolution_to_High_Resolution);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SBasic_to_init).requires(Screen_spec.class, Screen_ports.Screen_p_init_to_Basic);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SBasic_to_init).accepts(Screen_spec.class, Screen_ports.Screen_p_init_to_Basic);
				port(Screen_spec.class, Screen_ports.Screen_p_init_to_Basic).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SBasic_to_init);
				port(Screen_spec.class, Screen_ports.Screen_p_init_to_Basic).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SBasic_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RBasic_to_init).requires(Screen_spec.class, Screen_ports.Screen_p_Basic_reset);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RBasic_to_init).accepts(Screen_spec.class, Screen_ports.Screen_p_Basic_reset);
				port(Screen_spec.class, Screen_ports.Screen_p_Basic_reset).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RBasic_to_init);
				port(Screen_spec.class, Screen_ports.Screen_p_Basic_reset).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RBasic_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SColour_to_init).requires(Screen_spec.class, Screen_ports.Screen_p_init_to_Colour);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SColour_to_init).accepts(Screen_spec.class, Screen_ports.Screen_p_init_to_Colour);
				port(Screen_spec.class, Screen_ports.Screen_p_init_to_Colour).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SColour_to_init);
				port(Screen_spec.class, Screen_ports.Screen_p_init_to_Colour).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SColour_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RColour_to_init).requires(Screen_spec.class, Screen_ports.Screen_p_Colour_reset);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RColour_to_init).accepts(Screen_spec.class, Screen_ports.Screen_p_Colour_reset);
				port(Screen_spec.class, Screen_ports.Screen_p_Colour_reset).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RColour_to_init);
				port(Screen_spec.class, Screen_ports.Screen_p_Colour_reset).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RColour_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SHigh_Resolution_to_init).requires(Screen_spec.class, Screen_ports.Screen_p_init_to_High_Resolution);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SHigh_Resolution_to_init).accepts(Screen_spec.class, Screen_ports.Screen_p_init_to_High_Resolution);
				port(Screen_spec.class, Screen_ports.Screen_p_init_to_High_Resolution).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SHigh_Resolution_to_init);
				port(Screen_spec.class, Screen_ports.Screen_p_init_to_High_Resolution).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SHigh_Resolution_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RHigh_Resolution_to_init).requires(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_reset);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RHigh_Resolution_to_init).accepts(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_reset);
				port(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_reset).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RHigh_Resolution_to_init);
				port(Screen_spec.class, Screen_ports.Screen_p_High_Resolution_reset).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RHigh_Resolution_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SMP3_to_init).requires(MP3_spec.class, MP3_ports.MP3_p_init_to_MP3);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SMP3_to_init).accepts(MP3_spec.class, MP3_ports.MP3_p_init_to_MP3);
				port(MP3_spec.class, MP3_ports.MP3_p_init_to_MP3).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SMP3_to_init);
				port(MP3_spec.class, MP3_ports.MP3_p_init_to_MP3).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SMP3_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RMP3_to_init).requires(MP3_spec.class, MP3_ports.MP3_p_MP3_reset);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RMP3_to_init).accepts(MP3_spec.class, MP3_ports.MP3_p_MP3_reset);
				port(MP3_spec.class, MP3_ports.MP3_p_MP3_reset).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RMP3_to_init);
				port(MP3_spec.class, MP3_ports.MP3_p_MP3_reset).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RMP3_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SGPS_to_init).requires(GPS_spec.class, GPS_ports.GPS_p_init_to_GPS);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SGPS_to_init).accepts(GPS_spec.class, GPS_ports.GPS_p_init_to_GPS);
				port(GPS_spec.class, GPS_ports.GPS_p_init_to_GPS).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SGPS_to_init);
				port(GPS_spec.class, GPS_ports.GPS_p_init_to_GPS).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SGPS_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RGPS_to_init).requires(GPS_spec.class, GPS_ports.GPS_p_GPS_reset);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RGPS_to_init).accepts(GPS_spec.class, GPS_ports.GPS_p_GPS_reset);
				port(GPS_spec.class, GPS_ports.GPS_p_GPS_reset).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RGPS_to_init);
				port(GPS_spec.class, GPS_ports.GPS_p_GPS_reset).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RGPS_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCalls_to_init).requires(Calls_spec.class, Calls_ports.Calls_p_init_to_Calls);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCalls_to_init).accepts(Calls_spec.class, Calls_ports.Calls_p_init_to_Calls);
				port(Calls_spec.class, Calls_ports.Calls_p_init_to_Calls).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCalls_to_init);
				port(Calls_spec.class, Calls_ports.Calls_p_init_to_Calls).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_SCalls_to_init);



				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RCalls_to_init).requires(Calls_spec.class, Calls_ports.Calls_p_Calls_reset);
				port(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RCalls_to_init).accepts(Calls_spec.class, Calls_ports.Calls_p_Calls_reset);
				port(Calls_spec.class, Calls_ports.Calls_p_Calls_reset).requires(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RCalls_to_init);
				port(Calls_spec.class, Calls_ports.Calls_p_Calls_reset).accepts(Main_Controller_spec.class, Main_Controller_ports.Main_Controller_p_RCalls_to_init);
			}
		}.build();
	}

}