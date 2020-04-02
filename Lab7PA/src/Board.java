import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Token> tokenList;


    public Board(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    synchronized void removeToken(Token token) {
        tokenList.remove(token);
    }
}
