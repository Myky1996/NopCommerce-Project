package enviromentConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:${env}.properties" })
public interface Enviroment extends Config {

	@Key("userApp.url")
	String userAppUrl();

	@Key("userApp.username")
	String userAppname();

	@Key("userApp.password")
	String userAppPassword();
	
	@Key("adminApp.url")
	String adminAppUrl();
	
	@Key("adminApp.username")
	String adminAppname();
	
	@Key("adminApp.password")
	String adminAppPassword();

}
