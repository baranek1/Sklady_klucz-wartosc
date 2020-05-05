import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.net.UnknownHostException;

public class HMember {
    public static void main( String[] args ) throws UnknownHostException {
        Config config = HConfig.getConfig();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);



    }
}
