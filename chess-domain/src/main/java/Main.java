import com.angelolagreca.chess.domain.Chessboard;
import com.angelolagreca.chess.domain.exception.InitializationException;

public class Main {

    public static void main(String[] args) throws InitializationException {
        Chessboard chessboard = new Chessboard();
        System.out.println(chessboard);

    }
}
