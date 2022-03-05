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
[✔] - Atualizar localização do rebelde `PUT - endpoint: /rebeldes/atualizar/{id}/` [@Israel Cena](https://github.com/israelcena)<br> 
[✔] - Listar localização do rebelde `GET - endpoint: /rebeldes/listarloc/(id)` [@Israel Cena](https://github.com/israelcena)<br> 
[✔] - Deletar Rebelde pela ID `DELETE - endpoint: /rebeldes/{id}`[@Pedro Ricardo](https://github.com/phricardorj/)<br>
[❌] - Reportar o rebelde como um traidor `PATCH - endpoint: /rebeldes/reportar/{id}` [@Lucas Suplino](https://github.com/LucasSuplino) <br> 
[❌] - Negociar itens `PUT - endpoint: /rebeldes/negociar/{nome-do-intem}/{id1}/{id2}` [@Thiago Assi](https://github.com/AloneInAbyss) <br>
[❌] - Relatórios `GET - endpoint: /rebeldes/relatorio` <br>
[❌] - Tratamento das Exceções - STATUS HTTP: 200 (OK), 201 (Created), 204 (No Content), 422 (Unprocessable Entity)

## API Mapping

Assumimos que o artefato esteja rodando localmente na porta 8080.

### Atualizar Localização do rebelde
1. (get) http://localhost:8080/rebeldes/listarloc/(id)  Verifica local do Rebelde.
2. (put) http://localhost:8080/rebeldes/atualizar/(id) Atualiza Localização do rebelde

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
