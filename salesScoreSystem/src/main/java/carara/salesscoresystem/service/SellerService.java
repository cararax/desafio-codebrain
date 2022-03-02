package carara.salesscoresystem.service;

import carara.salesscoresystem.model.Seller;
import carara.salesscoresystem.repository.SellerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SellerService {

    public final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Transactional
    public Seller insertSeller(Seller seller) {
        Seller newSeller = new Seller();
        BeanUtils.copyProperties(seller, newSeller);
        return sellerRepository.save(newSeller);
    }
}
