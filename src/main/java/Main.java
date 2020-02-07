import service.encryptor.Encryptor;
import service.encryptor.PBKDF2Encryptor;

public class Main {

    public static void main(String[] args) {
        Encryptor encryptor = new PBKDF2Encryptor();
        System.out.println(encryptor.hash("Kjkszpj97/".toCharArray()));
    }
}
