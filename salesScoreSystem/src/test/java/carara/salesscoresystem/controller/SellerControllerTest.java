package carara.salesscoresystem.controller;

import carara.salesscoresystem.dto.SellerDto;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.service.SellerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SellerController.class})
@ExtendWith(SpringExtension.class)
class SellerControllerTest {
    @Autowired
    private SellerController sellerController;

    @MockBean
    private SellerService sellerService;

    @Test
    void testDeleteProduct() throws Exception {
        when(this.sellerService.deleteSeller((Long) any())).thenReturn("Delete Seller");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/sellers/{sellerId}", 123L);
        MockMvcBuilders.standaloneSetup(this.sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Seller"));
    }

    @Test
    void testFindById() throws Exception {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        when(this.sellerService.findById((Long) any())).thenReturn(seller);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sellers/{sellerId}", 123L);
        MockMvcBuilders.standaloneSetup(this.sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"name\":\"Name\"}"));
    }

    @Test
    void testUpdateSeller() throws Exception {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        when(this.sellerService.updateSeller((Long) any(), (SellerDto) any())).thenReturn(seller);

        SellerDto sellerDto = new SellerDto();
        sellerDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(sellerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/sellers/{sellerId}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.sellerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"name\":\"Name\"}"));
    }

    @Test
    void testDeleteProduct2() throws Exception {
        when(this.sellerService.deleteSeller((Long) any())).thenReturn("Delete Seller");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/sellers/{sellerId}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.sellerController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete Seller"));
    }

    @Test
    void testInsertSeller() throws Exception {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(sellerDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sellers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.sellerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

