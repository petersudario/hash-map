/**
 * Tabela hash com bit‑mixing adaptado para 16 buckets.
 */

public class BitMixHashTable<K> extends AbstractHashTable<K> {
    public BitMixHashTable() {
        super();
    }

    @Override
    protected int hash(K key) {
        if (key == null) return 0;
        int h = key.hashCode();
        h ^= (h >>> 16);
        h ^= (h >>> 7);
        h ^= (h >>> 4);
        return h & (capacity - 1); // máscara rápida para potência de 2
    }
}