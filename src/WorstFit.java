public class WorstFit {

    public boolean alocar(Memoria memoria, Processo processo) {

        Memoria.Bloco atual = memoria.getInicio();
        Memoria.Bloco maiorBloco = null;

        while (atual != null) {

            if (atual.estaLivre()
                    && atual.getTamanho() >= processo.getTamanho()) {

                if (maiorBloco == null
                        || atual.getTamanho() > maiorBloco.getTamanho()) {

                    maiorBloco = atual;
                }
            }

            atual = atual.getProximo();
        }

        if (maiorBloco == null) {
            return false;
        }

        memoria.alocar(processo, maiorBloco);

        return true;
    }
}