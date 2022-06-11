package org.pj.metaverse.cache.utils;

import com.alibaba.fastjson.JSON;
import io.github.dengliming.redismodule.redisjson.RedisJSON;
import io.github.dengliming.redismodule.redisjson.args.GetArgs;
import io.github.dengliming.redismodule.redisjson.args.SetArgs;
import io.github.dengliming.redismodule.redisjson.client.RedisJSONClient;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RFuture;
import org.redisson.config.Config;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pengjie
 * @date 15:10 2022/6/1
 **/

public class RedisJsonTemplate extends StringRedisTemplate {


    /**
     * 调用自增等方法的例子，json如下
     * {
     *     "id": 3,
     *     "name": "test"
     * }
     * @date 2022/6/6 11:45
     */
    public final String pathExample = "$.id";

    public final String defaultPath = ".";

    public RedisJSONClient getRedisJsonClient() {
        return redisJsonClient;
    }

    protected RedisJSONClient redisJsonClient;

    public RedisJsonTemplate(Config config, RedisConnectionFactory redisConnectionFactory) {
        super(redisConnectionFactory);
        this.redisJsonClient = new RedisJSONClient(config);
    }

    public <T> T getJson(String key, String path, Class<T> tClass){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.get(key, tClass, new GetArgs().path(path).indent("\t").newLine("\n"));
    }

    public <T> T getJsonAsync(String key, String path, Class<T> tClass){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.get(key, tClass, new GetArgs().path(path).indent("\t").newLine("\n"));
    }

    public <T> List<T> multiGetJson(String[] keys, String path, Class<T> tClass){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.mget(path, tClass, keys);
    }

    public <T> List<T> multiGetJsonAsync(String[] keys, String path, Class<T> tClass){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.mget(path, tClass, keys);
    }

    public <T> void setJson(T t, String key,String path){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        redisJson.set(key, SetArgs.Builder.create(path, JSON.toJSONString(t)));
    }

    public <T> RFuture<String> setJsonAsync(T t, String key, String path){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.setAsync(key, SetArgs.Builder.create(path, JSON.toJSONString(t)));
    }


    public long delJson(String key, String path){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.del(key, path);
    }

    public RFuture<Long> delJsonAsync(String key, String path){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.delAsync(key, path);
    }

    public Class getType(String key, String path) {
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.getType(key, path);
    }
    public RFuture<Class> getTypeAsync(String key, String path) {
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.getTypeAsync(key, path);
    }

    public String incrBy(String key, String path, long delta){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.incrBy(key, path , delta );
    }

    public RFuture<String> incrByAsync(String key, String path, long delta){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.incrByAsync(key, path , delta );
    }
    public String multBy(String key, String path, long delta){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.multBy(key, path , delta );
    }
    public RFuture<String> multByAsync(String key, String path, long delta){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.multByAsync(key, path , delta );
    }

    public Long strAppend(String key, String path, String value){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.strAppend(key, path , value );
    }
    public RFuture<Long> strAppendAsync(String key, String path, String value){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.strAppendAsync(key, path , value );
    }
    public Long strLen(String key, String path){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.strLen(key, path );
    }
    public RFuture<Long> strLenAsync(String key, String path){
        RedisJSON redisJson = redisJsonClient.getRedisJSON();
        return redisJson.strLenAsync(key, path );
    }

}
