public class NextFit {

    private Memoria.Bloco ultimoBloco;

    public boolean alocar(Memoria memoria, Processo processo) {

        if (ultimoBloco == null) {
            ultimoBloco = memoria.getInicio();
        }

        Memoria.Bloco inicioBusca = ultimoBloco;
        Memoria.Bloco atual = ultimoBloco;

        do {

            if (atual.estaLivre()
                    && atual.getTamanho() >= processo.getTamanho()) {

                memoria.alocar(processo, atual);

                ultimoBloco = atual.getProximo();

                if (ultimoBloco == null) {
                    ultimoBloco = memoria.getInicio();
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
}