
# Gerenciador Financeiro

O projeto de Gerenciamento Financeiro é um sistema para controle de receitas e despesas do usuário. O sistema permite adicionar transações financeiras e calcula automaticamente os totais de receitas, despesas e saldo disponível. A implementação utiliza interface gráfica com a biblioteca Swing em Java e armazena os dados em arquivos CSV.


## Autores

- ALLAN VICTOR FARIAS DE OLIVEIRA - 20210051468 [@Allan073](https://github.com/Allan073)
- EDUARDO DE SOUZA BLECHIOR - 20240002240 [@educhior](https://www.github.com/Educhior)


## Como Funciona o Sistema

1. **Início**:
    - O sistema inicia na TelaPrincipal, carregando os totais de receitas, despesas e saldo do arquivo CSV.
2. **Adicionar Receita/Despesa**:
    - O usuário adiciona transações através dos botões correspondentes, que abrem as telas TelaAdicionarReceita ou TelaAdicionarDespesa.
3. **Salvar Dados**:
    - Após o preenchimento dos campos, os dados são salvos no arquivo CSV.
4. **Atualização dos Totais**:
    - A TelaPrincipal atualiza automaticamente os totais após cada registro.

