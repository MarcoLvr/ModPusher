package it.puppabusters.modpusher;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean inserting = true;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Insert the mod file path [exit to leave]: ");
            String path = scanner.nextLine();

            if(path.equals("exit")) {
                inserting = false;
                continue;
            }

            File file = new File(path);
            if (!file.exists()) {
                System.out.println("File not found!");
                return;
            }

            String hash = calcHash(file);
            if (hash == null) {
                System.out.println("Error while calculating the hash!");
                return;
            }

            System.out.println("Hash: " + hash);
        } while (inserting);
    }


    public static String calcHash(File f) {
        try {
            Path filePath = Path.of(f.getPath());

            byte[] data = Files.readAllBytes(Paths.get(filePath.toUri()));
            byte[] hash = MessageDigest.getInstance("MD5").digest(data);
            String checksum = new BigInteger(1, hash).toString(16);
            return checksum;
        } catch (Exception e) {
            return null;
        }

    }
}
