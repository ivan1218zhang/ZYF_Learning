public class QuestionTest extends Question{
    String userAnswer="";

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    int getMark(){
        if (getUserAnswer().equals(getAnswer())){
            return 10;
        }
        return 0;
    }
}
