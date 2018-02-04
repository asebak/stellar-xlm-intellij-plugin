package ${packagename};

import org.stellar.sdk.*;
import org.stellar.sdk.responses.AccountResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

    public class Main {
    private static final String HORIZON_API = "https://horizon-testnet.stellar.org/";
        public static void main(String[] args) throws IOException {
            KeyPair pair = createKeys();
            createAccount(pair.getAccountId());

            Server server = new Server(HORIZON_API);
            AccountResponse account = server.accounts().account(pair);
            System.out.println("Balances for account " + pair.getAccountId());
            for (AccountResponse.Balance balance : account.getBalances()) {
                System.out.println(String.format(
                "Type: %s, Code: %s, Balance: %s",
                balance.getAssetType(),
                balance.getAssetCode(),
                balance.getBalance()));
            }

        }

        private static void createAccount(String accountId) {
            String friendbotUrl = String.format(
            "%sfriendbot?addr=%s", HORIZON_API,
            accountId);
            InputStream response = null;
            try {
            response = new URL(friendbotUrl).openStream();
            } catch (IOException e) {
            e.printStackTrace();
            }
            String body = new Scanner(response, "UTF-8").useDelimiter("\\A").next();
            System.out.println("SUCCESS! You have a new account :)\n" + body);
        }

        public static KeyPair createKeys() {
            KeyPair pair = KeyPair.random();

            System.out.println(new String(pair.getSecretSeed()));
            System.out.println(pair.getAccountId());
            return pair;
        }
}