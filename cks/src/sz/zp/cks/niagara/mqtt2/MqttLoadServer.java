package sz.zp.cks.niagara.mqtt2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import sz.zp.cks.action.MqttConnection;

public class MqttLoadServer implements ServletContextListener {

    public static MqttConnection mqttConnection =new MqttConnection ();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("****** mqttConnection stop *******");
		mqttConnection.stop();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("****** mqttConnection start *******");
		mqttConnection.start();
	}

}
