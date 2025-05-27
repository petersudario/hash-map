import java.util.ArrayList;

/**
 * Classe abstrata para tabela hash com encadeamento separado.
 * Usa lista encadeada em cada bucket para tratar colisões.
 * @param <K> Tipo da chave
 */
public abstract class AbstractHashTable<K> {
    protected final int capacity = 16;      // Ajustado para 16 buckets
    protected final ArrayList<Node<K>> table;
    protected int collisions = 0;

    public AbstractHashTable() {
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(null);
        }
    }

    /**
     * Converte a chave em um valor inteiro bruto (pode ser fora de [0,capacity)).
     */
    protected abstract int hash(K key);

    /**
     * Normaliza índice para o intervalo [0..capacity-1] usando floorMod.
     */
    private int normalize(int raw) {
        return Math.floorMod(raw, capacity);
    }

    /**
     * Insere a chave na tabela; incrementa colisões se bucket ocupado.
     */
    public void insert(K key) {
        int idx = normalize(hash(key));
        Node<K> head = table.get(idx);
        if (head != null) collisions++;
        table.set(idx, new Node<>(key, head));
    }

    /**
     * Verifica se a chave existe na tabela.
     */
    public boolean contains(K key) {
        int idx = normalize(hash(key));
        for (Node<K> curr = table.get(idx); curr != null; curr = curr.next) {
            if (curr.key.equals(key)) return true;
        }
        return false;
    }

    /**
     * Remove um elemento da tabela.
     */
    public boolean remove(K key) {
        int idx = normalize(hash(key));
        Node<K> curr = table.get(idx), prev = null;
        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) table.set(idx, curr.next);
                else prev.next = curr.next;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /**
     * Retorna número de colisões.
     */
    public int getCollisions() { return collisions; }

    /**
     * Retorna distribuição de elementos por bucket.
     */
    public int[] getDistribution() {
        int[] dist = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            for (Node<K> curr = table.get(i); curr != null; curr = curr.next) {
                dist[i]++;
            }
        }
        return dist;
    }

    /**
     * Nó da lista encadeada para colisões.
     */
    protected static class Node<K> {
        K key;
        Node<K> next;
        Node(K key, Node<K> next) { this.key = key; this.next = next; }
    }
}
