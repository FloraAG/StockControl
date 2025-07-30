# StockControl

## Introdução

**StockControl** é uma interface que permite ao usuário a manipulação do estoque e usuários cadastrados através de planilhas excel. O programa possui as funcionalidades de:

* Cadastro e manipulação de usuários;
* Cadastro e manipulação do estoque;
* Hierarquia de usuários;
* Interfaces distintas conforme a permissão do usuário;
* Consulta ao estoque geral, em defasagem ou defasado;
* Armazenamento de logs de uso dos itens cadastrados;
* **Atenção**: Não edite as planilhas manualmente para evitar erros.

## Modo de Uso

### Instalação
   * **Linux**
   1. Abra o terminal na pasta com o instalador .deb (stockcontrol_1.0_amd64.deb);
   2. Na linha de comando, digite: sudo apt install ./stockcontrol_1.0_amd64.deb;
   3. O programa será instalado na pasta /opt/stockcontrol.
   
   * **Windows**
   1. Dê um duplo clique no instalador e siga as instruções de instalação;
   2. O instalador permitirá ao usuário escolher a pasta de instalação;
   3. Para executar o programa, abra-o com permissão de administrador.

### Primeira Inicialização

1. Inicie o programa com duplo clique. Serão criadas: a pasta "planilhas" e os arquivos excel "Estoque.xlsx", "Logs.xlsx" e "Usuários.xls";
2. Na tela Login, digite "administrador" no campo usuário e a senha "Definir a senha" com os espaços (entre em contato com o suporte para alteração dessaa senha) no campo senha - essa será a mesma senha que abrirá as planilhas excel;
3. A interface Administração aparecerá na tela, contendo: na parte superior, "Olá, ADMINISTRADOR  Sair", e na parte central esquerda, quatro seções (Home, Cadastro, Consulta, Atualização de Dados) que deverão ser selecionadas conforme necessário:
   * A Seção Home proverá duas tabelas com dados dos itens que estejam próximo do valor mínimo estipulado ou abaixo desse valor;
   * A Seção Cadastro proverá campos de texto, quais, deverão ser preenchidos e utilizados no cadastro de novos usuários. Perceba-se que o campo de Orientação é opcional. A senha do usuário cadastrado será criptografada e armazenada na planilha. Usuários com permissão de administrador terão privilégios para acessar a própria interface Administração e a interface Estoque, podendo cadastrar e manipular itens do estoque;
   * A Seção Consulta proverá um tabela com as informações gerais dos usuários cadastrados;
   * A Seção Atualização de Dados proverá uma menu com os usuários cadastrados, devendo ser selecionado aquele com interesse de realizar a alteração dos dados. Campos com os dados antigos irão aparecer após o usuário confirmar a seleção. Esses campos conterão os dados do usuário selecionado e deverão ser alterados caso deseja fazer a atualização. Do contrário, o usuário selecionado pode ser deletado pelo botão Deletar Usuário - o programa fará um soft delete, ocultando o usuário da planilha "Usuários.xlsx" e excluindo-o após 2 anos da data de exclusão - **NÃO É RECOMENDADO ALTERAR AS PLANILHAS MANUALMENTE PARA O BOM FUNCIONAMENTO DO PROGRAMA**;
4. Cadastre o primeiro usuário com permissão de administrador;
5. Para retornar na tela Login, clique em SAIR na parte superior direita da tela,

### Inicializações Seguintes

#### 1. Permissão de Administrador
(Usuários com permissão de administrador)

