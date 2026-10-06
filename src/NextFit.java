public class NextFit {

    private int proximaPosicao = 0;

    public boolean alocar(Memoria memoria, Processo processo) {

        Memoria.Bloco inicioBusca = encontrarBloco(memoria, proximaPosicao);

        Memoria.Bloco atual = inicioBusca;

        do {

            if (atual.estaLivre() && atual.getTamanho() >= processo.getTamanho()) {

                int novaPosicao = atual.getInicio() + processo.getTamanho();

                memoria.alocar(processo, atual);
                proximaPosicao = novaPosicao;

                if (proximaPosicao >= memoria.getTamanho()) {
                    proximaPosicao = 0;
                }

                return true;
            }

            atual = atual.getProximo();

            if (atual == null) {
                atual = memoria.getInicio();
            }

        } while (atual != inicioBusca);

        return false;
    }

    private Memoria.Bloco encontrarBloco(Memoria memoria, int posicao) {

        Memoria.Bloco atual = memoria.getInicio();

        while (atual != null) {

            int inicio = atual.getInicio();
            int fim = inicio + atual.getTamanho();

            if (posicao >= inicio && posicao < fim) {
                return atual;
            }

            atual = atual.getProximo();
        }

        return memoria.getInicio();
    }
}