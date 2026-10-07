# ⭐ Simulador de Alocação de Memória ⭐

Projeto desenvolvido para a Fase 02 da competência **Desenvolver Simulador de Abstrações de Recursos de S.O.**

## ✦ Sobre o projeto

O sistema simula o funcionamento de um sistema operacional responsável pelo gerenciamento da memória principal.

A memória possui um tamanho total de **1000 unidades** e é representada por blocos livres e ocupados. Durante a simulação, novos processos são gerados de forma periódica e precisam ser alocados em espaços disponíveis da memória.

O simulador implementa quatro algoritmos de alocação de memória:

* **First Fit**
* **Next Fit**
* **Best Fit**
* **Worst Fit**

Os processos que não conseguem ser alocados devido à falta de espaço disponível são descartados.

## ✦ Funcionamento

A simulação ocorre em ciclos. A cada ciclo, novos processos são gerados e enviados para o algoritmo de alocação selecionado.

O fluxo da simulação é:

1. Gerar novos processos;
2. Definir aleatoriamente o tamanho de cada processo;
3. Solicitar ao algoritmo escolhido um espaço livre na memória;
4. Alocar o processo caso exista espaço suficiente;
5. Descartar o processo caso não seja possível realizar a alocação;
6. Remover aleatoriamente um ou dois processos da memória;
7. Liberar o espaço ocupado pelos processos removidos;
8. Atualizar o estado da memória;
9. Calcular as métricas da simulação;
10. Avançar para o próximo ciclo.

A simulação pode ser executada diversas vezes para obter médias dos resultados e permitir a comparação entre os algoritmos.

### ✦ Memória

A memória possui inicialmente **1000 unidades livres**.

Ela é representada por uma estrutura de blocos, onde cada bloco possui informações sobre:

* início;
* tamanho;
* estado do bloco;
* processo armazenado, quando ocupado.

Quando um processo é removido, o espaço ocupado é liberado e volta a ficar disponível para novas alocações.

Exemplo:

```text
Estado inicial:

Bloco livre | Início: 0 | Tamanho: 1000
```

Após a alocação de processos:

```text
Processo 1  | Início: 0  | Tamanho: 30
Processo 2  | Início: 30 | Tamanho: 40
Bloco livre | Início: 70 | Tamanho: 930
```

Quando um processo é removido, seu espaço volta a ser considerado livre:

```text
Bloco livre | Início: 0  | Tamanho: 30
Processo 2  | Início: 30 | Tamanho: 40
Bloco livre | Início: 70 | Tamanho: 930
```

### ✦ Geração de processos

Os processos são criados pelo `GeradorDeProcessos`.

Cada processo recebe:

* um **ID único e incremental**;
* um tamanho aleatório entre **10 e 50 unidades**.

São gerados **2 processos por ciclo** da simulação.

Quando um processo não consegue ser alocado, ele é descartado e não permanece na memória.

### ✦ Algoritmos de alocação

O simulador disponibiliza quatro estratégias para encontrar um espaço livre para o processo.

* **First Fit:** percorre a memória desde o início e utiliza o primeiro bloco livre que seja grande o suficiente;
* **Next Fit:** continua a busca a partir da posição onde a última alocação foi realizada;
* **Best Fit:** procura o menor bloco livre que seja suficiente para armazenar o processo;
* **Worst Fit:** procura o maior bloco livre disponível para realizar a alocação.

Cada algoritmo pode produzir uma distribuição diferente dos espaços livres e ocupados ao longo da simulação.

### ✦ Remoção de processos

A cada ciclo, são removidos aleatoriamente **um ou dois processos** que estão atualmente na memória.

Quando um processo é removido, o espaço ocupado por ele é liberado.

Exemplo:

```text
Processo 1 alocado. Tamanho: 37
Processo 2 alocado. Tamanho: 27

Processo 1 saiu da memória.
Processo 2 saiu da memória.

Estado da memória:

Bloco livre | Início: 0 | Tamanho: 1000
```

A quantidade de processos removidos é aleatória, podendo variar entre um e dois processos por ciclo.

## ✦ Métricas

Para avaliar o comportamento dos algoritmos, o simulador coleta algumas métricas durante as execuções.

### Tamanho médio dos processos

Representa a média do tamanho dos processos gerados durante a simulação.

### Ocupação média da memória

Representa a porcentagem média da memória que permaneceu ocupada durante os ciclos da simulação.

### Taxa de descarte

Representa a porcentagem de processos que não conseguiram ser alocados na memória.

A taxa é calculada considerando a quantidade de processos descartados em relação ao total de processos gerados.

## ✦ Execuções

