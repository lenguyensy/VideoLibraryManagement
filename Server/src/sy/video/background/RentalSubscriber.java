package sy.video.background;


import redis.clients.jedis.JedisPubSub;
import sy.config.Logger;

public class RentalSubscriber extends JedisPubSub {
	 
    private static Logger logger = new Logger(RentalSubscriber.class);
 
    @Override
    public void onMessage(String channel, String message) {
        logger.log("Message received on Channel " + channel + ", Message"+ message );
        System.out.println();
    }
 
    @Override
    public void onPMessage(String pattern, String channel, String message) {
 
    }
 
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
 
    }
 
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
 
    }
 
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
 
    }
 
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
 
    }
}

