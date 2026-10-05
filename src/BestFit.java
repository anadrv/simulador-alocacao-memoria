public class BestFit {

    public boolean alocar(Memoria memoria, Processo processo) {

        Memoria.Bloco atual = memoria.getInicio();
        Memoria.Bloco menorBloco = null;

        while (atual != null) {

            if (atual.estaLivre()
                    && atual.getTamanho() >= processo.getTamanho()) {

                if (menorBloco == null
                        || atual.getTamanho() < menorBloco.getTamanho()) {

                    menorBloco = atual;
                }
            }

            atual = atual.getProximo();
        }

        if (menorBloco == null) {
            return false;
        }

        memoria.alocar(processo, menorBloco);

        return true;
    }
}


