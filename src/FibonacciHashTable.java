/**
 * Tabela hash usando Fibonacci hashing adaptado para 16 buckets.
 */
public class FibonacciHashTable<K> extends AbstractHashTable<K> {
    private static final long FIB_CONST = 0x9E3779B9L; // floor(2^32/φ)

    @Override
    protected int hash(K key) {
        if (key == null) return 0;
        long h = key.hashCode() & 0xFFFFFFFFL;
        long prod = h * FIB_CONST;
        // extrai 4 bits mais significativos para índice [0..15]
        return (int)(prod >>> (32 - 4));
    }
}
