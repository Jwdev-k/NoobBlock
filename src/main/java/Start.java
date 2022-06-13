import com.google.gson.GsonBuilder;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Start {

    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        blockchain.add(new Block("0", "hello world 1"));
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block(blockchain.get(blockchain.size() - 1).getHash(),"yo 2" ));
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block(blockchain.get(blockchain.size() - 1).getHash(),"ha 2" ));
        blockchain.get(2).mineBlock(difficulty);

        //blockchain.get(2).setHash(blockchain.get(2).getPreviousHash()); //valid test
        System.out.println("BlockChain is valid: " + isChainValid());
        //String to Json
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(json);
    }

    public static boolean isChainValid() throws NoSuchAlgorithmException {
        Block currentBlock;
        Block previousBlock;
        String target = new String(new char[difficulty]).replace("\0", "0");
        for (int i = 1; i < blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
            if (!previousBlock.getHash().equals(currentBlock.previousHash)) {
                return false;
            }
            if (!currentBlock.hash.substring(0,difficulty).equals(target)) {
                return false;
            }
        }

        return true;
    }
}
