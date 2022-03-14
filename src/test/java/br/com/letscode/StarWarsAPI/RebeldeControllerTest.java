package br.com.letscode.StarWarsAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class RebeldeControllerTest {

        @Autowired
        MockMvc mvc;

        @Test
        void cadastrarRebeldeComSucesso() throws Exception {
            mvc.perform(
                            MockMvcRequestBuilders
                                    .post("/rebeldes")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("{\"nome\":\"Rebeldeson\",\"idade\":30,\"genero\":\"masculino\",\"localizacao\":{\"latitude\":-21.22,\"logintude\":-22.33,\"nome\":\"Andromeda\"},\"inventario\":{\"qtdArmas\":1,\"qtdAgua\":3,\"qtdMunicao\":6,\"qtdComida\":13}}"))
                    .andExpect(status().isCreated());
        }



}
