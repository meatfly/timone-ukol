package biz.hampl.timone.taks1;

import biz.hampl.timone.taks1.repository.PostalRepository;
import biz.hampl.timone.taks1.service.PostalConsolePrinter;
import biz.hampl.timone.taks1.service.PostalService;
import java.io.File;

public class Task1Application {

    public static void main(String[] args) {
        PostalRepository postalRepository = new PostalRepository();
        PostalService postalService = new PostalService(postalRepository);
        PostalConsolePrinter postalConsolePrinter = new PostalConsolePrinter(postalRepository);

        boolean validArgs = false;
        if (args.length == 0) {
            validArgs = true;
            postalConsolePrinter.init();
            postalService.readInput();
        } else {
            if ((args.length == 2)) {
                if (args[0].equalsIgnoreCase("-file")) {
                    File file = new File(args[1]);
                    if (file.exists()) {
                        validArgs = true;
                        postalService.importFile(file);
                        postalConsolePrinter.init();
                        postalService.readInput();
                    }
                }
            }
        }
        if (!validArgs) {
            System.out.println("run without args or with -file <path>");
        }
    }
}
