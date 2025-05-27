public class SimpleHashTable extends AbstractHashTable<String> {
    @Override
    protected int hash(String key) {
        return key.hashCode();
    }
}