package com.viettel.admin.exception;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Component;

@Component
public class CacheConfiguration {
    private static HazelcastInstance hazelcastInstance = null;
    public static HazelcastInstance getHazelcastInstance(){
        return hazelcastInstance;
    }
   /* @Bean("hazelcastInstance")
    public HazelcastInstance hazelcastInstance(JHipsterProperties jHipsterProperties){
        String member = jHipsterProperties.getCache().getHazelcast().getMember();
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress(member.split(";")).setSmartRouting(true);
        hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
        return hazelcastInstance;
    }*/

}
