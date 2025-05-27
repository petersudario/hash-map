package libs;

/**
 * Nó genérico para uso em tabelas hash com encadeamento separado.
 * @param <K> Tipo da chave armazenada no nó
 */
public class Node<K> {
    public final K key;
    public Node<K> next;

    public Node(K key, Node<K> next) {
        this.key = key;
        this.next = next;
    }
}