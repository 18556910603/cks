package sz.zp.cks.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;


import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Controller;
import sz.zp.cks.dao.AlarmMapper;
import sz.zp.cks.entity.Alarm;
import sz.zp.cks.niagara.mqtt2.MqttLoadServer;
import sz.zp.cks.service.ApmApiService;
import sz.zp.cks.service.EquipmentRepairService;
import sz.zp.cks.utils.StringUtil;
//@Controller
@Component
//@Controller
public class MqttConnection {

	    
	 // 静态初使化当前类

		@Autowired
	    private EquipmentRepairService equipmentRepairService;
		
		@Autowired
	    private ApmApiService apmApiService;

		@Autowired
		private AlarmMapper alarmMapper;
	    

	// 静态初使化当前类

	public static MqttConnection mqttConnection;

	// 在方法上加上注解@PostConstruct，这样方法就会在Bean初始化之后被Spring容器执行（注：Bean初始化包括，实例化Bean，并装配Bean的属性（依赖注入））。
	@PostConstruct
	public void init() {
		mqttConnection = this;
	}

	private final String HOST = "tcp://127.0.0.1:1883";//MQTT服务端IP以及连接端口
	private static final String PTOPIC1 = "topic";//订阅主题
	private static String clientid = "ER"+UUID.randomUUID().toString().replace("-", "");//客户端ID唯一，相同的会被逼下线
	private static MqttClient client;
	private static MqttConnectOptions options;
	//private final String userName = CommonUtil.userName;//MQTT服务端连接账号
	//private final String passWord = CommonUtil.passWord;//MQTT服务端连接密码

	//重连方法一

	//	    public MqttConnection() {
	//	        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
	//	        try {
	//	            client = new MqttClient(HOST, clientid, new MemoryPersistence());
	//	            // MQTT的连接设置
	//	            options = new MqttConnectOptions();
	//	            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
	//	            options.setCleanSession(true);
	//	            // 设置连接的用户名
	//	            //options.setUserName(userName);
	//	            // 设置连接的密码
	//	            //options.setPassword(passWord.toCharArray());
	//	            // 设置超时时间 单位为秒
	//	            options.setConnectionTimeout(10);
	//	            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
	//	            options.setKeepAliveInterval(20);
	//	           // options.setAutomaticReconnect(true);
	//	            // 设置回调
	//	            client.setCallback(new MqttCallback() {
	//	 
	////	                public void connectionLost(Throwable cause) {
	////	                    System.out.println("connectionLost---------");
	////	                    MqttLoadServer.mqttConnection.stop();
	////	                    MqttLoadServer.mqttConnection.start();
	////					
	////	                }
	//	            	public void connectionLost(Throwable throwable) {
	//	                    // 连接丢失后，一般在这里面进行重连
	//	                    System.out.println("[MQTT] 连接断开，30S之后尝试重连...");
	//	                    while(true) {
	//	                        try {
	//	                            Thread.sleep(30000);
	//	                            try {
	//									if(null != client) {
	//									    client.connect(options);
	//									    
	//									    System.out.println("[MQTT]重连成功");
	//									    client.subscribe(PTOPIC1, 1);
	//									}
	//								} catch (Exception e) {
	//									// TODO Auto-generated catch block
	//									System.out.println("[MQTT]重连失败1");
	//									e.printStackTrace();
	//								}
	//	                        
	//	                            break;
	//	                        } catch (Exception e) {
	//	                            e.printStackTrace();
	//	                            System.out.println("[MQTT]重连失败2");
	//	                            continue;
	//	                        }
	//	                    }
	//	                }
	//	                public void messageArrived(String topic, MqttMessage message) throws Exception {
	//	                    System.out.println("***** get message start *****");
	//	                    System.out.println(new Date());
	//	                    System.out.println("topic:" + topic);
	//	                    System.out.println("Qos:" + message.getQos());
	//	                    System.out.println("message:" + new String(message.getPayload(),"UTF-8"));
	//	                    System.out.println("***** get message end *****");
	//	                    System.out.println();
	//	                    
	//	           	     //EquipmentRepairServiceImpl equipmentRepairServiceImpl = new EquipmentRepairServiceImpl();
	//	           	     System.out.println("1111111111111111111-------------------");
	//	           	     //ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
	//	           	     //equipmentRepairService=(EquipmentRepairService) context.getBean("equipmentRepairService");
	//	           	     equipmentRepairService.insertSubmitByMQTT(message);
	//	           	    // ((ConfigurableApplicationContext)context).close();
	//	           	     System.out.println("+++++++++++++++++++");
	//	           	    // equipmentRepairServiceImpl.insertSubmitByMQTT(message);
	//	           	     System.out.println("22222");
	//	                }
	//	 
	//	                public void deliveryComplete(IMqttDeliveryToken token) {
	//	                    System.out.println("deliveryComplete---------" + token.isComplete());
	//	                }
	//	            });
	//	        } catch (MqttException e) {
	//	            e.printStackTrace();
	//	        }
	//	    }

