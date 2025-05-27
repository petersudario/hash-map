public class Main {
    public static void main(String[] args) {
        SimpleHashTable hashTable = new SimpleHashTable();

        hashTable.insert("banana");
        hashTable.insert("maçã");
        hashTable.insert("laranja");
        hashTable.insert("uva");
        hashTable.insert("melancia");
        hashTable.insert("abacaxi");
        hashTable.insert("pêra");

        System.out.println("Contém 'banana'? " + hashTable.contains("banana")); // true
        System.out.println("Contém 'kiwi'? " + hashTable.contains("kiwi"));     // false

        System.out.println("Remove 'uva': " + hashTable.remove("uva")); // true
        System.out.println("Contém 'uva'? " + hashTable.contains("uva")); // false

        System.out.println("Colisões: " + hashTable.getCollisions());

        int[] dist = hashTable.getDistribution();
        for (int i = 0; i < dist.length; i++) {
            System.out.printf("Bucket %d: %d elementos%n", i, dist[i]);
        }
    }
}
