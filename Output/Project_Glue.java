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
				port(Media_spec.class, Media_ports.Media_p_init_to_Camera).requires(Screen_spec.class, Screen_ports.Screen_p_init_to_High_Resolution);
				port(Media_spec.class, Media_ports.Media_p_init_to_Camera).accepts(Screen_spec.class, Screen_ports.Screen_p_init_to_High_Resolution);
				port(Screen_spec.class, Screen_ports.Screen_p_init_to_High_Resolution).requires();
				port(Screen_spec.class, Screen_ports.Screen_p_init_to_High_Resolution).accepts(Media_spec.class, Media_ports.Media_p_init_to_Camera);
			}
		}.build();
	}

}