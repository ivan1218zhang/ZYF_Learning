import java.io.FileInputStream;
import java.io.IOException;

public class QuestionUpdate extends Question{
    public QuestionUpdate() {

    }

    public void update() throws IOException {
        FileInputStream inputStream = new FileInputStream(getImage_url());
        pic_data = new byte[inputStream.available()];
        inputStream.read(pic_data);
        setCommand(5);
        command();
    }
}