A simulação pode ser executada várias vezes para reduzir a influência da aleatoriedade nos resultados.

Em cada execução:

* a memória é reiniciada;
* um novo gerador de processos é criado;
* os processos são gerados novamente;
* as métricas são calculadas.

Ao final, as métricas das execuções são utilizadas para calcular uma **média global** para cada algoritmo.

## ✦ Comparação dos algoritmos

O simulador possui uma opção para executar os quatro algoritmos automaticamente.

Ao selecionar essa opção, os detalhes dos ciclos não são exibidos no terminal. Em vez disso, ao final da execução são apresentadas as métricas de cada algoritmo.

Exemplo:

```text
=================================
COMPARAÇÃO DOS ALGORITMOS
=================================

First Fit
Tamanho médio: 30.19
Ocupação média: 58.50%
Taxa de descarte: 9.98%

Next Fit
Tamanho médio: 29.95
Ocupação média: 58.40%
Taxa de descarte: 10.66%

Best Fit
Tamanho médio: 29.89
Ocupação média: 59.72%
Taxa de descarte: 9.79%

Worst Fit
Tamanho médio: 29.99
Ocupação média: 54.41%
Taxa de descarte: 11.42%
```

A comparação permite analisar o comportamento dos diferentes algoritmos em relação ao aproveitamento da memória e à quantidade de processos descartados.

## ✦ Exemplo de execução

```text
-- Simulador Alocação de Memória --

1 - First Fit
2 - Next Fit
3 - Best Fit
4 - Worst Fit
5 - Executar os 4 algoritmos

Escolha o algoritmo: 1

First Fit selecionado.

-- Ciclo 1 --
Processo 1 alocado. Tamanho: 37
Processo 2 alocado. Tamanho: 27
Processo 1 saiu da memória.
Processo 2 saiu da memória.

Estado da memória:
Bloco livre | Início: 0 | Tamanho: 1000

Memória ocupada: 0
Memória livre: 1000

-- Ciclo 2 --
Processo 3 alocado. Tamanho: 31
Processo 4 alocado. Tamanho: 40
Processo 3 saiu da memória.
```

Como a geração dos processos e a remoção são aleatórias, os tamanhos, processos removidos e estados da memória podem variar a cada execução.

## ✦ Estrutura do projeto

As classes foram separadas de acordo com suas responsabilidades dentro da simulação, reduzindo o acoplamento e facilitando a manutenção e evolução do projeto.

```text
src/
├── Main.java
├── Memoria.java
├── Processo.java
├── GeradorDeProcessos.java
├── FirstFit.java
├── NextFit.java
├── BestFit.java
└── WorstFit.java
```

### ✦ `Main.java`

Ponto de entrada da aplicação. Responsável por apresentar o menu, selecionar o algoritmo, controlar as execuções e apresentar as métricas da simulação.

### ✦ `Memoria.java`

Representa a memória principal e controla os blocos livres e ocupados.

É responsável por realizar operações como:

* alocação de processos;
* liberação de memória;
* cálculo da memória ocupada;
* cálculo da memória livre;
* exibição do estado atual da memória.

### ✦ `Processo.java`

Representa os processos utilizados na simulação.

Cada processo possui um identificador único e um tamanho que representa a quantidade de memória necessária para sua alocação.

### ✦ `GeradorDeProcessos.java`

Responsável pela criação dos processos, geração dos IDs e definição aleatória do tamanho de cada processo.

### ✦ `FirstFit.java`

Implementa o algoritmo **First Fit**, buscando o primeiro bloco livre que comporte o processo.

### ✦ `NextFit.java`

Implementa o algoritmo **Next Fit**, continuando a busca por espaço livre a partir da posição da última alocação.

### ✦ `BestFit.java`

Implementa o algoritmo **Best Fit**, procurando o menor espaço livre capaz de comportar o processo.

### ✦ `WorstFit.java`

Implementa o algoritmo **Worst Fit**, procurando o maior espaço livre disponível para realizar a alocação.

## ✦ Tecnologias

* Java
* IntelliJ IDEA
* Git
* GitHub

## ✦ Como executar

1. Clone o repositório:

```bash
git clone URL_DO_REPOSITORIO
```

2. Abra o projeto no **IntelliJ IDEA**.

3. Execute a classe `Main`.

4. Escolha um dos algoritmos de alocação:

```text
1 - First Fit
2 - Next Fit
3 - Best Fit
4 - Worst Fit
5 - Executar os 4 algoritmos
```

5. Acompanhe a execução da simulação e, ao final, visualize as métricas calculadas.

6. Ao selecionar a opção **5**, os quatro algoritmos são executados e suas métricas são apresentadas para comparação.
