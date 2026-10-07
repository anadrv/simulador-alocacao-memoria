import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int QUANTIDADE_EXECUCOES = 100;
    private static final int QUANTIDADE_SEGUNDOS = 100;
    private static final int PROCESSOS_POR_SEGUNDO = 2;
    private static final int TEMPO_ESPERA = 0;

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n-- Simulador Alocação de Memória K-pop 3/5 --");
        System.out.println("\n1 - First Fit");
        System.out.println("2 - Next Fit");
        System.out.println("3 - Best Fit");
        System.out.println("4 - Worst Fit");
        System.out.println("5 - Executar os 4 algoritmos");
        System.out.print("\nescolha o algoritmo: ");

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

            case 5:
                System.out.println("Executando os 4 algoritmos.");
                break;

            default:
                System.out.println("Opção inválida.");
                scanner.close();
                return;
        }

        boolean mostrarDetalhes = opcao != 5;

        int inicioAlgoritmo = opcao == 5 ? 1 : opcao;
        int fimAlgoritmo = opcao == 5 ? 4 : opcao;

        List<String> nomesAlgoritmos = new ArrayList<>();
        List<Double> mediasTamanho = new ArrayList<>();
        List<Double> mediasOcupacao = new ArrayList<>();
        List<Double> mediasDescarte = new ArrayList<>();

        for (int algoritmo = inicioAlgoritmo;
             algoritmo <= fimAlgoritmo;
             algoritmo++) {

            double somaTamanhoMedio = 0;
            double somaOcupacaoMedia = 0;
            double somaTaxaDescarte = 0;

            String nomeAlgoritmo = switch (algoritmo) {
                case 1 -> "First Fit";
                case 2 -> "Next Fit";
                case 3 -> "Best Fit";
                case 4 -> "Worst Fit";
                default -> "";
            };

            if (mostrarDetalhes) {
                System.out.println("-- Executando: " + nomeAlgoritmo);

            }

            for (int execucao = 1; execucao <= QUANTIDADE_EXECUCOES; execucao++) {

                if (mostrarDetalhes) {
                    System.out.println("\nExecução " + execucao + "/" + QUANTIDADE_EXECUCOES);
                }

                Memoria memoria = new Memoria();

                GeradorDeProcessos gerador = new GeradorDeProcessos();

                FirstFit firstFit = new FirstFit();
                NextFit nextFit = new NextFit();
                WorstFit worstFit = new WorstFit();
                BestFit bestFit = new BestFit();

                List<Processo> processosNaMemoria = new ArrayList<>();

                Random random = new Random();

                int processosGerados = 0;
                int processosDescartados = 0;
                int somaTamanhoProcessos = 0;
                double somaOcupacao = 0;

                for (int segundo = 1; segundo <= QUANTIDADE_SEGUNDOS; segundo++) {

                    if (mostrarDetalhes) {
                        System.out.println("\n-- Ciclo " + segundo + " --");
                    }

                    for (int i = 0; i < PROCESSOS_POR_SEGUNDO; i++) {

                        Processo processo = gerador.gerarProcesso();
                        processosGerados++;
                        somaTamanhoProcessos += processo.getTamanho();

                        boolean alocado = false;

                        switch (algoritmo) {

                            case 1:
                                alocado = firstFit.alocar(memoria, processo);
                                break;

                            case 2:
                                alocado = nextFit.alocar(memoria, processo);
                                break;

                            case 3:
                                alocado = bestFit.alocar(memoria, processo);
                                break;

                            case 4:
                                alocado = worstFit.alocar(memoria, processo);
                                break;
                        }

                        if (alocado) {

                            processosNaMemoria.add(processo);

                            if (mostrarDetalhes) {
                                System.out.println("Processo " + processo.getId() + " alocado. Tamanho: " + processo.getTamanho());
                            }

                        } else {

                            processosDescartados++;

                            if (mostrarDetalhes) {
                                System.out.println("Processo " + processo.getId() + " descartado. Tamanho: " + processo.getTamanho());
                            }
                        }
                    }

                    if (!processosNaMemoria.isEmpty()) {

                        int quantidadeRemover = random.nextInt(2) + 1;
                        quantidadeRemover = Math.min(quantidadeRemover, processosNaMemoria.size());

                        for (int i = 0; i < quantidadeRemover; i++) {

                            int indice = random.nextInt(processosNaMemoria.size());

                            Processo processoRemovido = processosNaMemoria.remove(indice);
                            memoria.liberar(processoRemovido.getId());

                            if (mostrarDetalhes) {
                                System.out.println("Processo " + processoRemovido.getId()
                                                + " saiu da memória."
                                );
                            }
                        }
                    }

                    if (mostrarDetalhes) {
                        System.out.println("\nEstado da memória:");
                        memoria.mostrarMemoria();
                    }

                    int memoriaOcupada = memoria.getMemoriaOcupada();
                    double ocupacao = (memoriaOcupada * 100.0) / memoria.getTamanho();
                    somaOcupacao += ocupacao;

                    Thread.sleep(TEMPO_ESPERA);
                }

                double tamanhoMedio = (double) somaTamanhoProcessos / processosGerados;
                double ocupacaoMedia = somaOcupacao / QUANTIDADE_SEGUNDOS;
                double taxaDescarte = ((double) processosDescartados / processosGerados) * 100;

                somaTamanhoMedio += tamanhoMedio;
                somaOcupacaoMedia += ocupacaoMedia;
                somaTaxaDescarte += taxaDescarte;

                if (mostrarDetalhes) {

                    System.out.println("\n-- Métricas da execução --");
                    System.out.println("Processos gerados: " + processosGerados);
                    System.out.println("Processos descartados: " + processosDescartados);
                    System.out.printf("Tamanho médio dos processos: %.2f%n", tamanhoMedio);
                    System.out.printf("Ocupação média da memória: %.2f%%%n", ocupacaoMedia);
                    System.out.printf("Taxa de descarte: %.2f%%%n", taxaDescarte);
                }
            }

            double mediaGlobalTamanho = somaTamanhoMedio / QUANTIDADE_EXECUCOES;
            double mediaGlobalOcupacao = somaOcupacaoMedia / QUANTIDADE_EXECUCOES;
            double mediaGlobalDescarte = somaTaxaDescarte / QUANTIDADE_EXECUCOES;

            nomesAlgoritmos.add(nomeAlgoritmo);
            mediasTamanho.add(mediaGlobalTamanho);
            mediasOcupacao.add(mediaGlobalOcupacao);
            mediasDescarte.add(mediaGlobalDescarte);

            if (opcao != 5) {
                System.out.println("\n-- Média Global --");
                System.out.printf("Tamanho médio dos processos: %.2f%n", mediaGlobalTamanho);
                System.out.printf("Ocupação média da memória: %.2f%%%n", mediaGlobalOcupacao);
                System.out.printf("Taxa de descarte: %.2f%%%n", mediaGlobalDescarte);
            }
        }

        if (opcao == 5) {

            System.out.println("\n-- Métricas dos Algoritmos --");

            for (int i = 0; i < nomesAlgoritmos.size(); i++) {

                System.out.println("\n" + nomesAlgoritmos.get(i));
                System.out.printf("Tamanho médio: %.2f%n", mediasTamanho.get(i));
                System.out.printf("Ocupação média: %.2f%%%n", mediasOcupacao.get(i));
                System.out.printf("Taxa de descarte: %.2f%%%n", mediasDescarte.get(i));
            }
        }

        System.out.println("\n-- fim do simulador --");

        scanner.close();
    }
}
