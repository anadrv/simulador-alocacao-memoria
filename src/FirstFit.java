public class FirstFit {
    
    public boolean alocar(Memoria memoria, Processo processo) {

        Memoria.Bloco atual = memoria.getInicio();

        while (atual != null) {

            if (atual.estaLivre() && atual.getTamanho() >= processo.getTamanho()){

                memoria.alocar(processo, atual);
                return true;
            }
            atual = atual.getProximo();
        }

        return false;

    }
} 
