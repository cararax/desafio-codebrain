package carara.salesscoresystem.service;

import carara.salesscoresystem.dto.SellerDto;
import carara.salesscoresystem.exception.EntityNotFoundException;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SellerService.class})
@ExtendWith(SpringExtension.class)
class SellerServiceTest {
    @MockBean
    private SellerRepository sellerRepository;

    @Autowired
    private SellerService sellerService;

    @Test
    void testInsertSeller() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        when(this.sellerRepository.save((Seller) any())).thenReturn(seller);
        assertSame(seller, this.sellerService.insertSeller(new SellerDto("Name")));
        verify(this.sellerRepository).save((Seller) any());
    }

    @Test
    void testInsertSeller2() {
        when(this.sellerRepository.save((Seller) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> this.sellerService.insertSeller(new SellerDto("Name")));
        verify(this.sellerRepository).save((Seller) any());
    }

    @Test
    void testUpdateSeller() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        Optional<Seller> ofResult = Optional.of(seller);

        Seller seller1 = new Seller();
        seller1.setId(123L);
        seller1.setName("Name");
        when(this.sellerRepository.save((Seller) any())).thenReturn(seller1);
        when(this.sellerRepository.findById((Long) any())).thenReturn(ofResult);
        SellerDto sellerDto = new SellerDto("Name");
        assertSame(seller1, this.sellerService.updateSeller(123L, sellerDto));
        verify(this.sellerRepository).save((Seller) any());
        verify(this.sellerRepository).findById((Long) any());
        assertEquals("Name", sellerDto.getName());
    }

    @Test
    void testUpdateSeller2() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        Optional<Seller> ofResult = Optional.of(seller);
        when(this.sellerRepository.save((Seller) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        when(this.sellerRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> this.sellerService.updateSeller(123L, new SellerDto("Name")));
        verify(this.sellerRepository).save((Seller) any());
        verify(this.sellerRepository).findById((Long) any());
    }

    @Test
    void testUpdateSeller3() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        when(this.sellerRepository.save((Seller) any())).thenReturn(seller);
        when(this.sellerRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> this.sellerService.updateSeller(123L, new SellerDto("Name")));
        verify(this.sellerRepository).findById((Long) any());
    }

    @Test
    void testDeleteSeller() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        Optional<Seller> ofResult = Optional.of(seller);
        doNothing().when(this.sellerRepository).delete((Seller) any());
        when(this.sellerRepository.findById((Long) any())).thenReturn(ofResult);
        assertEquals("Seller with id 123 was deleted successfully.", this.sellerService.deleteSeller(123L));
        verify(this.sellerRepository).findById((Long) any());
        verify(this.sellerRepository).delete((Seller) any());
    }

    @Test
    void testDeleteSeller2() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        Optional<Seller> ofResult = Optional.of(seller);
        doThrow(new EntityNotFoundException("An error occurred")).when(this.sellerRepository).delete((Seller) any());
        when(this.sellerRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(EntityNotFoundException.class, () -> this.sellerService.deleteSeller(123L));
        verify(this.sellerRepository).findById((Long) any());
        verify(this.sellerRepository).delete((Seller) any());
    }

    @Test
    void testDeleteSeller3() {
        doNothing().when(this.sellerRepository).delete((Seller) any());
        when(this.sellerRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> this.sellerService.deleteSeller(123L));
        verify(this.sellerRepository).findById((Long) any());
    }

    @Test
    void testFindById() {
        Seller seller = new Seller();
        seller.setId(123L);
        seller.setName("Name");
        Optional<Seller> ofResult = Optional.of(seller);
        when(this.sellerRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(seller, this.sellerService.findById(123L));
        verify(this.sellerRepository).findById((Long) any());
    }

    @Test
    void testFindById2() {
        when(this.sellerRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> this.sellerService.findById(123L));
        verify(this.sellerRepository).findById((Long) any());
    }

    @Test
    void testFindById3() {
        when(this.sellerRepository.findById((Long) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> this.sellerService.findById(123L));
        verify(this.sellerRepository).findById((Long) any());
    }
}

