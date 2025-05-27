import libs.Node;
import libs.MeuArrayList;
import libs.Node;
/**
 * Implementação abstrata de tabela hash com encadeamento separado,
 * fator de carga e redimensionamento automático.
 *
 * @param <K> Tipo da chave
 */
public abstract class AbstractHashTable<K> {
    protected int capacity;
    protected MeuArrayList<Node<K>> table;
    protected int size = 0;
    protected int collisions = 0;
    protected final double LOAD_FACTOR = 0.75;

    public AbstractHashTable() {
        this.capacity = 16;
        this.table = new MeuArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.append(null);
        }
    }

    /** Converte chave em valor inteiro bruto. */
    protected abstract int hash(K key);

    /** Garante índice válido em [0..capacity-1]. */
    private int normalize(int raw) {
        return Math.floorMod(raw, capacity);
    }

    /** Insere chave, conta colisões e verifica fator de carga. */
    public void insert(K key) {
        int idx = normalize(hash(key));
        Node<K> head = table.get(idx);
        if (head != null) collisions++;
        table.set(idx, new Node<>(key, head));
        size++;
        if ((double) size / capacity > LOAD_FACTOR) {
            resize();
        }
    }

    /** Duplica capacity e rehash de todos os elementos. */
    private void resize() {
        int oldCap = capacity;
        MeuArrayList<Node<K>> oldTable = table;

        capacity *= 2;
        table = new MeuArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.append(null);
        }
        size = 0;
        collisions = 0;

        for (int i = 0; i < oldCap; i++) {
            for (Node<K> n = oldTable.get(i); n != null; n = n.next) {
                insert(n.key);
            }
        }
    }

    /** Verifica se a chave existe na tabela. */
    public boolean contains(K key) {
        int idx = normalize(hash(key));
        for (Node<K> n = table.get(idx); n != null; n = n.next) {
            if (n.key.equals(key)) return true;
        }
        return false;
    }

    /** Retorna número total de colisões ocorridas. */
    public int getCollisions() {
        return collisions;
    }

    /** Retorna distribuição de elementos por bucket. */
    public int[] getDistribution() {
        int[] dist = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            for (Node<K> n = table.get(i); n != null; n = n.next) {
                dist[i]++;
            }
        }
        return dist;
    }
}
