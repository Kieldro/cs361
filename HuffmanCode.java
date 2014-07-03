import java.util.*;
 
abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public final int frequency; // the frequency of this tree
    public HuffmanTree(int freq) { frequency = freq; }
 
    // compares on the frequency
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}
 
class HuffmanLeaf extends HuffmanTree {
    public final String value; // the character this leaf represents
 
    public HuffmanLeaf(int freq, String val) {
        super(freq);
        value = val;
    }
}
 
class HuffmanNode extends HuffmanTree {
    public final HuffmanTree left, right; // subtrees
 
    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}
 
public class HuffmanCode {
    public static HashMap<String, String> encodings = new HashMap();
    
    // input is an array of frequencies, indexed by character code
    public static HuffmanTree buildTree(HashMap<String, Integer> charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        // initially, we have a forest of leaves
        // one for each non-empty character
        for (String key : charFreqs.keySet())
            trees.offer(new HuffmanLeaf(charFreqs.get(key), key));
    
        assert trees.size() > 0;
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
    
            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(a, b));
        }
        return trees.poll();
    }
    
    
    public static void printCodes(HuffmanTree tree) {
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        genCodes(tree, new StringBuffer());
    }
    
    private static void genCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            
            // print out character, frequency, and code for this leaf (which is just the prefix)
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
            encodings.put(leaf.value, prefix.toString());
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
            
            // traverse left
            prefix.append('0');
            genCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
            
            // traverse right
            prefix.append('1');
            genCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
 
    public static void main(String[] args) {
        String test = "this is an example for huffman encoding";
 
        // we will assume that all our characters will have
        // code less than 256, for simplicity
        HashMap<String, Integer> charFreqs = new HashMap();
        // int[] charFreqs = new int[256];
        // read each character and record the frequencies
        // for (char c : test.toCharArray()){
        //     int prev = charFreqs.get(c);
        //     charFreqs.put(c, ++prev);
            
        // }
 
        // // build tree
        // HuffmanTree tree = buildTree(charFreqs);
 
        // // print out results
        // System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        // printCodes(tree, new StringBuffer());
    }
}