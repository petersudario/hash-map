
public class WhatthefuckHashTable<K> extends AbstractHashTable<K> {
    @Override
    protected int hash(K key) {
        int peso = 2;
        int sum = 0;
        String k_str = key.toString();
        int k_len = k_str.length();
        for (int idx = 0; idx < k_len; idx++) {
            sum += k_str.charAt(idx);
        }
        System.out.println(sum);
        System.out.println("Hashing key: " + key);
        return (sum + peso) % k_len;
    }
}
