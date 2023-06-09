import me.n1ar4.fake.proto.GreetingMessage;

public class GreetingFromServerTest {
    public static void main(String[] args) {
        GreetingMessage g = new GreetingMessage();
        System.out.println(g.getBytes().length);
    }
}
