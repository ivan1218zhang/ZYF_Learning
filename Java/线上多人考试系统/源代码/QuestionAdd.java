import java.io.*;

public class QuestionAdd extends Question{
    public void add(){
        try {
            FileInputStream inputStream = new FileInputStream(image_url);
            pic_data = new byte[inputStream.available()];
            inputStream.read(pic_data);
        } catch (IOException e) {
            pic_data=new byte[1];
        }
        setCommand(2);
        command();
    }
}
