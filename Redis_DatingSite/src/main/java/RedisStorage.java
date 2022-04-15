import redis.clients.jedis.Jedis;

public class RedisStorage {
    private final String key;
    private final Jedis jedis;

    public RedisStorage(String key) {
        this.key = key;
        jedis = new Jedis("redis://127.0.0.1:6379");
    }

    public void init() {
        if (jedis.exists(key)) {
            jedis.del(key);
        }
    }

    public void registerUser(int score, int id) {
        jedis.zadd(key, score, String.valueOf(id));
    }

    public String getRandomUser() {
        return jedis.zrandmember(key);
    }

}
