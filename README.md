# MasterMind Game
O algoritmo desenvolvido tenta resolver o jogo de descoberta de senha MasterMind ao guardar sempre o melhor estado e atualizá-lo, sendo o melhor estado a tentativa que obteve mais elementos na posição correta (melhor estado de posição) ou apenas existentes na resposta (melhor estado de elementos). A partir disso, são feitas tentativas parcialmente aleatórias com base nesse estado, as tentativas são analisadas, e caso a tentativa tenha sido melhor (tenha tido mais acertos) que o melhor estado, atualizamos o melhor estado e repetimos isso até descobrir todos os elementos e posições da resposta correta. A descoberta dos elementos e das posições ocorre de maneira independente. Primeiro descobrimos os elementos e somente após o término dessa etapa tentamos descobrir as posições.

### Funcionamento do algoritmo
O algoritmo é inicializado com uma tentativa com os primeiros elementos do alfabeto escolhido. Essa tentativa é analisada e é obrigatoriamente o melhor estado (nesse momento é levada em consideração apenas a presença dos elementos). As próximas tentativas levam em conta a quantidade de acertos do melhor estado. Para o caso da descoberta dos elementos, sem levar em conta a posição, as tentativas são definidas da seguinte forma: os primeiros elementos são escolhidos aleatoriamente dentro do melhor estado de maneira que sejam escolhidos tantos elementos quanto o número de acertos desse estado, o restante dos elementos é escolhido de forma aleatória. Quando todos os elementos forem descobertos, a busca das posições corretas é inicializada. A inicialização dessa busca é o melhor estado (ainda sem levar em conta a posição), ou seja, a tentativa que obteve todos os acertos para a descoberta dos elementos. Essa tentativa é novamente analisada levando em conta agora somente a posição dos elementos e é definida como o melhor estado. Para o caso da descoberta das posições, as tentativas são realizadas da seguinte forma: são mantidos na mesma posição tantos elementos quanto o número de acertos do melhor estado, o restante é trocado de forma aleatória.

### Exemplo de descoberta dos elementos:
* Answer: 15378
* Primeira Tentativa: 01234
* Análise: 2 corretos (posição ou apenas estão lá)
* Melhor estado: 01234 (Inicialização)
* Nova tentativa: 

Pega 2 aleatoriamente do melhor estado e coloca no inicio
32 

Pega o restante aleatoriamento do alfabeto sem repetir
32 586
* Análise: 3 corretos (posição ou apenas estão lá)
* Melhor estado: 32586
* Nova tentativa: 

Pega 3 aleatoriamente do melhor estado e coloca no inicio
683 

Pega o restante aleatoriamento do alfabeto sem repetir
683 10
* Análise: 3 corretos (posição ou apenas estão lá)
* Melhor estado: 32586 (Permanece o mesmo)

E repete até achar 5 corretos.