1. Inicie o programa e faça o login;
2. A interface Estoque aparecerá na tela, contendo: na parte superior, "Olá, Usuário  Administração  Sair", e na parte central esquerda, seis seções (Home, Cadastro - Estoque, Consulta - Estoque, Atualização de Itens, Declarar Uso, Registro de Uso) - veja a seção de permissão de usuário para detalhes complementares - que deverão ser selecionadas conforme necessário:
   * A Seção Cadastro-Estoque proverá campos de texto, quais, deverão ser preenchidos e utilizados no cadastro de novos itens do estoque. A classificação da periculosidade de produtos químicos segue a [GHS](https://unece.org/sites/default/files/2021-09/GHS_Rev9E_0.pdf). Para definir o estoque mínimo e o estoque atual, o usuário deverá atentar-se ao seguinte:
   1. A unidade de medida do item - o programa permite a inserção de unidades de medida (gr, ml, L, etc) e unidades de contagem (caixa, frascos, etc);
   2. A quantidade mínima do item - o valor, referente a unidade de medida, que indicará que o estoque está abaixo do suficiente ou esgotado;
   3. A quantidade atual do item - o valor, referente a unidade de medida, que representa o estoque atual;
   4. A importância de se ponderar em como deseja manipular o item - p.ex.: a unidade de medida de um frasco plástico de álcool etílico 46,2ºINPM poderá ser classificada em litros ou frasco (unidade de contagem). Dessa forma, os valores do estoque mínimo/atual refletirão a quantidade mínima/atual em litros ou a quantidade mínima/atual de frascos respectivamente. Assim, os usuários poderão declarar o uso em litros ou quantidade do frasco. Recomenda-se que caso a unidade de medida seja classificada como unidade de contagem (caixa, frasco, etc), a unidade de medida e o número total do conteúdo sejam declarados em parêntesis - p.ex.: caixa (20uni), frasco (200ml). **VEJA A SEÇÃO DECLARAR USO PARA MAIS DETALHES**;
   * A Seção Atualização de Itens proverá um menu com os itens cadastrados, devendo ser selecionado aquele com interesse de realizar a alteração dos dados. Campos com os dados antigos irão aparecer após o usuário confirmar a seleção. Esses campos conterão os dados do item selecionado e deverão ser alterados caso deseja fazer a atualização. Do contrário, o item selecionado pode ser deletado pelo botão Deletar Item - o programa fará um hard delete, deletando o item da planilha "Estoque.xlsx". Caso o usuário queira atualizar;
   * A Seção Registro de Uso proverá duas tabelas com o uso dos itens declarados do próprio usuário e o uso geral. Essa seção também permitirá ao usuário selecionar o mês/ano para deletar os registros do período selecionado;
   * A Seção Administração poderá ser selecionado caso o usuário clique em "Administração", no campo superior direito. Veja a descrição da Seção Administração em **"Primeira inicialização"**. Para regressar na Seção Estoque, o usuário precisará clicar em "Estoque", no campo superior direito, ao lado de seu nome.
   
#### 2. Permissão de Usuário
(Todos os usuários cadastrados, mesmo sem permissão administrador)

1. Inicie o programa e faça o login;
2. A interface Estoque aparecerá na tela, contendo: na parte superior, "Olá, Usuário Sair", e na parte central esquerda, quatro seções (Home, Consulta - Estoque, Declarar Uso, Registro de Uso) - veja a seção de permissão de administrador para detalhes complementares - que deverão ser selecionadas conforme necessário:
   * A Seção Home proverá duas tabelas com dados dos itens que estejam próximo do valor mínimo estipulado ou abaixo desse valor mínimo;
   * A Seção Consulta proverá uma tabela com as informações gerais dos itens cadastrados;
   * A Seção Declarar Uso proverá um menu com os itens cadastrados, devendo ser selecionado aquele com interesse de declarar uso. Campos com os dados principais do item aparecerão na tela, junto com o campo texto "Quantidade Utilizada". Para declarar o uso, o usuário deverá atentar-se à unidade de medida, declarando a quantidade utilizada na mesma unidade de medida do item, conforme os exemplos:
   1. Item: Frasco Álcool Etílico\
      Unidade de Medida: frasco(1L)\
      Estoque Atual: 10 (frascos) - na tela aparecerá somente o numero, ficando subentendido que o número refere-se a quantidade de frascos.\
      Quantidade Utilizada: 0.05 (litro) - nesse exemplo hipotético, o usuário utilizou 50ml de um frasco de 1 litro de álcool etílico, dessa forma:
      
                                          1 frasco  ------------ 1000ml
                                             x      ----------   50ml
   3. Item: Caixa Caneta Esferográfica Azul\
      Unidade de Medida: caixa(100uni)\
      Estoque Atual: 5 (caixas) - na tela aparecerá somente o numero, ficando subentendido que o número refere-se a quantidade de caixas.\
      Quantidade Utilizada: 0,10 (caixa) - nesse exemplo hipotético, o usuário utilizou 10 unidades de uma caixa de 100 unidades, dessa forma:

                                          1 caixa  ------------ 100uni
                                             x      ---------  10uni
   4. Item: Frasco Álcool Etílico 1L\
      Unidade de Medida: litro\
      Estoque Atual: 10 (litros) - na tela aparecerá somente o numero, ficando subentendido que o número refere-se a quantidade de litros.\
      Quantidade Utilizada: 0.05 (litro) - nesse exemplo hipotético, o usuário utilizou 50ml de um frasco de 1 litro de álcool etílico, dessa forma:
      
                                          1 L  ------------ 1000ml
                                           x    ----------   50ml
   5. Item: Caixa Caneta Esferográfica Azul 100uni\
      Unidade de Medida: unidade\
      Estoque Atual: 500 (unidades) - na tela aparecerá somente o numero, ficando subentendido que o número refere-se a quantidade unitária do item.\
      Quantidade Utilizada: 10 (unidades) - nesse exemplo hipotético, o usuário utilizou 10 unidades de uma caixa de 100 unidades, não havendo necessidade de conversão.
   * A Seção Registro de Uso proverá uma tabela - veja a seção permissão de administrador para detalhes complementares - com o uso dos itens declarados do usuário;
   * A Seção Perfil, que poderá ser selecionada quando o usuário clicar em seu nome. Nessa Seção, o usuário terá permissão de alterar seus dados pessoais.
   
## Problemas Conhecidos

O programa não permite a redefinição da senha do administrador e não possui suporte para a edição direta das planilhas excel, afim de evitar a corrupção dos arquivos e o livre acesso (a senha deve ser modificada previamente a instalação do programa pelo suporte).

## Créditos

**Desenvolvimento**: Flora Aparecida Gomes de Queiros;\
**Ícone(programa)**: Flora Aparecida Gomes de Queiros;\
**Ícones**: Uicons designed by [Flaticon](https://www.flaticon.com/uicons).\
**Imagens**: \
    Fundo Principal. Foto de Fayette Reynolds M.S., obtido em [Pexels](https://www.pexels.com/pt-br/foto/colorida-cheio-de-cor-ciencia-celula-11198503/) [modificada];\
    Fundo Secundário I. Foto de Dejavu Fry, obtido em [Pexels](https://www.pexels.com/pt-br/foto/flora-crescimento-aumento-floracao-12171685/) [modificada];\
    Fundo Secundário II. Foto de Susanne Jutzeler, suju-foto, obtido em [Pexels](https://www.pexels.com/pt-br/foto/campo-de-girassol-amarelo-foco-raso-sob-o-ceu-ensolarado-1169084/) [modificada];\
    Fundo Secundário III. Foto de Irina Iriser, obtido em [Pexels](https://www.pexels.com/pt-br/foto/foto-de-flores-em-close-up-2534524/) [modificada];\
    Fundo Secundário IV. Foto de Karen F., obtido em [Pexels](https://www.pexels.com/pt-br/foto/jardim-petalas-flor-rosa-9250754/) [modificada].

 
