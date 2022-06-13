import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Getter @Setter
@ToString
public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String previousHash, String data) throws NoSuchAlgorithmException {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() throws NoSuchAlgorithmException {
        return new SHA256().encrypt(previousHash + timeStamp  + nonce + data);
    }

    public void mineBlock(int difficulty) throws NoSuchAlgorithmException {
        String target = new String(new char[difficulty]).replace("\0","0");
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined : " + getHash());
    }
}
