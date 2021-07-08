public class Question extends Command {
    int id;
    String text="";
    String image_url="";
    String a="";
    String b="";
    String c="";
    String d="";
    String answer="";
    String table="";
    byte[] pic_data={};

    public void setTable(String table) {
        this.table = table;
    }

    public void copy(Question question){
        setId(question.getId());
        setText(question.getText());
        setImage_url(question.image_url);
        setA(question.getA());
        setB(question.getB());
        setC(question.getC());
        setD(question.getD());
        setAnswer(question.getAnswer());
        setTable(question.getTable());
        setPic_data(question.getPic_data());
    }

    public byte[] getPic_data() {
        return pic_data;
    }

    public void setPic_data(byte[] pic_data) {
        this.pic_data = pic_data;
    }

    public String getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
