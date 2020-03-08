package biz.hampl.timone.taks1.service;

import biz.hampl.timone.taks1.repository.PostalRepository;
import biz.hampl.timone.taks1.vo.PackageVo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class PostalService {

    private final PostalRepository postalRepository;

    public static final String QUIT = "quit";
    public static final String HELP = "help";
    private static boolean exit = false;

    public PostalService(PostalRepository postalRepository) {
        this.postalRepository = postalRepository;
    }

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            String line = scanner.nextLine();
            if (QUIT.equalsIgnoreCase(line)) {
                exit = true;
            } else if (HELP.equalsIgnoreCase(line)) {
                showHelp();
            } else {
                PackageVo packageVo = parseLine(line);
                if (packageVo != null) {
                    addData(packageVo);
                } else {
                    invalidInput();
                }
            }
        }
    }

    private void invalidInput() {
        System.out.println("invalid input enter 'help' for show help");
    }

    private void addData(PackageVo line) {
        postalRepository.add(line);
    }

    private void showHelp() {
        System.out.println("Valid Input line format:"
                + "<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits>");
        System.out.println("enter 'help' for show this help");
        System.out.println("enter 'quit' for exit program");
    }

    protected PackageVo parseLine(String line) {
        if (StringUtils.isEmpty(line)) {
            return null;
        }
        String[] s = line.split(" ");
        if (s.length != 2) {
            return null;
        }
        String weightS = s[0];
        String postalCodeS = s[1];

        if (!weightS.matches("^\\d*\\.?\\d{0,3}$")) {
            return null;
        }
        if (!postalCodeS.matches("\\d{5}")) {
            return null;
        }
        try {
            double weight = Double.parseDouble(weightS);
            int postalCode = Integer.parseInt(postalCodeS);
            if (weight < 0.0) {
                return null;
            }
            return new PackageVo(weight, postalCode);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void importFile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = reader.readLine();
            PackageVo packageVo = parseLine(line);
            if (packageVo != null) {
                addData(packageVo);
            }
            while (line != null) {
                line = reader.readLine();
                packageVo = parseLine(line);
                if (packageVo != null) {
                    addData(packageVo);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
