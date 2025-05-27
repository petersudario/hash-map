package libs;

public class MeuArrayList<T> {

    private Object[] lista;
    private int tamanho;

    public MeuArrayList() {
        lista = new Object[5];
        tamanho = 0;
    }

    public MeuArrayList(int capacidade) {
        lista = new Object[capacidade];
        tamanho = 0;
    }

    private void aumentarTamanho() {
        int novaCapacidade = tamanho * 2;
        Object[] lindonjohnson = new Object[novaCapacidade];
        if (tamanho >= 0) System.arraycopy(lista, 0, lindonjohnson, 0, tamanho);

        lista = lindonjohnson;

    }

    public void append(T param) {
        if (tamanho == lista.length) aumentarTamanho();
        lista[tamanho++] = param;
    }

    public void set(int indice, T param){
        if (indice >= tamanho || indice < 0) throw new IndexOutOfBoundsException("Índice fora do intervalo" + indice);
        lista[indice] = param;
    }

    public boolean contains(T param) {return indexOf(param) >= 0;}

    public int indexOf(T param){
        if (param == null) {
            for (int i = 0; i < lista.length; i++) {
                if (lista[i] == null) return i;
            }
        } else {
            for (int i = 0; i < lista.length; i++) {
                if (param.equals(lista[i])) return i;
            }
        }
        return -1;
    }

    public void toArray(){
        System.out.println("a fazer");
    }

    @SuppressWarnings("unchecked")
    public T get(int indice) {
        if (indice >= tamanho || indice < 0) throw new IndexOutOfBoundsException("Índice fora do intervalo" + indice);
        return (T) lista[indice];
    }

    @SuppressWarnings("unchecked")
    public T remove(int indice) {
        if (indice >= tamanho || indice < 0) throw new IndexOutOfBoundsException("Índice fora do intervalo" + indice);
        T remover = (T) lista[indice];

        for (int i = indice; i < tamanho - 1; i++) lista[i] = lista[i + 1];

        lista[--tamanho] = null;
        return remover;
    }

    @SuppressWarnings("unchecked")
    public T trim() {
        if (tamanho <= 0) return null;
        int lindonjohnson = --tamanho;
        T trimmed = (T) lista[lindonjohnson];
        lista[lindonjohnson] = null;
        return trimmed;
    }

    public int tamanho(){ return tamanho;}

}