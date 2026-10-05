public class BestFit {

    private Memoria.Bloco ultimoBloco;

    public boolean alocar(Memoria memoria, Processo processo) {

        if (ultimoBloco == null) {
            ultimoBloco = memoria.getInicio();
        }

        Memoria.Bloco inicioBusca = ultimoBloco;
        Memoria.Bloco atual = ultimoBloco;
        Memoria.Bloco menorBloco = null;

        do {

            if (atual.estaLivre()
                    && atual.getTamanho() >= processo.getTamanho()) {

                if (menorBloco == null
                        || atual.getTamanho() < menorBloco.getTamanho()) {

                    menorBloco = atual;
                }
            }

            atual = atual.getProximo();

            if (atual == null) {
                atual = memoria.getInicio();
            }

        } while (atual != inicioBusca);

        if (menorBloco == null) {
            return false;
        }

        memoria.alocar(processo, menorBloco);

        ultimoBloco = menorBloco.getProximo();

        if (ultimoBloco == null) {
            ultimoBloco = memoria.getInicio();
        }

        return true;
    }
}

