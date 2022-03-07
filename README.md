# 🌌 StarWars API Restful 🚀

## ⚙️ Tecnologias usadas:

- [Java](https://www.java.com/)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)

## ✅ Tasks:
[✔] - Arquitetura e Modelos [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Adicionar rebeldes `POST - endpoint: /rebeldes` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Listar Rebeldes  `GET - endpoint: /rebeldes` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Selecionar Rebelde pela ID `GET - endpoint: /rebeldes/{id}` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Deletar Rebelde pela ID `DELETE - endpoint: /rebeldes/{id}`[@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Atualizar localização do rebelde `PUT - endpoint: /rebeldes/localizacao/{id}/` [@Israel Cena](https://github.com/israelcena)<br>
[✔] - Listar localização do rebelde `GET - endpoint: /rebeldes/localizacao/(id)` [@Israel Cena](https://github.com/israelcena)<br>
[✔] - Reportar o rebelde como um traidor `PATCH - endpoint: /rebeldes/reportar/{id}` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Listar Rebeldes traidores `GET - endpoint: /rebeldes/traidores`[@Pedro Ricardo](https://github.com/phricardorj/)<br>
[❌] - Negociar itens `PUT - endpoint: /rebeldes/negociar/{nome-do-intem}/{id1}/{id2}` [@Thiago Assi](https://github.com/AloneInAbyss) <br>
[⏳] - Relatórios `GET - endpoint: /rebeldes/relatorio` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Listar Inventários de todos os Rebeldes `GET - endpoint: /rebeldes/inventarios` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[✔] - Retornar o número total de itens perdidos `GET - endpoint: /rebeldes/itens-perdidos` [@Pedro Ricardo](https://github.com/phricardorj/)<br>
[❌] - Adicionar URI para retornar pelo header o endereço do rebelde criado. [@Israel Cena](https://github.com/israelcena)<br>
[❌] - Tratamento das Exceções [@Lucas Suplino](https://github.com/LucasSuplino)

## 🗺️ API Documentation

1. (POST) `/rebeldes` - Cadastrar um novo Rebelde
2. (GET) `/rebeldes` - Listar todos os Rebeldes cadastrados
3. (GET) `/rebeldes/{id}` - Selecionar específico Rebelde pela sua ID
4. (DELETE) `/rebeldes/{id}` - Deletar específico Rebelde pela sua ID
5. (GET) `/rebeldes/inventarios` - Listar Inventários de todos os Rebeldes
6. (GET) `/rebeldes/traidores/itens-perdidos` - Retornar o número total de itens perdidos

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
      "qtdArmas": 1,
      "qtdAgua": 3,
      "qtdMunicao": 6,
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
