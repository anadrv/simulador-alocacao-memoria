public class Memoria {

    private static final int TAMANHO_MEMORIA = 1000;

    private Bloco inicio;

    public Memoria() {
        inicio = new Bloco(0, TAMANHO_MEMORIA);
    }

    public int getTamanho() {
        return TAMANHO_MEMORIA;
    }

    public Bloco getInicio() {
        return inicio;
    }

    public void mostrarMemoria() {

        Bloco atual = inicio;

        while (atual != null) {

            if (atual.estaLivre()) {
                System.out.println(
                        "Livre | Inicio: " + atual.getInicio()
                                + " | Tamanho: " + atual.getTamanho()
                );
            } else {
                System.out.println(
                        "Processo " + atual.getProcesso().getId()
                                + " | Inicio: " + atual.getInicio()
                                + " | Tamanho: " + atual.getTamanho()
                );
            }

            atual = atual.getProximo();
        }
    }

    public static class Bloco {

        private int inicio;
        private int tamanho;
        private Processo processo;
        private Bloco proximo;

        public Bloco(int inicio, int tamanho) {
            this.inicio = inicio;
            this.tamanho = tamanho;
            this.processo = null;
            this.proximo = null;
        }

        public int getInicio() {
            return inicio;
        }

        public int getTamanho() {
            return tamanho;
        }

        public void setTamanho(int tamanho) {
            this.tamanho = tamanho;
        }

        public Processo getProcesso() {
            return processo;
        }

        public void setProcesso(Processo processo) {
            this.processo = processo;
        }

        public Bloco getProximo() {
            return proximo;
        }

        public void setProximo(Bloco proximo) {
            this.proximo = proximo;
        }

        public boolean estaLivre() {
            return processo == null;
        }
    }

    public void alocar(Processo processo, Bloco bloco) {

        int tamanhoRestante = bloco.getTamanho() - processo.getTamanho();

        bloco.setProcesso(processo);
        bloco.setTamanho(processo.getTamanho());

        if (tamanhoRestante > 0) {

            Bloco novoBloco = new Bloco(
                    bloco.getInicio() + processo.getTamanho(),
                    tamanhoRestante
            );

            novoBloco.setProximo(bloco.getProximo());
            bloco.setProximo(novoBloco);
        }
    }

    public boolean liberar(int id) {

        Bloco atual = inicio;

        while (atual != null) {

            if (!atual.estaLivre()
                    && atual.getProcesso().getId() == id) {

                atual.setProcesso(null);

                return true;
            }

            atual = atual.getProximo();
        }

        return false;
    }
}