	//重连方法二	    
	public MqttConnection() {
		// host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
		try {
			client = new MqttClient(HOST, clientid, new MemoryPersistence());
			// MQTT的连接设置
			options = new MqttConnectOptions();
			// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
			options.setCleanSession(false);
			// 设置连接的用户名
			//options.setUserName(userName);
			// 设置连接的密码
			//options.setPassword(passWord.toCharArray());
			// 设置超时时间 单位为秒
			options.setConnectionTimeout(10);
			// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(20);
			// options.setAutomaticReconnect(true);
			// 设置回调
			client.setCallback(new MqttCallbackExtended() {
				//连接成功后调用

				@Override
				public void connectComplete(boolean reconnect, String serverURI) {
					System.out.println("[MQTT]连接成功...开始订阅...");
					// TODO Auto-generated method stub
					try {
						client.subscribe(PTOPIC1, 1);//具体订阅代码
					} catch (MqttException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}


				public void connectionLost(Throwable throwable) {

					MqttLoadServer.mqttConnection.stop();
					MqttLoadServer.mqttConnection.start();

					//	            		 System.out.println("connectionLost---------");
					//	            		 //mqttConnection.stop();//关闭
					//	            		 try {
					//	            			 
					//							mqttConnection.reConnect();
					//						} catch (Exception e) {
					//							// TODO Auto-generated catch block
					//							System.out.println("connectionLost---重连失败(1)");
					//							//e.printStackTrace();
					//							System.out.println("connectionLost---重连失败(2)");
					//						}//重新连接
				}
				public void messageArrived(String topic, MqttMessage message) throws Exception {

					//message示例
					//{"source":"cf6_6_632_rsb_ys","timestamp":"2019-三月-28 16:43:44 PM CST","sourceState":"Offnormal","ackState":"Unacked","priority":"1","alarmClass":"HighPriorityAlarms","text":"瞬间用水量过大","lowLimit":"%err:baja:Facets:lowLimit%","highLimit":"80.0","hyperlinkOrd":"../file:^FEI/alarmInfo/alarmInfo.html"}
					System.out.println("***** get message start *****");
					System.out.println(new Date());
					System.out.println("clientid:"+clientid);
					System.out.println("topic:" + topic);
					System.out.println("Qos:" + message.getQos());
					System.out.println("message:" + new String(message.getPayload(),"UTF-8"));
					System.out.println("***** get message end *****");

					if(mqttConnection.equipmentRepairService==null){
						System.out.println("equipmentRepairService注入失败");
					}else{
						System.out.println("equipmentRepairService注入成功");
					}
					//开始处理Niagara传输过来的message
					try {
						String messageStr=new String(message.getPayload(),"utf-8");
						System.out.println("first:"+messageStr);
						messageStr=StringUtil.convertDateOfMqttAlarmString(messageStr);
						//System.out.println("转换格式后"+messageStr);
						JSONObject obj = JSONObject.fromObject(messageStr);
						String uuid=obj.getString("uuid").replace("-", "");
						String normalTime=obj.getString("normalTime");
						String source=obj.getString("source");
						Alarm alarm = new Alarm();
						alarm.setAlarmId(uuid);
						alarm.setNormalTime(normalTime);
						//添加首次启动判断 AbstractMqttDriverNetwork AbstractMqttDriverDevice
						if(StringUtils.equals(source,"AbstractMqttDriverNetwork AbstractMqttDriverDevice")){
							System.out.println("首次启动，无告警");
						}
						else {
                            System.out.println("uuid=" + uuid);
                            System.out.println("normalTime" + normalTime);
                            System.out.println(alarmMapper.determineAlarmId(uuid));
						    if(StringUtils.equals(normalTime,"")){

                                //判断uuid是否已经存在
                                if(alarmMapper.determineAlarmId(uuid)!=0){
                                //if (alarmMapper.determineAlarmIdAndNormalTime(alarm) != 0) {
                                    System.out.println("----拦截---已经存在该异常alarm-----");
                                } else {
                                    System.out.println("无重复uuid，继续执行");
                                    boolean b = mqttConnection.equipmentRepairService.insertSubmitByMQTT(messageStr);
                                    if (b == true) {
                                        System.out.println("成功完成Niagara的数据处理");
                                    } else {
                                        System.out.println("未能完成Niagara的数据处理");
                                    }
                                    System.out.println("then:" + messageStr);
                                    apmApiService.insertByPreForApm(messageStr);
                                }
                            }else {
                                uuid = "NM" + uuid;
                                System.out.println("NM_new_uuid"+uuid);
                                if (alarmMapper.determineAlarmId(uuid) != 0) {
                                    System.out.println("----拦截---已经存在该恢复正常alarm-----");
                                } else {
                                    System.out.println("无重复uuid（恢复正常的uuid，前缀+normal），继续执行");
                                    boolean b = mqttConnection.equipmentRepairService.insertSubmitByMQTT(messageStr);
                                    if (b == true) {
                                        System.out.println("成功完成Niagara的数据处理");
                                    } else {
                                        System.out.println("未能完成Niagara的数据处理");
                                    }
                                    System.out.println("then:" + messageStr);
                                    apmApiService.insertByPreForApm(messageStr);
                                }
                            }
						}
	            	}
	                 catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					System.out.println("deliveryComplete---------" + token.isComplete());
				}
			});
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	//断线重连
	public void reConnect() throws Exception {
		try {
			if(null != client) {
				if (!client.isConnected()) {
					System.out.println("***** client to connect *****");
					client.connect(options);
				} 
				if (client.isConnected()) {//连接成功，跳出连接
					System.out.println("***** connect success *****");

				}
			}else{
				System.out.println("client is null");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			while (true) {
				try {
					//判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
					if (!client.isConnected()) {
						System.out.println("***** client to connect *****");
						client.connect(options);
					}
					if (client.isConnected()) {//连接成功，跳出连接
						System.out.println("***** connect success *****");
						break;
					}
				} catch (MqttException e1) {
					e1.printStackTrace();
				}
			}
			//订阅消息
			// client.subscribe(PTOPIC1, 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			// 断开连接
			if(client.isConnected()){
				client.disconnect();
			}else{
				System.out.println("---------is running stop() to disconnect client ,but client was disconnected-------");
			}
			// 关闭客户端
			//client.close();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws MqttException {
		MqttConnection mqttConnection = new MqttConnection();
		mqttConnection.start();
	}


}
