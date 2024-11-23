# language: pt
Funcionalidade: API - Produtos

  @smoke
  Cenário: Registrar um novo produto
    Quando submeter o registro de um novo produto
    Então o produto deve ser registrado com sucesso