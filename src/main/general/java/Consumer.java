import generator.JsonGenerator;
import org.springframework.cglib.proxy.Mixin;

public class Consumer {

    public static void main(String[] args) {

        JsonGenerator jsonGenerator = new JsonGenerator();

        jsonGenerator.run();


    }
}
