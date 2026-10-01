import java.util.Random;

public class GeradorDeProcessos {

    private int proximoId = 1;
    private Random random = new Random();

    public Processo gerarProcesso() {
        int tamanho = random.nextInt(41) + 10;

        Processo processo = new Processo(proximoId, tamanho);

        proximoId++;

        return processo;
    }
}