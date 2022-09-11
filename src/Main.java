import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    static final String OUTPUT_FILENAME = "output.txt";

    public static void main(String[] args) {
        try {
            Optional<Map<Integer, List<String>>> logs;
            String fileName = args[0];
            InputHandler inputHandler = new InputHandler();
            OutputHandler outputHandler = new OutputHandler();
            inputHandler.read(fileName);
            logs = inputHandler.getLogs();
            if(logs.isPresent()){
                PairingLogic scanner = new PairingLogic(logs.get());
                scanner.findAllPatterns();
                outputHandler.extract(OUTPUT_FILENAME, scanner.getPatterns());
            }
            else{
                System.out.println("\nThis file doesn't contain logs");
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nThis file doesn't exist.");
        } catch (IOException e) {
            System.out.println("\nSomething went wrong.\nPlease try again.");
        }
    }
}
