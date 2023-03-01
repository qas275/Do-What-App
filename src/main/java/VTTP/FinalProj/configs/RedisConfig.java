package VTTP.FinalProj.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    

    @Value("${REDISHOST}")
    private String redHost;

    @Value("${REDISPORT}")
    private Integer redPort;

    @Value("${REDISPASSWORD}")
    private String redPassword;

    @Value("${REDISUSER}")
    private String redUser;

    @Bean("redis_results_cache")
    @Scope("singleton")
    public RedisTemplate<String, String> cRedisTemplate(){
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        System.out.println(redUser+ redPassword);
        config.setHostName(redHost);
        config.setDatabase(0);
        config.setPassword(redPassword);
        config.setUsername(redUser);
        config.setPort(redPort);

        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFactory = new JedisConnectionFactory(config, jedisClient);
        jedisFactory.afterPropertiesSet();

        final RedisTemplate<String, String> template= new RedisTemplate<>();
        template.setConnectionFactory(jedisFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
