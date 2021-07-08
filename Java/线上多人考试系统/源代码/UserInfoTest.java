public class UserInfoTest extends UserInfo{
    int mark;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void saveMark(){
        setCommand(6);
        command();
    }
}
