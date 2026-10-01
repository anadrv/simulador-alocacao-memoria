public class Memoria {

    private int tamanho;
    private Bloco primeiro;

    public Memoria(int tamanho) {
        this.tamanho = tamanho;
        this.primeiro = new Bloco(tamanho, null);
    }

    public int getTamanho() {
        return tamanho;
    }

    private static class Bloco {

        private int tamanho;
        private Processo processo;
        private Bloco proximo;

        public Bloco(int tamanho, Processo processo) {
            this.tamanho = tamanho;
            this.processo = processo;
        }
    }
}
