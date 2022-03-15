# 🌌 StarWars API Restful 🚀

Este trabalho não tinha como requisito o uso de banco de dados, todos os dados são armazenados na memória da máquina enquanto a aplicação estiver rodando. Nosso objetivo é consolidar princípios do Spring Boot, por esse motivo, focamos, especificamente, nos conceitos fundamentais desse framework e do Java, como Programação orientada a objetos, endpoints, métodos HTTP e testes unitários.

## ⚙️ Technologies:

- [Java](https://www.java.com/)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)
- [JUnit](https://junit.org/junit5/)

## 🗺️ API Documentation

1. (POST) `/rebeldes` - Cadastrar um novo Rebelde
2. (GET) `/rebeldes` - Listar todos os Rebeldes cadastrados
3. (GET) `/rebeldes/{id}` - Selecionar específico Rebelde pela sua ID
4. (DELETE) `/rebeldes/{id}` - Deletar específico Rebelde pela sua ID

###### Segue abaixo um modelo de Json para cadastrar um novo Rebelde

```Json 
{
  "nome": "Rebeldeson",
  "idade": 30,
  "genero": "masculino",
  "localizacao": {
     "latitude": -21.22,
     "logintude": -22.33,
     "nome": "Andromeda"
  },
    "inventario": {
      "qtdArmas": 10,
      "qtdAgua": 30,
      "qtdMunicao": 8,
      "qtdComida": 13
  }
}
```

### Atualizar Localização do rebelde
1. (GET) `/rebeldes/localizacao/(id)` - Selecionar localização de um Rebelde específico pela sua ID
2. (PUT) `/rebeldes/localizacao/(id)` - Atualizar localização de um Rebelde específico pela sua ID

###### Segue abaixo um modelo de Json para atualizar a localização do rebelde

```Json 
{
  "latitude": -15.22,
  "longitude": -10.33,
  "nome": "Andromeda"
}
```

### Obter/Gerar Relátorio
1. (GET) `/rebeldes/relatorio` - Devolve as informações dos Rebeldes, Traidores e muito mais

###### Segue abaixo um exemplo do Relátorio retornado pela API em Json

```Json 
{
    "porcentagemRebeldes": "80.0%",
    "porcentagemTraidores": "20.0%",
    "inventarioRelatorio": {
        "totalArmas": 4,
        "totalAgua": 12,
        "totalMunicao": 24,
        "totalComida": 52,
        "itensPerdidos": 25
    }
}
```

### Negociar Item (Rebelde para Rebelde)
1. (PUT) `/rebeldes/negociar` - Rebeldes conseguem negociar entre eles, traidores não negociam!

```Json
{
    "rebeldeReceptor": "ID_REBELDE_RECEPTOR_AQUI",
    "rebeldeFornecedor": "ID_REBELDE_FORNECEDOR_AQUI",
    "itemReceptor": [{nome: "arma", quantidade: 1}, {nome: "municao", quantidade: 1}],
    "itemFornecedor": [{nome: "agua", quantidade: 3}, {nome: "comida", quantidade: 1}]
}
```

| ITEM | PONTOS |
|------|--------|
| 1 Arma | 4 |
| 1 Munição | 3 |
| 1 Água | 2 |
| 1 Comida | 1 |

O Rebelde Receptor receberá o item pedido em troca de outro item! O item pedido é o "itemReceptor" e a quantidade "qtdItemReceptor". Para a troca ser aceita, o Rebelde
Fornecedor deverá receber um item "itemFornecedor" com a quantidade proporcional ao total de pontos do item pedido pelo Rebelde Receptor.

## ✅ Tasks and Requirements:
[✔] - Adicionar rebeldes `POST - endpoint: /rebeldes` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Listar Rebeldes  `GET - endpoint: /rebeldes` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Selecionar Rebelde pela ID `GET - endpoint: /rebeldes/{id}` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Deletar Rebelde pela ID `DELETE - endpoint: /rebeldes/{id}`[@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Atualizar localização do rebelde `PUT - endpoint: /rebeldes/localizacao/{id}/` [@Israel Cena](https://github.com/israelcena)<br>
[✔] - Listar localização do rebelde `GET - endpoint: /rebeldes/localizacao/(id)` [@Israel Cena](https://github.com/israelcena)<br>
[✔] - Reportar o rebelde como um traidor `PATCH - endpoint: /rebeldes/reportar/{id}` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Listar Rebeldes traidores `GET - endpoint: /rebeldes/traidores`[@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Negociar itens `PUT - endpoint: /rebeldes/negociar` [@Thiago Assi](https://github.com/AloneInAbyss) & [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Relatórios `GET - endpoint: /rebeldes/relatorio` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Adicionar URI para retornar pelo header o endereço do rebelde criado. [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[❌] - Tratamento das Exceções [@Lucas Suplino](https://github.com/LucasSuplino)<br>
[✔] - Realizar ao menos 1 (um) cenário de testes [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Adicionado Logs [@Pedro Ricardo](https://github.com/phricardorj/)

## 🖖 Squad Members<br>
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/phricardorj">
        <img src="https://avatars.githubusercontent.com/u/70300680" width="100px;" alt="phricardorj"/><br>
        <sub>
          <b>Pedro Ricardo</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/AloneInAbyss">
        <img src="https://avatars.githubusercontent.com/u/37054274" width="100px;" alt="AloneInAbyss"/><br>
        <sub>
          <b>Thiago Assi</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/israelcena">
        <img src="https://avatars.githubusercontent.com/u/1072865" width="100px;" alt="israelcena"/><br>
        <sub>
          <b>Israel Cena</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/LucasSuplino">
        <img src="https://avatars.githubusercontent.com/u/31294320" width="100px;" alt="LucasSuplino"/><br>
        <sub>
          <b>Lucas Suplino</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
