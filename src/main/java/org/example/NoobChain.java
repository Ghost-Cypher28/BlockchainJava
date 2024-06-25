package org.example;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to mine block 1");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Tring to mine block 2");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine block 3");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlock is valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe Block chain: ");
        System.out.println(blockchainJson);


//        Block genesisBlock = new Block ("Hi im the first block", "0");
//        System.out.println("Hash for block 1:" + genesisBlock.hash);
//
//        Block secondBlock = new Block("Yoh im the second block", genesisBlock.hash);
//        System.out.println("Hash for block 2:" + secondBlock.hash);
//
//        Block thirdBlock = new Block("Whatsup im the third block", secondBlock.hash);
//        System.out.println("Hash for block 3:" + thirdBlock.hash);

    }

    public static Boolean isChainValid() {
            Block currentBlock;
            Block previousBlock;
            String hashTarget = new String(new char[difficulty]).replace("\n0","0");

//            loop through blockchain to check hashes:
            for (int i = 1; i < blockchain.size(); i++) {
                currentBlock = blockchain.get(i);
                previousBlock = blockchain.get(i-1);

                //compare registered hash and calculated hash:
                if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                    System.out.println("Current Hashes not equal");
                    return false;
                }
//               compare previous hash and registered previous hash
                if (!previousBlock.hash.equals(previousBlock.calculateHash()) ) {
                    System.out.println("Previous hashes is not equal");
                    return false;
                }
                //check if hash is solved
                if (!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                    System.out.println("This block hasn't been mined ");
                    return false;
                    
                }

            }
            return true;
    }
}