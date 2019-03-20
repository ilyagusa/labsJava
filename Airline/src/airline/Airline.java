package airline;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Airline {

    public static void main(String[] args) throws FileNotFoundException, IOException {

       InputInfo.readFile();
       InputInfo.helpCommands();
       InputInfo.Commands();
    }

}
