//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>();

        hashTable.insert("pepo");
        hashTable.insert("java");
        hashTable.insert("hash");
        hashTable.insert("colisão");
        hashTable.insert("estrutura");
        hashTable.insert("tabela");
        hashTable.insert("mapa");
        hashTable.insert("função");

        System.out.println("Contém 'pepo'? " + hashTable.contains("pepo"));
        System.out.println("Contém 'python'? " + hashTable.contains("python"));

        System.out.println("Remover 'java': " + hashTable.remove("java"));
        System.out.println("Contém 'java'? " + hashTable.contains("java"));

        System.out.println("\nColisões: " + hashTable.getCollisions());
        System.out.println("Distribuição por bucket:");
        hashTable.printDistribution();
    }

    static class HashTable<K> {
        HashTable(Node<K>[] table) {
            this.table = table;
        }

        private static class Node<K> {
            K key;
            Node<K> next;

            Node(K key, Node<K> next) {
                this.key = key;
                this.next = next;
            }
        }

        private final int capacity = 8;
        private final Node<K>[] table;
        private int collisions = 0;

        @SuppressWarnings("unchecked")
        public HashTable() {
            table = (Node<K>[]) new Node[capacity];
        }

        private int hash(K key) {
            return Math.floorMod(key.hashCode(), capacity);
        }

        public void insert(K key) {
            int idx = hash(key);
            Node<K> head = table[idx];

            if (head != null) collisions++;

            table[idx] = new Node<>(key, head);
        }

        public boolean contains(K key) {
            int idx = hash(key);
            Node<K> current = table[idx];
            while (current != null) {
                if (current.key.equals(key)) return true;
                current = current.next;
            }
            return false;
        }

        public boolean remove(K key) {
            int idx = hash(key);
            Node<K> curr = table[idx], prev = null;

            while (curr != null) {
                if (curr.key.equals(key)) {
                    if (prev == null) table[idx] = curr.next;
                    else prev.next = curr.next;
                    return true;
                }
                prev = curr;
                curr = curr.next;
            }
            return false;
        }

        public int getCollisions() {
            return collisions;
        }

        public void printDistribution() {
            for (int i = 0; i < capacity; i++) {
                int count = 0;
                Node<K> curr = table[i];
                while (curr != null) {
                    count++;
                    curr = curr.next;
                }
                System.out.println("Bucket " + i + ": " + count + " elemento(s)");
            }
        }
    }
}
