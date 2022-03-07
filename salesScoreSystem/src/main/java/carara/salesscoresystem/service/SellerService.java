package carara.salesscoresystem.service;

import carara.salesscoresystem.dto.SellerDto;
import carara.salesscoresystem.exception.EntityNotFoundException;
import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.repository.SellerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SellerService {

    public final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Transactional
    public Seller insertSeller(SellerDto sellerDto) {
        Seller sellerToInsert = new Seller();
        BeanUtils.copyProperties(sellerDto, sellerToInsert);
        return sellerRepository.save(sellerToInsert);
    }

    @Transactional
    public Seller updateSeller(Long sellerId, SellerDto sellerDto) {
        Seller sellerToUpdate = findById(sellerId);
        BeanUtils.copyProperties(sellerToUpdate, sellerDto);
        return sellerRepository.save(sellerToUpdate);
    }

    @Transactional
    public String deleteSeller(Long sellerId) {
        Seller seller = findById(sellerId);
        sellerRepository.delete(seller);
        return "Seller with id " + sellerId + " was deleted successfully.";
    }

    public Seller findById(Long sellerId) {
        Optional<Seller> sellerById = sellerRepository.findById(sellerId);
        if (sellerById.isEmpty()) {
            throw new EntityNotFoundException("Seller with id  " + sellerId + " was not found.");
        }
        return sellerById.get();
    }
}
