public class WorstFit {

    private Memoria.Bloco ultimoBloco;

    public boolean alocar(Memoria memoria, Processo processo) {

        if (ultimoBloco == null) {
            ultimoBloco = memoria.getInicio();
        }

        Memoria.Bloco inicioBusca = ultimoBloco;
        Memoria.Bloco atual = ultimoBloco;
        Memoria.Bloco maiorBloco = null;

        do {

            if (atual.estaLivre()
                    && atual.getTamanho() >= processo.getTamanho()) {

                if (maiorBloco == null
                        || atual.getTamanho() > maiorBloco.getTamanho()) {

                    maiorBloco = atual;
                }
            }

            atual = atual.getProximo();

            if (atual == null) {
                atual = memoria.getInicio();
            }

        } while (atual != inicioBusca);

        if (maiorBloco == null) {
            return false;
        }

        memoria.alocar(processo, maiorBloco);

        ultimoBloco = maiorBloco.getProximo();

        if (ultimoBloco == null) {
            ultimoBloco = memoria.getInicio();
        }

        return true;
    }
}