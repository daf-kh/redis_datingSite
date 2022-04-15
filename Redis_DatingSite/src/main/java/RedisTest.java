import java.util.Random;

public class RedisTest {

    public static final int USERS = 20;
    public static final int SLEEP = 1000;
    public static final String KEY = "USERS_ON_PAGE";

    private static int score = 1;


    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage(KEY);
        redis.init();

        while (true) {
            int random_number_10 = new Random().nextInt(10);
            int random_number_20 = new Random().nextInt(10, 20);
            for (int i = 1; i <= USERS; i++) {
                redis.registerUser(score, i);
                if ((score == random_number_10 || score == random_number_20)) {
                    String paidUser = redis.getRandomUser();
                    System.out.printf("> Пользователь %s оплатил платную услугу\n", paidUser);
                    showUser(paidUser);
                }
                showUser(String.valueOf(i));
                score++;
            }
            score = 1;
            Thread.sleep(SLEEP);
        }

    }

    private static void showUser(String id) throws InterruptedException {
        System.out.printf("На главной странице показываем пользователя %s\n", id);
        Thread.sleep(SLEEP);
    }
}

