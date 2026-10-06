import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n-- Simulador Alocação de Memória K-pop 3/5 --");
        System.out.print("\nescolha o algoritmo: ");
        System.out.println("\n1 - First Fit");
        System.out.println("2 - Next Fit");
        System.out.println("3 - Best Fit");
        System.out.println("4 - Worst Fit");


        int opcao = scanner.nextInt();

        switch (opcao) {

            case 1:
                System.out.println("First Fit selecionado.");
                break;

            case 2:
                System.out.println("Next Fit selecionado.");
                break;

            case 3:
                System.out.println("Best Fit selecionado.");
                break;

            case 4:
                System.out.println("Worst Fit selecionado.");
                break;

            default:
                System.out.println("Opção inválida.");
                scanner.close();
                return;
        }

        Memoria memoria = new Memoria();
        GeradorDeProcessos gerador = new GeradorDeProcessos();
        FirstFit firstFit = new FirstFit();
        NextFit nextFit = new NextFit();
        WorstFit worstFit = new WorstFit();
        BestFit bestFit = new BestFit();

        List<Processo> processosNaMemoria = new ArrayList<>();
        Random random = new Random();

        for (int segundo = 1; segundo <= 100; segundo++) {

            System.out.println("\n--Ciclo " + segundo + " --");

            for (int i = 0; i < 2; i++) {

                Processo processo = gerador.gerarProcesso();

                boolean alocado = false;

                switch (opcao) {

                    case 1:
                        // First Fit
                        alocado = firstFit.alocar(memoria, processo);
                        break;

                    case 2:
                        // Next Fit
                        alocado = nextFit.alocar(memoria, processo);
                        break;

                    case 3:
                        // Best Fit
                        alocado = bestFit.alocar(memoria, processo);
                        break;

                    case 4:
                        //Worst Fit
                        alocado = worstFit.alocar(memoria, processo);
                        break;
                }

                if (alocado) {

                    processosNaMemoria.add(processo);

                    System.out.println(
                            "Processo " + processo.getId()
                                    + " alocado. Tamanho: "
                                    + processo.getTamanho()
                    );

                } else {

                    System.out.println(
                            "Processo " + processo.getId()
                                    + " descartado. Tamanho: "
                                    + processo.getTamanho()
                    );
                }
            }

            if (!processosNaMemoria.isEmpty()) {

                int quantidadeRemover = random.nextInt(2) + 1;

                quantidadeRemover = Math.min(
                        quantidadeRemover,
                        processosNaMemoria.size()
                );

                for (int i = 0; i < quantidadeRemover; i++) {

                    int indice = random.nextInt(
                            processosNaMemoria.size()
                    );

                    Processo processoRemovido =
                            processosNaMemoria.remove(indice);

                    memoria.liberar(processoRemovido.getId());

                    System.out.println(
                            "Processo " + processoRemovido.getId()
                                    + " saiu da memória."
                    );
                }
            }

            System.out.println("\nEstado da memória:");
            memoria.mostrarMemoria();

            Thread.sleep(1000);
        }

        System.out.println("\n-- fim do simulador --");

        scanner.close();
    }
}
