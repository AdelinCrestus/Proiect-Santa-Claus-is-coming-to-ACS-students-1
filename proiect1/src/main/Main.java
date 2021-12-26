package main;

import checker.Checker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Input;
import santa.SantaClaus;

import java.io.File;
import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 1; i <= 25 ; i++) {
            StringBuilder stringBuilder = new StringBuilder("tests/test");
            stringBuilder.append(i);
            stringBuilder.append(".json");
            Input input = objectMapper.readValue(new File(stringBuilder.toString()), Input.class);
            SantaClaus santaClaus = SantaClaus.getInstance();
            santaClaus.set(input);

            System.out.println(input);
        }
        Checker.calculateScore();
    }
}
